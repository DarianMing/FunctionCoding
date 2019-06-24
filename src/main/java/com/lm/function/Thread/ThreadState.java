package com.lm.function.Thread;

public class ThreadState {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                SleepUtil.second(100);
            }
        } , "TimeWaitingThread").start();

        new Thread(new Waiting() , "WaitingThread").start();
        new Thread(new Blokced() , "BlockedThread-1").start();
        new Thread(new Blokced() , "BlockedThread-2").start();
    }




    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blokced implements Runnable {

        @Override
        public void run() {
            synchronized (Blokced.class) {
                while (true) {
                    SleepUtil.second(100);
                }
            }
        }
    }
}
