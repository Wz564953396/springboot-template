package com.wz.example.template.config;

import com.wz.example.template.security.MyAuthenticationFailureHandler;
import com.wz.example.template.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    private String[] permitPaths = {"/", "/test/hello", "/user/login", "/login.html"};

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

//    默认是token保存在内存中，手动注入JdbcTokenRepositoryImpl，实现将token保存在数据库中，同时自动建表
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()                                 //关闭CSRF防护，不知道干嘛用的
                .formLogin()                                  //自定义登录页面
                .loginPage("/login.html")                     //登录页面路径设置
                .loginProcessingUrl("/user/login")            //定义登录用户名密码提交的接口路径
                .defaultSuccessUrl("/index.html")             //登录成功后，默认跳转路径
                .successHandler(myAuthenticationSuccessHandler)//前后端分离情况下，不需要返回静态资源，采用handler方式返回restful数据
                .failureHandler(myAuthenticationFailureHandler)//前后端分离情况下，返回json格式数据
                .failureForwardUrl("/login.html")              //登录失败后，转发forward到资源上
                .failureUrl("login.html")                      //登录失败后，重定向redirect到资源上
//                设置拒绝访问页面
                .and().exceptionHandling().accessDeniedPage("/403.html")

                .and().logout().logoutUrl("/user/logout")      //用户注销，设置退出接口
                .logoutSuccessUrl("/logout.html").permitAll()   //用户退出后的访问页面

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
