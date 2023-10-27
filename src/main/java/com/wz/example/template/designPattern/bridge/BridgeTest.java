package com.wz.example.template.designPattern.bridge;

import com.wz.example.template.designPattern.bridge.abstraction.Colour;
import com.wz.example.template.designPattern.bridge.abstraction.Red;
import com.wz.example.template.designPattern.bridge.implementor.Bag;
import com.wz.example.template.designPattern.bridge.implementor.HandBag;

/**
 * 桥接模式：
 * 将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度。
 *
 * 桥接模式通常适用于以下场景：
 *  当一个类存在两个独立变化的维度，且这两个维度都需要进行扩展时。
 *  当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时。
 *  当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。
 *
 */
public class BridgeTest {

    public static void main(String[] args) {
        Colour red = new Red();
        Bag bag = new HandBag();

        bag.setColour(red);

        System.out.println(bag);
    }
}
