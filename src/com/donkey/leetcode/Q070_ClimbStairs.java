package com.donkey.leetcode;

public class Q070_ClimbStairs {

    public static int climbStairs(int n) {
        return process(n);
    }

    public static int process(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        return process(n - 1) + process(n - 2);
    }

    public static int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(climbStairs2(4));
    }
}
