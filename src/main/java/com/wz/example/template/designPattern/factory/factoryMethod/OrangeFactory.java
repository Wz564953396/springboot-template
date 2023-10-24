package com.wz.example.template.designPattern.factory.factoryMethod;

import com.wz.example.template.designPattern.factory.dto.FruitDTO;
import com.wz.example.template.designPattern.factory.dto.OrangeDTO;
import org.springframework.stereotype.Component;

@Component
public class OrangeFactory implements FactoryMethodFactory {

    @Override
    public FruitDTO getFruit() {
        return new OrangeDTO();
    }
}
