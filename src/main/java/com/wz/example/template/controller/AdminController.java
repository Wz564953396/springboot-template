package com.wz.example.template.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
//@Secured({"root", "admin"})
public class AdminController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('admin,root')")  //执行此方法之前进行权限校验
    @PostAuthorize("hasAnyAuthority('admin,root')") //执行此方法之后进行权限校验
    public String hello() {
        return "hello admin security";
    }
}
