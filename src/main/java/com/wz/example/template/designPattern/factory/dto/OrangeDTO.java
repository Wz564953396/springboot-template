package com.wz.example.template.designPattern.factory.dto;

import java.math.BigDecimal;

public class OrangeDTO implements FruitDTO{


    @Override
    public Integer index() {
        return 3;
    }

    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(5.3);
    }

    @Override
    public void draw() {
        System.out.println("orange.draw()...");
    }
}
