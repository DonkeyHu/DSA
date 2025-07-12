package com.donkey.leetcode;

/**
 * 四个点之间怎么转呀？(i,j) (i,n),(m,n),(m,j)
 * 挪动一位之后这四个点关系如何变化联系的？ (i,j+1) (i+1,n) (m,n-1) (m-1,j)
 */
public class Q048_RotateImage {

    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int i = 0;
        int j = 0;
        int m = len - 1;
        int n = len - 1;

        while (i < m) {
            swap(matrix, i++, j++, m--, n--);
        }

    }

    public void swap(int[][] matrix, int i, int j, int m, int n) {
        for (int k = 0; k < m - i; k++) {
            int tmp = matrix[i][j + k];
            matrix[i][j + k] = matrix[m - k][j];
            matrix[m - k][j] = matrix[m][n - k];
            matrix[m][n - k] = matrix[i + k][n];
            matrix[i + k][n] = tmp;
        }
    }

}
