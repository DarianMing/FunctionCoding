package com.lm.function.currentQueue;

import com.lm.function.Thread.SleepUtil;

import java.util.concurrent.CountDownLatch;

public class JoinCountDownLatchTest {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->{
            System.out.println(1);
            countDownLatch.countDown();
            System.out.println(2);
            countDownLatch.countDown();
        }).start();
    }

    private static void testThread() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            SleepUtil.second(5);
            System.out.println("parser1 finish");
        });
        Thread thread2 = new Thread(() -> {
            SleepUtil.second(5);
            System.out.println("parser2 finish");
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(" all parser finishn");
    }
}
