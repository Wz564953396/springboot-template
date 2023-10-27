package com.wz.example.template.designPattern.adapter;

public class AbstractAdapter implements Adapter {

    private Adaptee adaptee;

    @Override
    public void method1() {
        System.out.println("AbstractAdapter.method1()...");
        adaptee.method1();
    }

    @Override
    public void method2() {

    }

    public Adaptee getAdaptee() {
        return adaptee;
    }

    public void setAdaptee(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
}
