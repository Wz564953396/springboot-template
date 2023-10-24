package com.wz.example.template.designPattern.factory.abstractFactory;

import com.wz.example.template.designPattern.factory.dto.BrandDTO;
import com.wz.example.template.designPattern.factory.dto.FruitDTO;

/**
 * 保证客户端在一个过程中始终只使用同一个产品族中的对象
 */
public abstract class AbstractFactory {

    public abstract FruitDTO getFruit();
    public abstract BrandDTO getBrand();
}
