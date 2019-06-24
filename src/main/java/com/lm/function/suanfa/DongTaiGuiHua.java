package com.lm.function.suanfa;

import java.util.Optional;

public class DongTaiGuiHua {

    private int maxW = Integer.MIN_VALUE;

    private int maxVal = Integer.MIN_VALUE;

    private int [] weight = {2,2,4,6,3};

    private int [] price = {3,4,8,9,6};

    private int n = 5;

    private int w = 9;

    private boolean[][] mem = new boolean[5][10];


    public void f1 (int i , int cw , int val) {
        if (cw == w || i == n) {
            if(val > maxVal) maxVal = val;
            return;
        }
        f1(i + 1 , cw , val);
        if (cw + weight[i] <= w) {
            f1(i+1 , cw + weight[i] , val + price[i]);
        }
    }

    public void f (int i , int cw) {
        if (cw == w || i == n) {
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return;
        mem[i][cw] = true;
        f(i + 1 , cw);
        if (cw + weight[i] <= w) {
            f(i + 1 , cw + weight[i]);
        }
    }

    public int func () {
        boolean[][] status = new boolean[n][w+1];
        status[0][0] = true;
        status[0][weight[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if(status[i-1][j]) status[i][j] = true;
            }
            for (int j = 0; j <= w - weight[i]; j++) {
                if(status[i-1][j]) status[i][weight[i] + j] = true;
            }
        }
        for (int i = w; i >= 0; i--) {
            if (status[n-1][i]) return i;
        }
        return 0;
    }

    public int func1 () {
        int[][] status = new int[n][w+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                status[i][j] = -1;
            }
        }
        status[0][0] = 0;
        status[0][weight[0]] = price[0];
        for (int i = 1; i < n ; i++) {
            for (int j = 0; j <= w ; j++) {
                if (status[i-1][j] != -1) status[i][j] = status[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; j++) {
                if (status[i-1][j] >= 0) {
                    int v = status[i-1][j] + price[i];
                    if (v > status[i][j + weight[i]] ) {
                        status[i][j + weight[i]] = v;
                    }
                }
            }
        }
        int maxVal = -1;
        for (int i = 0; i <= w ; i++) {
            if(status[n-1][i] > maxVal) maxVal = status[n-1][i];
        }

        return maxVal;
    }

    public static void main(String[] args) {
        DongTaiGuiHua hua = new DongTaiGuiHua();
        hua.f(0,0);
        System.out.println(hua.maxW);
        System.out.println(hua.func());
        hua.f1(0,0,0);
        System.out.println(hua.maxVal);
        System.out.println(hua.func1());
    }

}
