package com.wz.example.template.multithread.ticket;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

public class Window implements Runnable{
    @SneakyThrows
    @Override
    public void run() {
        synchronized (Main.ticketCount) {
            while (Main.ticketCount > 0) {
                Thread.sleep(100);
                Main.ticketCount--;
                Main.sellCount++;
                System.out.println(String.format("窗口[%s]卖出了第%d张票，剩余%d张", Thread.currentThread(), Main.sellCount, Main.ticketCount));
            }
        }
    }
}
