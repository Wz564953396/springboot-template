package com.wz.example.template.designPattern.decorator;

public class Latte extends BeverageDecorator{

    private void preMake() {
        System.out.println("先热热杯");
    }

    @Override
    public String name() {
        return "Latte";
    }

    @Override
    public void make() {
        preMake();
        super.make();
    }
}
