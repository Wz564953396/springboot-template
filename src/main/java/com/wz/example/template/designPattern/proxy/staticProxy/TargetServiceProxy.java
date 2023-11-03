package com.wz.example.template.designPattern.proxy.staticProxy;

import com.wz.example.template.designPattern.proxy.ITargetService;
import com.wz.example.template.designPattern.proxy.TargetServiceImpl;
import org.springframework.stereotype.Component;

/**
 * 静态代理：
 *      优点：再不改变目标对象功能的前提下，能通过代理对象对目标功能扩展
 *      缺点：很明显，因为代理对象要和目标对象实现一样的接口，所以会有很多代理类
 *      一旦接口新增方法，目标对象和代理对象都要维护
 */
@Component
public class TargetServiceProxy implements ITargetService {

    private TargetServiceImpl targetService;


    @Override
    public void execute() {
        System.out.println("代理：功能增强");
        targetService.execute();
        System.out.println("代理结束");
    }

    public TargetServiceImpl getTargetService() {
        return targetService;
    }

    public void setTargetService(TargetServiceImpl targetService) {
        this.targetService = targetService;
    }
}
