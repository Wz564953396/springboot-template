package com.wz.example.template.multithread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

public class ThreadDemo {

    public static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);


    public static void main(String[] args) {
        Cook cookThread = new Cook();
        Foodie foodieThread = new Foodie();
        cookThread.start();
        foodieThread.start();
    }
}
