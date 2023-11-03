package com.wz.example.template.designPattern.proxy.dynamicProxy;

import com.wz.example.template.designPattern.proxy.ITargetService;
import com.wz.example.template.designPattern.proxy.TargetServiceImpl;


/**
 * 动态代理:
 *      这块分为 JDK 动态代理和 Cglib 动态代理
 *
 *
 */
public class TestDynamicProxy {
    public static void main(String[] args) {

        ITargetService targetService = new TargetServiceImpl();

        System.out.println("--------------------jdk---------------------");

        JdkProxyFactory proxyFactory = new JdkProxyFactory(targetService);
        ITargetService proxyInstance = (ITargetService) proxyFactory.getProxyInstance();

        proxyInstance.execute();

        System.out.println("--------------------cjlib-----------------------");

        CjlibProxyFactory cjlibProxyFactory = new CjlibProxyFactory(targetService);
        TargetServiceImpl proxyInstance2 = (TargetServiceImpl) cjlibProxyFactory.getProxyInstance();

        proxyInstance2.execute();

    }
}
