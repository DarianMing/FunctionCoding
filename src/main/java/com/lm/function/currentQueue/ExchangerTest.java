package com.lm.function.currentQueue;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExchangerTest {

    private static final Exchanger<String> exchanger = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() ->{
            try {
//                String A = "银行流水A";
//                exchanger.exchange(A);
                System.out.println(Thread.currentThread().getName() + exchanger.exchange("银行流水A"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.execute(() ->{
            try {
                System.out.println(Thread.currentThread().getName() + exchanger.exchange("银行流水B"));
//                String B = "银行流水B";
//                exchanger.exchange(B);
//                String A = exchanger.exchange("B");
//                System.out.println("A和B数据是否一致: " + A.equals(B) + " , A录入数据是：" + A + " , B录入数据是：" + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.shutdown();
    }

}
