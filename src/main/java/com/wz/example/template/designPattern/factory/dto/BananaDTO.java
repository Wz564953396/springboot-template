package com.wz.example.template.designPattern.factory.dto;

import java.math.BigDecimal;

public class BananaDTO implements FruitDTO{


    @Override
    public Integer index() {
        return 2;
    }

    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(5.2);
    }

    @Override
    public void draw() {
        System.out.println("banana.draw()...");
    }
}
