package com.lm.function.concurrency;

import com.lm.function.Thread.SleepUtil;
import com.lm.function.entity.Msg;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicStampedReference;

//http://www.sohu.com/a/302585786_661203
public class Task implements Runnable {

    volatile boolean running = true;

    volatile int a = 1;

    static long test = 1;

    static final Msg m = new Msg();

    long i = 0;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始执行！");
        while (running) {
            test = i;
            i++;
        }
        System.out.println(Thread.currentThread().getName() + "执行结束！");
    }

    public void setTest (int i) {
        test = i;
    }

    public long getTest () {
        return test;
    }

    public static void main(String[] args) {

    }

    private static void Test() {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Task task = new Task();
        Thread thread = new Thread(task);
        thread.setName("task1");
        thread.start();
        Task task1 = new Task();
        Thread thread1 = new Thread(task1);
        thread1.setName("task2");
        thread1.start();
        task.a = 2;
        task1.a = 3;
        //SleepUtil.second(1);
//        task.setTest(2);
//        task1.setTest(3);
        task.running = false;
        System.out.println(task.i);
        System.out.println("线程终止");
        System.out.println(task.running);
        System.out.println(task1.running);
        System.out.println(task.a);
        System.out.println(task1.a);
        System.out.println(task.getTest());
        System.out.println(task1.getTest());
        System.out.println(Task.test);
    }
}

/**
 static区别于普通变量在多线程里面仅仅是提供了可见，但是不能保证数据的更新(相对于普通变量只是提供了共享，但其实是不可见)，
 而voilate是可以保证一个变量在线程间通信并且实时更新，我之前一直以为static可以同步更新数据，突然明白了，
 线程运行使用的是单独的内存区，static是提供了共享，但没有更新线程内存中的数据
 */