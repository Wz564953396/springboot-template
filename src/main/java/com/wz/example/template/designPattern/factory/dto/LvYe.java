package com.wz.example.template.designPattern.factory.dto;

import lombok.Data;

@Data
public class LvYe extends BrandDTO {

    private String brandName = "绿叶水果";

    private Integer rank = 2;

    @Override
    public void sophistry() {
        System.out.println("LvYeShuiGuo()...");
    }
}
