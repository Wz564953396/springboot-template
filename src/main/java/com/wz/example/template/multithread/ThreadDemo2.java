package com.wz.example.template.multithread;

public class ThreadDemo2 implements Runnable{


    public static void main(String[] args) {
        ThreadDemo2 run = new ThreadDemo2();
        Thread thread = new Thread(run);
        thread.start();
    }

    @Override
    public void run() {

    }
}
