package com.wz.example.template.designPattern.factory.simpleFactory;

import com.wz.example.template.designPattern.factory.dto.AppleDTO;
import com.wz.example.template.designPattern.factory.dto.BananaDTO;
import com.wz.example.template.designPattern.factory.dto.FruitDTO;
import com.wz.example.template.designPattern.factory.dto.OrangeDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class SimpleFactory {

    public static final int TYPE_APPLE = 1;//苹果
    public static final int TYPE_BANANA = 2;//香蕉
    public static final int TYPE_ORANGE = 3;//桔子

    public FruitDTO getFruit(Integer type) {
        if (ObjectUtils.isEmpty(type)) {
            return null;
        }
        if (TYPE_APPLE == type) {
            return new AppleDTO();
        } else if (TYPE_BANANA == type) {
            return new BananaDTO();
        } else if (TYPE_ORANGE == type) {
            return new OrangeDTO();
        }
        throw new RuntimeException();
    }
}
