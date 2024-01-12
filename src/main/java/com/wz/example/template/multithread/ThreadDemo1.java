package com.wz.example.template.multithread;

public class ThreadDemo1 extends Thread {

    public static void main(String[] args) {
        ThreadDemo1 thread1 = new ThreadDemo1();
        ThreadDemo1 thread2 = new ThreadDemo1();
//        调run(), 只是调用了一个方法，并不是启动了线程
//        thread1.run();
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        System.out.println("ThreadDemo1....");
    }
}
