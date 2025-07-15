package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *  边界条件！边界条件！
 */
public class Q054_SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int a = 0;
        int b = 0;
        int c = m - 1;
        int d = n - 1;
        // 这里的边界条件少了点
        while (a <= c && b <= d) {
            fill(matrix, a++, b++, c--, d--, ans);
        }
        return ans;
    }

    // (a,b) (a,d) (c,d) (c,b)
    public void fill(int[][] matrix, int a, int b, int c, int d, List<Integer> list) {
        // 这里的边界条件也少了点！
        if (a == c) {
            for (int i = b; i <= d; i++) {
                list.add(matrix[a][i]);
            }
        } else if (b == d) {
            for (int i = a; i <= c; i++) {
                list.add(matrix[i][b]);
            }
        } else {
            for (int i = b; i < d; i++) {
                int x = matrix[a][i];
                list.add(x);
            }
            for (int i = a; i < c; i++) {
                int x = matrix[i][d];
                list.add(x);
            }
            for (int i = d; i > b; i--) {
                int x = matrix[c][i];
                list.add(x);
            }
            for (int i = c; i > a; i--) {
                int x = matrix[i][b];
                list.add(x);
            }
        }
    }
}
