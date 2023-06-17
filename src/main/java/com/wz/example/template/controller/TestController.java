package com.wz.example.template.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder str = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headName = headerNames.nextElement();
            String header = request.getHeader(headName);
            str.append(headName + "\t" + header + "\n");
        }

        String jwt_token_string = request.getHeader("JWT_TOKEN");
        System.out.println(jwt_token_string);

        return str.toString();
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request);
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        System.out.println(wholeStr);
        String parameter = request.getParameter("1");
        System.out.println(response);
        return wholeStr;
    }

    public static void method1() {
//        存12个元素时会自动扩容
        HashMap<String, Object> map = new HashMap<>(16);
        for (int i = 0; i < 12; i++) {
            map.put(String.valueOf(i), i);
        }
        System.out.println(map);
        for (int i = 12; i < 16; i++) {
            map.put(String.valueOf(i), i);
        }
        System.out.println(map);
    }

//    正则表达式校验手机号


    public static void main(String[] args) {
        method1();
    }
}
