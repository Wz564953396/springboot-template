package com.wz.example.template.aop;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AopService {

    public void business() {
        System.out.println("service code is running...");
    }

    public static void main(String[] args) {
        String str = "abc123def456ghi789";
        Pattern pattern = Pattern.compile("\\d+"); // 使用正则表达式 \\d+ 匹配连续数字
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            String number = matcher.group();
            System.out.println(number);
        }
    }
}
