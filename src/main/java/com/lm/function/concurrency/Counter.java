package com.lm.function.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Counter {

    private int i = 0;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private void count () {
        i++;
    }

    private void safeCount () {
        for (;;){
            int i = atomicInteger.get();
            boolean b = atomicInteger.compareAndSet(i, ++i);
            if (b) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> threads = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        IntStream.range(0 , 100).forEach(i ->
                threads.add(new Thread(() ->
                        IntStream.range(0, 10000).forEach(j -> {
                            cas.count();
                            cas.safeCount();
        }))));
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 10000; j++) {
//                    cas.count();
//                    cas.safeCount();
//                }
//            });
//            threads.add(thread);
//        }
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }



}
