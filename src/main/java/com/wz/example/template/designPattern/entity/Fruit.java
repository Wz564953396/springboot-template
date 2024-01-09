package com.wz.example.template.designPattern.entity;

import com.kevin.action.visit.Visit;

/**
 * 水果接口
 */
public interface Fruit {

    int price();

    void draw();

    int accept(Visit visit);

}
