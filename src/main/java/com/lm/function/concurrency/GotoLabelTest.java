package com.lm.function.concurrency;

//https://www.cnblogs.com/zouqf/p/9881968.html
public class GotoLabelTest {

    public static void main(String[] args) {
        int i = 0;
        label1: while (true) {
            System.out.println("----------------外层循环开始----------------");
            while (true) {
                i++;
                System.out.println("i=" + i);
                if (i == 1) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("continue label1");
                    continue label1;
                }
                if (i == 5) {
                    System.out.println("break");
                    break;
                }
                if (i == 7) {
                    System.out.println("break label1");
                    break label1;
                }
                System.out.println("----------------内层循环结束----------------");
            }
            System.out.println("----------------外层循环结束----------------");
        }
        System.out.println("End");
    }

}
