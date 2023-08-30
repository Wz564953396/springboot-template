package com.wz.example.template.aop;

import org.springframework.stereotype.Service;

@Service
public class AopService {

    public void business() {
        System.out.println("service code is running...");
    }
}
