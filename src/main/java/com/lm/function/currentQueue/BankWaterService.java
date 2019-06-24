package com.lm.function.currentQueue;

import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class BankWaterService implements Runnable {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4 , this);

    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String,Integer> sheet : concurrentHashMap.entrySet()) {
            result += sheet.getValue();
        }
        concurrentHashMap.put("result" , result);
        System.out.println(result);
    }

    private void count () {
        IntStream.range(0,4).forEach(i-> executor.execute(()->{
            concurrentHashMap.put(Thread.currentThread().getName() , 1);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }));
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
        System.out.println(bankWaterService.concurrentHashMap);
    }
}
