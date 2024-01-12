package com.wz.example.template.multithread.blockingqueue;


import lombok.SneakyThrows;

public class Foodie extends Thread {

    private Integer num = 0;

    @SneakyThrows
    @Override
    public void run() {
        while (num < 20) {
            System.out.println(String.format("吃！%s", ThreadDemo.blockingQueue.take()));
            Thread.sleep(1000);
            num++;
        }
        interrupt();
    }
}
