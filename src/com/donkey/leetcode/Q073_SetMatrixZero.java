package com.donkey.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 首先想到的方案就是先遍历把0 （i,j）的位置，然后再处置
 */
public class Q073_SetMatrixZero {

    public void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();

        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }

        for(Integer r: row) {
            for (int j = 0; j < n; j++) {
                matrix[r][j] = 0;
            }
        }

        for(Integer c: col) {
            for (int i = 0; i < m; i++) {
                matrix[i][c] = 0;
            }
        }

    }

}
