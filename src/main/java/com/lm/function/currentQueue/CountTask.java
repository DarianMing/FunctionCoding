package com.lm.function.currentQueue;

import lombok.AllArgsConstructor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

@AllArgsConstructor
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + " start = " + start + " end = " + end);
        int sum ;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            sum = IntStream.rangeClosed(start, end).sum();
        } else {
            int middle = (end + start)/2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            Integer left = leftTask.join();
            Integer right = rightTask.join();
            sum = left + right;
;        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.toString());
        CountTask countTask = new CountTask(1 , 10);
        ForkJoinTask<Integer> result = forkJoinPool.submit(countTask);
        System.out.println(forkJoinPool.toString());
        System.out.println(result.get());
        System.out.println(forkJoinPool.toString());
        System.out.println(Runtime.getRuntime().availableProcessors());
        int n = 5;
        n |= n >>> 1; n |= n >>> 2;  n |= n >>> 4;
        n |= n >>> 8; n |= n >>> 16;
        System.out.println(n);
        n = (n + 1) << 1;
        CompletableFuture.supplyAsync()
        System.out.println(n);
    }



    public static void int2(long i){
        String s="";
        while(i>0){
            if(i%2!=0)
                s="1"+s;
            else
                s="0"+s;
            i=i/2;
        }
        System.out.println(s);
    }

}
