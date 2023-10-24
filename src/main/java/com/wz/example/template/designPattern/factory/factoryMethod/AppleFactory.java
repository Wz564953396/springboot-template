package com.wz.example.template.designPattern.factory.factoryMethod;

import com.wz.example.template.designPattern.factory.dto.AppleDTO;
import com.wz.example.template.designPattern.factory.dto.FruitDTO;
import org.springframework.stereotype.Component;

@Component
public class AppleFactory implements FactoryMethodFactory {

    @Override
    public FruitDTO getFruit() {
        return new AppleDTO();
    }
}
