package com.lm.function.unit11;

import org.springframework.util.StopWatch;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Shop {

    public String product;

    public Shop (String product) {
        this.product = product;
    }

    public static void delay () {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double calculatePrice (String product) {
        delay();
        throw new RuntimeException("lala");
        //return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public double getPrice (String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync (String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
//        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
//        new Thread( () -> {
//            try {
//                double price = calculatePrice(product);
//                completableFuture.complete(price);
//            }catch (Exception ex) {
//                completableFuture.completeExceptionally(ex);
//            }
//        }).start();
//        return completableFuture;
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Shop shop = new Shop("Debt");
        Future<Double> future = shop.getPriceAsync("my favorite shop");
        IntStream.rangeClosed(1,20).forEach(i -> System.out.println("main" + i));
        try {
            Double aDouble = future.get();
            System.out.println("price is " + aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        stopWatch.prettyPrint();
    }


}
