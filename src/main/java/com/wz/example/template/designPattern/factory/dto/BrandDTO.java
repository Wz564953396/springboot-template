package com.wz.example.template.designPattern.factory.dto;

import lombok.Data;

@Data
public abstract class BrandDTO {

    private String brandName = "";

    private Integer rank = 0;

    public void sophistry() {
        System.out.println("sophistry()...");
    }
}
