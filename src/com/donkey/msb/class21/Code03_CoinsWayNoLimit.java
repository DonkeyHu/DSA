package com.donkey.msb.class21;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code03_CoinsWayNoLimit {

    public static int way1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        int res = 0;
        for (int num = 0; num * arr[index] <= rest; num++) {
            res += process(arr, index + 1, rest - num * arr[index]);
        }
        return res;
    }


    public static int way2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int N = arr.length;

        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int res = 0;
                for (int num = 0; num * arr[index] <= rest; num++) {
                    res += dp[index + 1][rest - num * arr[index]];
                }
                dp[index][rest] = res;
            }
        }

        return dp[0][aim];
    }


    public static int way3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }

        int N = arr.length;

        int[] dp = new int[aim + 1];
        dp[0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int tmp = dp[rest];
                dp[rest] = rest - arr[index] >= 0 ? tmp + dp[rest - arr[index]] : tmp;
            }
        }

        return dp[aim];
    }


}
