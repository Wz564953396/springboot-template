package com.wz.example.template.designPattern.proxy.dynamicProxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * Cglib（Code Generation Library）动态代理
 *      静态代理和 JDK 动态代理都要求目标对象实现一个接口，但是有时候目标对象只是一个单独对象，并没有实现任何接口，这种情况就可以使用目标对象的子类来实现代理，这就是 Cglib代理。
 *
 *      Cglib 代理也叫子类代理，它是通过在内存中构建一个子类对象从而实现对目标对象的扩展。
 *
 *      Cglib 是一个强大的高性能的代码生成包，可以在运行期扩展 Java 类与实现 Java 接口，它广泛的被许多 AOP 框架使用，例如 Spring AOP，实现方法拦截。
 *
 *      在 AOP 编程中如何选择使用哪种动态代理呢？
 *
 *      目标对象实现接口，利用JDK动态代理
 *      目标对象不需要实现接口，用Cglib实现代理
 *      Cglib 包底层是通过字节码处理框架 ASM 来转换字节并生成新的类
 */
public class CjlibProxyFactory implements MethodInterceptor {

    //维护一个目标对象
    private Object target;

    //构造器，传入一个被代理的对象
    public CjlibProxyFactory(Object target) {
        this.target = target;
    }

    //返回一个代理对象:  是 target 对象的代理对象
    public Object getProxyInstance() {
        //1. 创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2. 设置父类
        enhancer.setSuperclass(target.getClass());
        //3. 设置回调函数
        enhancer.setCallback(this);
        //4. 创建子类对象，即代理对象
        return enhancer.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib代理模式 ~~ 开始");
        Object returnVal = method.invoke(target, objects);
        System.out.println("Cglib代理模式 ~~ 提交");
        return returnVal;
    }
}
