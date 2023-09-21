package com.xinput.util;

import java.util.Arrays;

public class ArrayUtils {
    /**
     * 打印二维数组
     */
    public static void print(int[][] arrays) {
        for (int[] ints : arrays) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
    }

    /**
     * 创建二维数组
     */
    public static int[][] createArray(int m, int n) {
        int num = 1;
        int[][] array = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = num++;
            }
        }
        return array;
    }
}
