package com.lm.function.Thread;

import java.util.concurrent.TimeUnit;

public class Job implements Runnable {


    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始工作！");
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + "工作结束！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
