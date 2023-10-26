package com.wz.example.template.designPattern.singleton;

/**
 * 单例模式：静态内部类   StaticInnerSingleton.getSingleton();
 *
 * 优点：外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化 staticInnerSingleton，故而不占内存
 *      即当 StaticInnerSingleton 第一次被加载时，并不需要去加载Inner，只有当 getSingleton()方法第一次被调用时，才会去初始化 staticInnerSingleton,
 *      第一次调用 getSingleton()方法会导致虚拟机加载Inner类
 *      这种方法不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化
 *
 */
public class StaticInnerSingleton {

    private StaticInnerSingleton() {
        /**
         * 解决了反射会破坏内部类单例模式的问题
         */
        if (Inner.staticInnerSingleton != null) {
            throw new RuntimeException("休想进行反射！");
        }
    }

    private static class Inner {
        private static StaticInnerSingleton staticInnerSingleton = new StaticInnerSingleton();

    }

    public static StaticInnerSingleton getSingleton() {
        return Inner.staticInnerSingleton;
    }
}
