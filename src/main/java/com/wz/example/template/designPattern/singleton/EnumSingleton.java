package com.wz.example.template.designPattern.singleton;

/**
 * 单例模式：枚举单例
 * 通过枚举机制实现的单例模式，因为Java虚拟机会保证枚举对象的唯一性，因此每一个枚举类型和定义的枚举变量在JVM中都是唯一的。
 * 既可以避免多线程同步问题；还可以防止通过反射和反序列化来重新创建新的对象
 *
 * 但是Kevin说有线程安全问题？
 *
 */
public enum EnumSingleton {
    INSTANCE;

    public void doSth() {
        System.out.println("EnumSingleton.INSTANCE.doSth()");
    }
}
