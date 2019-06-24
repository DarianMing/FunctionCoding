package com.lm.function.unit9;

import org.springframework.lang.NonNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionOps9 {

    public static int head (IntStream numbers) {
        return numbers.findFirst().getAsInt();
    }

    public static IntStream tail (IntStream numbers) {
        return numbers.skip(1);
    }

    public static IntStream numbers () {
        return IntStream.iterate( 2 , n -> n+1);
    }


    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1,2,3");
//        Optional<Object> o = Optional.ofNullable(null);
//        Optional.empty();
//        list.sort(Comparator.naturalOrder());
        List<String> testList = new ArrayList<>();
        testList.add("1杨");
        testList.add("1李");
        testList.add("1王");
        testList.add("1张");
        testList.add("2杨");
        testList.add("2孙");
        testList.add("2赵");
        testList.removeIf(test->test.startsWith("1"));
        System.out.println(testList);
        testList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        AtomicInteger a = new AtomicInteger(10);
        System.out.println(a.accumulateAndGet(20,Integer::min));
        int[] ints = new int[10];
        Arrays.setAll(ints , i ->i*2);
        Arrays.stream(ints).forEach(i -> System.out.println(i));
        int[] ints1 = new int[10];
        Arrays.fill(ints1 , 1);
        Arrays.parallelPrefix(ints1 , (c,b)-> c + b);

    }

}
