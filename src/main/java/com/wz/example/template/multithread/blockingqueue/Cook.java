package com.wz.example.template.multithread.blockingqueue;

import lombok.SneakyThrows;

public class Cook extends Thread{

    private Integer num = 1;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(200);
            System.out.println(String.format("出餐！%s", String.format("饭%d", num)));
            ThreadDemo.blockingQueue.put(String.format("饭%d", num++));
        }
    }
}
