package com.wz.example.template.controller;

import com.wz.example.template.model.resposne.RestResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/root")
//基于注解方式的角色控制，角色必须以"ROLE_"开头
@Secured("ROLE_root")
public class RootController {

    private String text = "周笛是个傻子";

    @GetMapping("/hello")
    public String hello(ModelAndView modelAndView) {
        modelAndView.addObject("value", "hello root security");
        return "hello root security";
    }

    @GetMapping("/getAuthentication")
    public RestResponse getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);

//        SecurityContextHolder.strategyName默认为SecurityContextHolder.MODE_THREADLOCAL，只有父线程携带用户信息
//        可通过启动参数-D spring.security.strategy=MODE_INHERITABLETHREADLOCAL 修改策略，实现子线程携带用户信息
//        也可以启动时通过代码
//        System.setProperty(SecurityContextHolder.SYSTEM_PROPERTY, SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//        实现。
        new Runnable() {
            @Override
            public void run() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                System.out.println("子线程：" + authentication);
            }
        }.run();
        return new RestResponse(authentication);
    }

    @GetMapping("/getSession")
    public HttpSession getSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return session;
    }
}
