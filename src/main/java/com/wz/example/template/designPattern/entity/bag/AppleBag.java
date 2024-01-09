package com.wz.example.template.designPattern.entity.bag;

import com.kevin.entity.Bag;

/**
 * 苹果包装
 * Created by Kevin on 10/9 009.
 */
public class AppleBag implements Bag {
    @Override
    public void pack() {
        System.out.print("--苹果使用纸箱包装");
    }
}
