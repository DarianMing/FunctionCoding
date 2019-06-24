package com.lm.function.lock;

import com.lm.function.Thread.SleepUtil;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class TwoLockTest {

    private static final Lock lock = new TwoLock();

    public static void main(String[] args) {

        templet(0 , 10 , i -> {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        });

        templet(10 , 20 , i -> {
            SleepUtil.second(1);
            System.out.println();
        });

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        Arrays.stream(threadInfos).forEach(threadInfo -> {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        });

    }

    private static void templet (int start , int end , IntConsumer consumer) {
        IntStream.range(start , end).forEach(consumer);
    }

    private static class Worker extends Thread{
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            try {
                SleepUtil.second(1);
                //System.out.println(Thread.currentThread().getName());
                SleepUtil.second(1);
            } finally {
                lock.unlock();
            }
        }
    }
}
