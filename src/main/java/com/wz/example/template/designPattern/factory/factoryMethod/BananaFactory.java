package com.wz.example.template.designPattern.factory.factoryMethod;

import com.wz.example.template.designPattern.factory.dto.BananaDTO;
import com.wz.example.template.designPattern.factory.dto.FruitDTO;
import org.springframework.stereotype.Component;

@Component
public class BananaFactory implements FactoryMethodFactory {

    @Override
    public FruitDTO getFruit() {
        return new BananaDTO();
    }
}
