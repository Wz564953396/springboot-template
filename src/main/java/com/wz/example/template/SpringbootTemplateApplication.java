package com.wz.example.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@MapperScan("com.wz.example.template.mapper")
@SpringBootApplication
//开启@Secured注解，开启@PreAuthorize注解
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringbootTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTemplateApplication.class, args);
    }

}
