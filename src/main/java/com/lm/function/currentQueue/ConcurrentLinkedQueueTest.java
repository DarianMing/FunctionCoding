package com.lm.function.currentQueue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

//https://www.cnblogs.com/linjiqin/archive/2013/05/30/3108188.html
public class ConcurrentLinkedQueueTest {

    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
    private static int count = 2; // 线程个数
    private static CountDownLatch downLatch = new CountDownLatch(count);
    //CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
    /**
     *  生产
     */
    public static void offer () {
        IntStream.range(0,100000).forEach(i-> queue.add(i));
    }

    /**
     *  消费
     */
    static class Poll implements Runnable {

        @Override
        public void run() {
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
            downLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ConcurrentLinkedQueueTest.offer();
        IntStream.range(0 , count).forEach(i-> executorService.submit(new Poll()));
        downLatch.await();//使得主线程(main)阻塞直到latch.countDown()为零才继续执行
        System.out.println("cost time " + (System.currentTimeMillis() - startTime) + "ms");
        executorService.shutdown();
    }


}
