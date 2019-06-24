package com.lm.function.lock;

import com.lm.function.Thread.SleepUtil;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class MutexTest {

    private static final Lock lock = new Mutex();

    public static void main(String[] args) {
        templet(0 , 10 , i -> {
            WorkerMutex worker = new WorkerMutex();
            worker.setDaemon(true);
            worker.start();
        });

        templet(10 , 20 , i -> {
            SleepUtil.second(2);
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

    private static class WorkerMutex extends Thread{
        @Override
        public void run() {
            lock.lock();
            try {
                SleepUtil.second(1);
                System.out.println(Thread.currentThread().getName());
                SleepUtil.second(1);
            } finally {
                lock.unlock();
            }
        }
    }
}
