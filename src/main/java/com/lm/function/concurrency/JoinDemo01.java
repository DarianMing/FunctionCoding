package com.lm.function.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

//https://blog.csdn.net/baidu_41861751/article/details/83997641
//https://www.cnblogs.com/lcplcpjava/p/6896904.html
public class JoinDemo01 extends Thread {

    @Override
    public void run() {
        IntStream.range(0,10).forEach(i->{
            System.out.println("join..........." + i);
        });
    }

    public static void main(String[] args) {
        JoinDemo01 joinDemo01 = new JoinDemo01();
        Thread thread = new Thread(joinDemo01);
        thread.start();
        IntStream.range(0,10).forEach(i->{
            if (i == 5) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main..............." + i);
        });
    }

}
