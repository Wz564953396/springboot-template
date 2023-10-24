package com.wz.example.template.designPattern.factory.abstractFactory;

import com.wz.example.template.designPattern.factory.dto.AppleDTO;
import com.wz.example.template.designPattern.factory.dto.BaiXiangYuan;
import com.wz.example.template.designPattern.factory.dto.BrandDTO;
import com.wz.example.template.designPattern.factory.dto.FruitDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductChain1Factory extends AbstractFactory{

    @Override
    public FruitDTO getFruit() {
        return new AppleDTO();
    }

    @Override
    public BrandDTO getBrand() {
        return new BaiXiangYuan();
    }
}
