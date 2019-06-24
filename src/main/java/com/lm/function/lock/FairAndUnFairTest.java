package com.lm.function.lock;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class FairAndUnFairTest {

    private static ReentrantLock2 fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);

    public static void main(String[] args) {
        testLock(unfairLock);
    }

    private static void testLock (ReentrantLock2 lock) {
        templet(0 , 5 , i -> {
            Job job = new Job(lock);
            job.start();
        });
    }

    private static void templet (int start , int end , IntConsumer consumer) {
        IntStream.range(start , end).forEach(consumer);
    }

    private static class Job extends Thread {
        private ReentrantLock2 lock;

        public Job (ReentrantLock2 lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                Object[] objects = lock.getQueuedThreads().stream().map(Thread::getId).toArray();
                System.out.println("lock by " + Thread.currentThread().getId() + "    waiting by"
                        + Arrays.asList(objects));
            } finally {
                lock.unlock();
            }
            //SleepUtil.second(1);
            lock.lock();
            try {
                Object[] objects = lock.getQueuedThreads().stream().map(Thread::getId).toArray();
                System.out.println("lock by " + Thread.currentThread().getId() + "    waiting by"
                        + Arrays.asList(objects));
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2 (boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            List<Thread> threads = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(threads);
            return threads;
        }
    }
}
