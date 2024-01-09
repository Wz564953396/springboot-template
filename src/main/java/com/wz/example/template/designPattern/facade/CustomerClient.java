package com.wz.example.template.designPattern.facade;

/**
 * 外观模式：
 *      隐藏了系统的复杂性，并向客户端提供了一个可以访问系统的接口。
 */
public class CustomerClient {


    public static void main(String[] args){
        doOrderFacade();
    }

    public static void doOrderFacade(){
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.doOrder();
    }

    public static void doOrder(){

//        PickService pickService = new PickService();
//        PackService packService = new PackService();
//        SendService sendService = new SendService();
//
//        //采摘
//        System.out.println("--------------");
//        pickService.doPick();
//
//        //包装
//        System.out.println("--------------");
//        packService.doPack();
//        //快递
//        System.out.println("--------------");
//        sendService.doSend();

    }

}
