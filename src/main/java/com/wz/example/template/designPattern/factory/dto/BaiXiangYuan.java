package com.wz.example.template.designPattern.factory.dto;

import lombok.Data;

@Data
public class BaiXiangYuan extends BrandDTO {

    private String brandName = "百香园";

    private Integer rank = 1;

    @Override
    public void sophistry() {
        System.out.println("BaiXiangYuan()...");
    }
}
