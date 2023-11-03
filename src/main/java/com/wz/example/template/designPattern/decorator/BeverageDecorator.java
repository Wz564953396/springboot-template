package com.wz.example.template.designPattern.decorator;

/**
 * 装饰者抽象类，装饰者与被装饰者遵循里氏替换原则
 *
 */
public abstract class BeverageDecorator extends Beverage{


    @Override
    public void make() {
        System.out.println(String.format("制作了一杯[%s]:", name()));
    }

}
