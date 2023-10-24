package com.wz.example.template.designPattern.factory.abstractFactory;

import com.wz.example.template.designPattern.factory.dto.*;
import org.springframework.stereotype.Component;

@Component
public class ProductChain2Factory extends AbstractFactory{

    @Override
    public FruitDTO getFruit() {
        return new BananaDTO();
    }

    @Override
    public BrandDTO getBrand() {
        return new LvYe();
    }
}
