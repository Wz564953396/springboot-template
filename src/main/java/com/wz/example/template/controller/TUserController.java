package com.wz.example.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zz_Wang
 * @since 2022-12-12
 */
@RestController
@RequestMapping("/tUser")
public class TUserController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello, spring security");
        return "hello, spring security";
    }
}
