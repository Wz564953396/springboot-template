package com.wz.example.template.designPattern.prototype;

public class Sheep implements Cloneable{
    private String name;
    private Integer age;
    private Integer sex;
    private Caregiver caregiver;

    public Sheep() {
    }

    public Sheep(String name, Integer age, Integer sex, Caregiver caregiver) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.caregiver = caregiver;
    }

    @Override
    protected Sheep clone() {
        try {
            return (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", caregiver=" + caregiver +
                '}';
    }
}
