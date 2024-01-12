package com.wz.example.template.multithread.ticket;

public class Main {
    public static Integer ticketCount = 100;

    public static Integer sellCount = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Window());
        Thread thread2 = new Thread(new Window());
        Thread thread3 = new Thread(new Window());
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
