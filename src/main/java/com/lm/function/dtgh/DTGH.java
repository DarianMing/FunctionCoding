package com.lm.function.dtgh;

public class DTGH {

    public static int[][] arr = new int[4][4] ;

    public static int[][][] values = new int[3][3][];

    public static int min = Integer.MAX_VALUE;

    static {
        arr[0][0] = 1;
        arr[0][1] = 3;
        arr[0][2] = 5;
        arr[0][3] = 9;
        arr[1][0] = 2;
        arr[1][1] = 1;
        arr[1][2] = 3;
        arr[1][3] = 4;
        arr[2][0] = 5;
        arr[2][1] = 2;
        arr[2][2] = 6;
        arr[2][3] = 7;
        arr[3][0] = 6;
        arr[3][1] = 8;
        arr[3][2] = 4;
        arr[3][3] = 3;
    }

    public void fun () {
        int ret[][] = new int[4][4];
        ret[0][0] = 1;
        for (int i = 1; i < 4 ; i++) {
            ret[i][0] = ret[i-1][0] + arr[i][0];
            ret[0][i] = ret[0][i-1] + arr[0][i];
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4 ; j++) {
                ret[i][j] = Integer.min(ret[i-1][j] , ret[i][j-1]) + arr[i][j];
            }
        }
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                System.out.print(ret[i][j] + " ");
            }
            System.out.println();

        }
        System.out.println(ret[3][3]);
    }

    public void fun1 () {
        int ret[] = new int[4];
        ret[0] = 1;
        for (int i = 1; i < 4; i++) {
            ret[i] = ret[i-1] + arr[0][i];
        }
        for (int i = 1; i < 4 ; i++) {
            ret[0] = ret[0] + arr[i][0];
            for (int j = 1; j < 4; j++) {
                ret[j] = Integer.min(ret[j] , ret[j-1]) + arr[i][j];
            }
            for (int j = 0; j < 4; j++) {
                System.out.print(ret[j] + " ");
            }
            System.out.println();
        }
        System.out.println(ret[3]);
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
        //new DTGH().fun();
        new DTGH().fun1();

        int [][] a = new int[5][5];
        a[-1][0] = 1;
        System.out.println(a[-1][0]);
    }


}
