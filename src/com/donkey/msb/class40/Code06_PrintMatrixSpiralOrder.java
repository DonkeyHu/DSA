package com.donkey.msb.class40;

import java.util.Arrays;

/**
 * 给定一个长方形矩阵matrix，实现转圈打印
 * a  b  c  d
 * e  f  g  h
 * i  j  k  L
 * 打印顺序：a b c d h L k j I e f g
 */
public class Code06_PrintMatrixSpiralOrder {

    public static void process(char[][] arr) {
        int a = 0;
        int b = 0;
        int c = arr.length - 1;
        int d = arr[0].length - 1;
        while (a <= c) {
            if (a != c) {
                printMatrix(arr, a++, b++, c--, d--);
            } else {
                for (int i = 0; i <= d - b; i++) {
                    System.out.print(arr[a][b + i] + " ");
                }
                a++;
                b++;
                c--;
                d--;
            }
        }
    }

    public static void printMatrix(char[][] arr, int a, int b, int c, int d) {
        for (int i = 0; i < d - b; i++) {
            System.out.print(arr[a][b + i] + " ");
        }
        for (int i = 0; i < c - a; i++) {
            System.out.print(arr[a + i][d] + " ");
        }
        for (int i = 0; i < d - b; i++) {
            System.out.print(arr[c][d - i] + " ");
        }
        for (int i = 0; i < c - a; i++) {
            System.out.print(arr[c - i][b] + " ");
        }
    }

    public static void main(String[] args) {
        char[][] arr = {{'a', 'b', 'c', 'd', '1'},
                {'e', 'f', 'g', 'h', '2'},
                {'i', 'j', 'k', 'L', '3'},
                {'8', '7', '6', '5', '4'},};
        process(arr);
    }

}
