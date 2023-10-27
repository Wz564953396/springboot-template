package com.wz.example.template.designPattern.adapter;

public class AdapterTest {
    public static void main(String[] args) {

        Adaptee adaptee = new Adaptee();

        AbstractAdapter adapter = new AbstractAdapter();
        adapter.setAdaptee(adaptee);

        adapter.method1();

    }
}
