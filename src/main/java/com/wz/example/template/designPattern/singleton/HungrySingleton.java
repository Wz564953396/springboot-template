package com.wz.example.template.designPattern.singleton;

/**
 * 单例模式：1.懒汉式
 */
public class HungrySingleton {

    /**
     * 利用static静态变量保证实例在JVM加载过程中就被创建，且只有一份
     */
    private static HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    /**
     * 私有化构造器，使外部无法构造新的实例
     */
    private HungrySingleton(){

    }

    /**
     * 外部只能通过这个方法获取单例
     * @return
     */
    public static HungrySingleton getHungrySingleton() {
        return HUNGRY_SINGLETON;
    }

}
