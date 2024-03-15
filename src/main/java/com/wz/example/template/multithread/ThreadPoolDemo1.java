package com.wz.example.template.multithread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.*;

public class ThreadPoolDemo1 {

    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(10);

    public void test1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        long i = 0;
        while (true) {
            executor.execute(new Task(i++));

            if (i > 100) {
                break;
            }
        }
    }

    /**
     * 固定数量的线程池
     */
    public void test2() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        long i = 0;
        while (true) {
            executor.execute(new Task(i++));

            if (i > 100) {
                break;
            }
        }
    }

    /**
     * 固定数量的线程池
     */
    public void test3() {
        ExecutorService executor = Executors.newCachedThreadPool();
        long i = 0;
        while (true) {
            executor.execute(new Task(i++));

            if (i > 100) {
                break;
            }
        }
    }

    class Task implements Runnable {

        private Long id;

        public Task(Long id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(String.format("%s  task is beginning: %d", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), id));
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(String.format("%s  task is finished: %d", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), id));
        }
    }
}
