package com.donkey.leetcode;

/**
 * 这题思路首先想到的是动态规划
 */
public class Q062_UniquePath {

    public static int uniquePaths(int m, int n) {
        return process(m - 1, n - 1);
    }


    public static int process(int m, int n) {
        if (m == 0 && n == 0) {
            return 1;
        }

        if (m < 0) {
            return 0;
        }
        if (n < 0) {
            return 0;
        }

        return process(m - 1, n) + process(m, n - 1);
    }

    public static int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths2(7, 3));
    }

}
