package com.lm.function.unit11;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Function {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                IntStream.rangeClosed(1,20).forEach(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread" + i);
                });
                return "lala";
            }
        });
        try {
            future.get(1 , TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        IntStream.rangeClosed(1,20).forEach(i -> System.out.println("main" + i));
    }
}
