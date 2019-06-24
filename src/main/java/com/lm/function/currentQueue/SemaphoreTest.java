package com.lm.function.currentQueue;

import com.lm.function.Thread.SleepUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        IntStream.range(0 , THREAD_COUNT).forEach(i->{
            executor.execute(() ->{
                try {
                    s.acquire();
                    System.out.println(Thread.currentThread().getName() + "save data");
                    SleepUtil.second(1);
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        executor.shutdown();
    }




}
