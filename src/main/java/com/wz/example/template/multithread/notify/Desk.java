package com.wz.example.template.multithread.notify;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Desk extends Thread{

    public static Lock lock = new ReentrantLock();
    public static Integer count = 10;
    public static Integer foodFlag = 0;

    @Override
    public void run() {
    }
}
