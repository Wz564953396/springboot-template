package com.wz.example.template.designPattern.factory;

import com.wz.example.template.designPattern.factory.abstractFactory.AbstractFactory;
import com.wz.example.template.designPattern.factory.dto.BrandDTO;
import com.wz.example.template.designPattern.factory.dto.FruitDTO;
import com.wz.example.template.designPattern.factory.factoryMethod.FactoryMethodFactory;
import com.wz.example.template.designPattern.factory.simpleFactory.SimpleFactory;
import com.wz.example.template.resposne.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/designPattern/factory")
public class FactoryTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FactoryTestController.class);


    @Autowired
    private SimpleFactory simpleFactory;

    @Autowired
    @Qualifier("appleFactory")
    private FactoryMethodFactory factoryMethodFactory;

    @Autowired
    @Qualifier("productChain1Factory")
    private AbstractFactory abstractFactory;

    @GetMapping("/simpleFactory")
    public RestResponse simpleFactory(FruitCmd cmd) {
        System.out.println("simpleFactory...");
        FruitDTO fruit = simpleFactory.getFruit(cmd.getType());
        System.out.println(fruit.index() + "\t" + fruit.price());
        fruit.draw();
        return new RestResponse();
    }

    @GetMapping("/factoryMethod")
    public RestResponse factoryMethod() {
        System.out.println("factoryMethod...");
        FruitDTO fruit = factoryMethodFactory.getFruit();
        System.out.println(fruit.index() + "\t" + fruit.price());
        fruit.draw();
        return new RestResponse();
    }

    @GetMapping("/abstractFactory")
    public RestResponse abstractFactory() {
        System.out.println("abstractFactory...");
        FruitDTO fruit = abstractFactory.getFruit();
        BrandDTO brand = abstractFactory.getBrand();
        System.out.println(fruit.index() + "\t" + fruit.price() + "\t" + brand.getBrandName());
        fruit.draw();
        return new RestResponse();
    }

    @ExceptionHandler(RuntimeException.class)
    public RestResponse handleException(Exception e) {
        LOGGER.error(e.getMessage());
        return RestResponse.error("simpleFactory出错了~", e.getMessage());
    }
}
