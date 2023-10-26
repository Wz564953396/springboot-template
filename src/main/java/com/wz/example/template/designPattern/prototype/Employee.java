package com.wz.example.template.designPattern.prototype;

public class Employee implements Cloneable{

    String name;
    int age;
    public Money m = new Money();

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


class Money {
    public double money = 100.2;
}