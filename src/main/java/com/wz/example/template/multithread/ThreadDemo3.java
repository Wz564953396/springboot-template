package com.wz.example.template.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo3 implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadDemo3 call = new ThreadDemo3();
        FutureTask<String> futureTask = new FutureTask<>(call);
        Thread thread = new Thread(futureTask);
        thread.start();
        String result = futureTask.get();
        System.out.println(result);
    }

    @Override
    public String call() throws Exception {
        return String.valueOf(Math.random());
    }
}
