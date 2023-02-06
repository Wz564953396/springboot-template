package com.wz.example.template.config;

import com.wz.example.template.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    private String[] permitPaths = {"/user/login", "/login.html"};

    private final String loginProcessingUrl = "/user/login";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private MyAuthenticationEntryPointHandler myAuthenticationEntryPointHandler;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setUsernameParameter(filter.SPRING_SECURITY_FORM_USERNAME_KEY);
        filter.setPasswordParameter(filter.SPRING_SECURITY_FORM_PASSWORD_KEY);
        filter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        filter.setFilterProcessesUrl(loginProcessingUrl);
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        return filter;
    }

//    默认是token保存在内存中，手动注入JdbcTokenRepositoryImpl，实现将token保存在数据库中，同时自动建表
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

//    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()                                 //关闭CSRF防护，不知道干嘛用的
                .httpBasic()
                .authenticationEntryPoint(myAuthenticationEntryPointHandler)

                .and()
                .addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()                                    //自定义登录页面
//                .loginPage("/login.html")                       //登录页面路径设置
//                .loginProcessingUrl(loginProcessingUrl)         //定义登录用户名密码提交的接口路径
//                .defaultSuccessUrl("/index.html")               //登录成功后，默认跳转路径
//                .successHandler(myAuthenticationSuccessHandler) //前后端分离情况下，不需要返回静态资源，采用handler方式返回restful数据
//                .failureForwardUrl("/login.html")               //认证失败后，forward（转发）到
//                .failureUrl("/login.html")                      //认证失败后，redirect（重定向）到
//                .failureHandler(myAuthenticationFailureHandler) //前后端分离情况下，不需要返回静态资源，认证失败后返回restful数据

                .and()
                .logout()                                       //拿到注销登录的配置对象，注销登录功能默认是开启的
                .logoutUrl("/user/logout")                      //指定注销登录的url，默认值为"/logout"，请求方式为GET，Security 5.7.2之后，默认GET,POST,PUT,DELETE请求方式都可以
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .invalidateHttpSession(true)                    //使当前会话失效，默认为true
                .clearAuthentication(true)                      //清空当前认证标记，默认为true
                .logoutSuccessUrl("/login.html")                //注销登录后，跳转到某个url上，默认为"/login?logout"
                .logoutRequestMatcher(new OrRequestMatcher(
                        new AntPathRequestMatcher("/user/logout", HttpMethod.POST.name()),
                        new AntPathRequestMatcher("/user/logout1", HttpMethod.GET.name())))
                                                                //自定义注销登录的请求路径和请求方式
                .permitAll()

//                设置拒绝访问
                .and().exceptionHandling().accessDeniedPage("/403.html")
                .and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)

//                设置哪些路径可以直接访问，不需要安全认证
                .and().authorizeRequests().antMatchers(permitPaths).permitAll()
//                权限控制，只能配置单个权限
                .antMatchers("/root/**").hasAuthority("root")

//                权限控制，可配置多个权限，也可以通过注解@PreAuthorize("hasAnyAuthority('admin,root')")进行配置
                .antMatchers("/admin/**").hasAnyAuthority("admin,root")

//                继续配置方式的角色控制，与权限控制类似，也可以通过注解@Secured("ROLE_root")进行配置
                .antMatchers("/admin/**").hasAnyRole("admin")

                .anyRequest().authenticated()

//                设置《记住我》的token实现
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)   //设置记住时长
                .userDetailsService(userDetailsService)
                .and().build();
    }
}
