package com.lm.function.unit3;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionOps {

    private static List<Dish> menu ;

    static {
        menu = Arrays.asList(
                new Dish("pork" , false , 800 , Dish.Type.MEAT),
                new Dish("beef" , false , 700 , Dish.Type.MEAT),
                new Dish("chicken" , false , 400 , Dish.Type.MEAT),
                new Dish("french fries" , true , 530 , Dish.Type.OTHER),
                new Dish("rice" , true , 350 , Dish.Type.OTHER),
                new Dish("season fruit" , true , 120 , Dish.Type.OTHER),
                new Dish("pizza" , true , 550 , Dish.Type.OTHER),
                new Dish("prawns" , false , 300 , Dish.Type.FISH),
                new Dish("salmon" , false , 450 , Dish.Type.FISH)
        );
    }

    public static void main(String[] args) {
        List<String> threeHighCalories = menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                   return d.getCalories() > 300;
                }).map(d ->{
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                }).limit(3).collect(Collectors.toList());
        System.out.println(threeHighCalories);

        //用流来筛选前两道荤菜
        menu.stream().filter(d -> d.getType() != Dish.Type.OTHER).limit(2).forEach(System.out::println);

        //每道菜的名称长度
        menu.stream().map(d -> d.getName().length()).forEach(System.out::print);
        menu.stream().map(Dish::getName).map(String::length).forEach(System.out::print);

        List<String> wordList = Arrays.asList("hello", "world");
        List<String[]> collect = wordList.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());
        List<Stream<String>> collect1 = wordList.stream().map(word -> word.split("")).map(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect1);
        wordList.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);

        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        List<int[]> collect2 = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        System.out.println(collect2);

        //判断菜单中是否有素食可以选择
        System.out.println(menu.stream().anyMatch(Dish::isVegetarian));

        List<Integer> list = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        list.stream().filter(i -> i % 2 ==0)
                     .distinct()
                     .forEach(System.out::println);

        //热量最大值
        Optional<Integer> reduce = menu.stream().reduce(BinaryOperator.maxBy(Comparator.comparing(Dish::getCalories))).map(Dish::getCalories);
        Optional<Integer> reduce3 = menu.stream().map(Dish::getCalories).reduce(BinaryOperator.maxBy(Comparator.naturalOrder()));
        OptionalInt reduce1 = menu.stream().mapToInt(Dish::getCalories).reduce(Integer::max);
        Optional<Integer> reduce2 = menu.stream().map(Dish::getCalories).reduce(Integer::max);
        Optional<Dish> max = menu.stream().max(Comparator.comparing(Dish::getCalories));
        OptionalInt max1 = menu.stream().mapToInt(Dish::getCalories).max();
        Optional<Dish> collect3 = menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        IntSummaryStatistics collect4 = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        Integer collect5 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::max));
        System.out.println(reduce.get());
        System.out.println(reduce1.getAsInt());
        System.out.println(reduce2.get());
        System.out.println(max.get().getCalories());
        System.out.println(collect3.get().getCalories());
        System.out.println(reduce3.get());
        System.out.println(collect4);
        System.out.println(collect5);
        System.out.println(max1);

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        List<Integer> reduce4 = stream.reduce(new ArrayList<>(), (List<Integer> l, Integer e) -> {
            l.add(e);
            return l;//转换函数
        }, (List<Integer> l1, List<Integer> l2) -> {
            l1.addAll(l2);
            return l1;//累积函数
        });
        System.out.println(reduce4);

        //查找每个子组中热量最高的DISH
        Map<Dish.Type, Optional<Dish>> collect6 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType
                        , Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(collect6);

        Map<Dish.Type, Dish> collect7 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType
                        , Collectors.collectingAndThen(Collectors.maxBy
                                (Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(collect7);

        Map<Dish.Type, Dish> collect8 = menu.stream()
                .collect(Collectors.toMap(Dish::getType, Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(collect8);

        //对于每种类型的DISH，菜单中都有哪几种CaloricLevel
        Map<Dish.Type, Set<CaloricLevel>> collect9 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, Collectors.toSet())));
        System.out.println(collect9);

        //荤素分组
        Map<Boolean, List<Dish>> collect10 = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(collect10.get(true).toString());
        Runnable runnable = () -> System.out.println("qq");
        Supplier<String> s = () -> "s";
        s.get();
        boolean b = IntStream.range(2, 100).noneMatch(i -> 100 % i == 0);
    }


    private static void operationApple() {

        List<String> list  = Arrays.asList("a","b","A","B");
        list.sort(String::compareToIgnoreCase);
        Supplier<Apple> supplier = Apple::new;
        Apple apple = supplier.get();
        Comparator<Apple> comparing = Comparator.comparing(Apple::getWeight);
        Function<Integer , Apple> function = Apple::new;
        UnaryOperator<Apple> uo = x -> x.setWeight(x.getWeight() + 1);
        UnaryOperator<Apple> uo1 = x -> x.setWeight(x.getWeight() * 2);
        Function<Apple, Apple> appleAppleFunction = uo.andThen(uo1);
        Apple apply = appleAppleFunction.apply(function.apply(100));
        System.out.println(apply.getWeight());
    }


}
