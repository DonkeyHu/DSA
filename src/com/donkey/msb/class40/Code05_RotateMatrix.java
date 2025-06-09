package com.donkey.msb.class40;


import java.util.Arrays;

/**
 * 给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子
 * a  b  c		g  d  a
 * d  e  f		h  e  b
 * g  h  i		i  f  c
 */
public class Code05_RotateMatrix {

    public static char[][] process(char[][] arr) {
        int a = 0;
        int b = 0;
        int c = arr.length - 1;
        int d = arr[0].length - 1;
        while (a < c) {
            spiral(arr, a++, b++, c--, d--);
        }
        return arr;
    }

    public static void spiral(char[][] arr, int a, int b, int c, int d) {
        for (int i = 0; i < d - b; i++) {
            char tmp = arr[a][b + i];
            arr[a][b + i] = arr[c - i][b];
            arr[c - i][b] = arr[c][d - i];
            arr[c][d - i] = arr[a + i][d];
            arr[a + i][d] = tmp;
        }
    }

    public static void main(String[] args) {
        char[][] arr = {{'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'}};
        char[][] res = process(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
    }


}
