package com.lm.function.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Calculate implements Runnable {

    //public static AtomicInteger i = new AtomicInteger(0);

    public static int i = 0;

    @Override
    public void run() {
        IntStream.range(0,10000).forEach(item -> i++);
    }

    public void test () {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        IntStream.range(0,100).forEach(i->{
            threads[i] = new Thread(new Calculate());
            threads[i].start();
//            try {
//                threads[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
        new Calculate().test();
        IntStream.range(0,100).forEach(i-> {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(i);
    }
}
