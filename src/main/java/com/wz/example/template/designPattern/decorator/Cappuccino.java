package com.wz.example.template.designPattern.decorator;


public class Cappuccino extends BeverageDecorator{

    public void preMake() {
        System.out.println("先加了点咖啡豆");
    }

    @Override
    public String name() {
        return "Cappuccino";
    }

    @Override
    public void make() {
        preMake();
        super.make();
    }
}
