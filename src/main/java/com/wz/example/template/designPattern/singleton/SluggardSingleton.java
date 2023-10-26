package com.wz.example.template.designPattern.singleton;

/**
 * 单例模式：懒汉式
 */
public class SluggardSingleton {


    private volatile static SluggardSingleton singleton = null;


    /**
     * 不入流的懒汉式单例：
     * 1.线程不安全，多个线程同时调用getSingleton()时，有可能singleton == null 都返回 true
     * @return
     */
    private SluggardSingleton getSingleton() {
        if (singleton == null) {
            singleton = new SluggardSingleton();
        }
        return singleton;
    }

    /**
     * 双重检查锁定模式的懒汉式单例：
     * https://blog.csdn.net/qq_44842835/article/details/132166785
     * volatile的特性
     * （1）可见性：指对共享资源的修改对其他线程或进程是可见的。也就是volatile关键字修饰的变量被修改后会从工作内存实时反馈到主内存，其他线程获取该变量时会直接读主内存。
     * （2）有序性：指对共享资源的操作按照一定的顺序进行，不会出现乱序的情况。volatile通过禁止 指令重排（下面会有示例说明）原理来保证有序性
     * @return
     */
    private SluggardSingleton getSingleton2() {
        if (singleton == null) {
            synchronized (SluggardSingleton.class) {
                if (singleton == null) {
                    singleton = new SluggardSingleton();
                }
            }
        }
        return singleton;
    }
}
