package com.lm.function.concurrency;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class Unit1 {

    public static final long count = 1000000001;


    public static void concurrency () throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
            }
        });
        thread.start();
        int b = 0;
        for (int i = 0; i < count ; i++) {
            b--;
        }
        thread.join();
        long end = System.currentTimeMillis() - start;
        System.out.println("concurrency: " + end + " ms b=" + b);
    }

    public static void serial () {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (int i = 0; i < count ; i++) {
            b--;
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("serial: " + end + " ms b=" + b);
    }

    public static void main(String[] args) throws InterruptedException {
        Unit1.concurrency();
        Unit1.serial();
        Runnable runnable = ()-> System.out.println("lala");
        runnable.wait();
        System.out.println(Arrays.toString(runnable.getClass().getDeclaredMethods()));
    }
}
