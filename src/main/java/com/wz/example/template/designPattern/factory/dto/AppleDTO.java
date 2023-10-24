package com.wz.example.template.designPattern.factory.dto;

import java.math.BigDecimal;

public class AppleDTO implements FruitDTO{


    @Override
    public Integer index() {
        return 1;
    }

    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(5.1);
    }

    @Override
    public void draw() {
        System.out.println("apple.draw()...");
    }
}
