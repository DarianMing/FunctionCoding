//package com.lm.function.currentQueue;
//
//import com.lm.function.entity.Msg;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.Executor;
//import java.util.concurrent.LinkedTransferQueue;
//
//public class MsgQueueManager {
//
//    private final BlockingQueue<Msg> messageQueue;
//
//    private MsgQueueManager () {
//        Executor
//        messageQueue = new LinkedTransferQueue<>();
//    }
//
//    public void put (Msg msg) {
//        try {
//            messageQueue.put(msg);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    public Msg take () {
//        try {
//            return messageQueue.take();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//        return null;
//    }
//
//    static class DispatchMessageTask implements Runnable {
//        @Override
//        public void run() {
//
//        }
//
//
//    }
//
//
//}
