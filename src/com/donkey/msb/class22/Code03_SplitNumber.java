package com.donkey.msb.class22;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class Code03_SplitNumber {

    public static int way1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    // 第一个要裂开的数
    // 剩余要裂开的数
    // 如何去理解自然智慧
    public static int process(int first, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (first > rest) {
            return 0;
        }
        int ways = 0;
        for (int index = first; index <= rest; index++) {
            ways += process(index, rest - index);
        }
        return ways;
    }


    public static int way2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int index = n - 1; index >= 1; index--) {
            for (int rest = index + 1; rest <= n; rest++) {
                int ways = 0;
                for (int i = index; i <= rest; i++) {
                    ways += dp[i][rest - i];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public static int way3(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int index = n - 1; index >= 1; index--) {
            for (int rest = index + 1; rest <= n; rest++) {
                dp[index][rest] = dp[index + 1][rest] + dp[index][rest - index];
            }
        }
        return dp[1][n];
    }


    public static void main(String[] args) {
        int n = 4;
        System.out.println(way1(n));
        System.out.println(way2(n));
        System.out.println(way3(n));
    }

}
