package com.wz.example.template.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/root")
//基于注解方式的角色控制，角色必须以"ROLE_"开头
@Secured("ROLE_root")
public class RootController {

    @GetMapping("/hello")
    public String hello(ModelAndView modelAndView) {
        modelAndView.addObject("value", "hello root security");
        return "hello root security";
    }

}
