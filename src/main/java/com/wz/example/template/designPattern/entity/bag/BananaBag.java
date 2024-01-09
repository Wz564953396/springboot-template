package com.wz.example.template.designPattern.entity.bag;

import com.kevin.entity.Bag;

/**
 * 香蕉包装
 * Created by Kevin on 10/9 009.
 */
public class BananaBag implements Bag {
    @Override
    public void pack() {
        System.out.print("--香蕉使用竹萝包装");
    }
}
