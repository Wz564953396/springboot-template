package com.wz.example.template.designPattern.entity.bag;

import com.kevin.entity.Bag;

/**
 * 桔子包装
 * Created by Kevin on 10/9 009.
 */
public class OrangeBag implements Bag {
    @Override
    public void pack() {
        System.out.print("--桔子使用网兜包装");
    }
}
