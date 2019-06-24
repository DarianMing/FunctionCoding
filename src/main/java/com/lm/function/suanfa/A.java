package com.lm.function.suanfa;

public class A {

    private static int func(int[] a, int[] b) {
        int len = a.length + 1;
        int[][] visitor = new int[len][len];
        for (int i = 2; i < len ; i += 2 ) {
            visitor[0][i] = visitor[0][i-2] + b[i-2] * b[i-1];
            visitor[i][0] = visitor[i-2][0] + a[i-2] * a[i-1];
        }
        for (int i = 1; i < len ; i++) {
            for (int j = 1; j < len ; j++) {
                if ((i + j) % 2 == 0) {
                    int top = (i - 2) >= 0 ? visitor[i-2][j] + a[i-2] * a[i-1] : Integer.MAX_VALUE;
                    int diagonal = visitor[i-1][j-1] + a[i-1] * b[j-1];
                    int left = (j - 2) >= 0 ? visitor[i][j-2] + b[j-2] * b[j-1] : Integer.MAX_VALUE;
                    visitor[i][j] = Integer.min(diagonal, Integer.min(top, left));
                }
            }
        }
//        int k = len - 1;
//        int v = len - 1;
//        while ((k >= 0) || (v >= 0)) {
//            if ((k-2) >= 0 && v >= 0 && (visitor[k][v] - a[k-2] * a[k-1]) == visitor[k-2][v]) {
//                System.out.print("a[" + k + "] * a[" + v + "] + ");
//                k -= 2;
//            } else if ((k - 1) >= 0 && (v - 1) >= 0 && (visitor[k][v] - a[k-1] * b[v-1]) == visitor[k-1][v-1]) {
//                System.out.print("a[" + (k-1) + "] * b[" + (v-1) + "] + ");
//                k -= 1; v -= 1;
//            } else if (k >= 0 && (v - 2) >= 0 && (visitor[k][v] - a[k] * b[v-2]) == visitor[k][v-2]) {
//                System.out.print("b[" + (v-2) + "] * b[" + (v-1) + "] + ");
//                v -= 2;
//            }
//        }
//        System.out.println();
        for (int i = 0; i < len ; i++) {
            for (int j = 0; j < len ; j++) {
                System.out.printf("%-4d " , visitor[i][j]);
            }
            System.out.println();
        }
        return visitor[len-1][len-1];
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,3,1,4};
        int[] b = new int[]{2,4,6,3};
        System.out.println(A.func(a,b));
    }



}
