package com.wz.example.template.designPattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * JDK 动态代理
 * 代理对象，不需要实现接口，但是目标对象要实现接口，否则不能用 JDK动态代理
 * 代理对象的生成是利用 JDK 的 API，动态的在内存中构建代理对象
 */
public class JdkProxyFactory {

    private Object target;

    //  构造器，对target 进行初始化
    public JdkProxyFactory(Object target) {

        this.target = target;
    }

    //  给目标对象 生成一个代理对象
    public Object getProxyInstance() {


        /**
         *     1. ClassLoader loader ： 指定当前目标对象使用的类加载器, 获取加载器的方法固定
         *     2. Class<?>[] interfaces: 目标对象实现的接口类型，使用泛型方法确认类型
         *     3. InvocationHandler h : 事情处理，执行目标对象的方法时，会触发事情处理器方法, 会把当前执行的目标对象方法作为参数传入
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK代理开始~~");
                        //反射机制调用目标对象的方法
                        Object returnVal = method.invoke(target, args);
                        System.out.println("JDK代理提交");
                        return returnVal;
                    }
                });
    }
}
