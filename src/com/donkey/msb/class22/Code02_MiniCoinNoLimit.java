package com.donkey.msb.class22;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class Code02_MiniCoinNoLimit {

    public static int way1(int[] arr, int aim) {
        return process1(0, aim, arr);
    }


    public static int process1(int index, int rest, int[] arr) {
        if (rest == 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int count = 0; count * arr[index] <= rest; count++) {
            int num = process1(index + 1, rest - count * arr[index], arr);
            if (num != Integer.MAX_VALUE) {
                ans = Math.min(ans, num + count);
            }
        }
        return ans;
    }

    public static int way2(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 0; rest <= aim; rest++) {
            dp[N][rest] = rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int count = 0; count * arr[index] <= rest; count++) {
                    int num = dp[index + 1][rest - count * arr[index]];
                    if (num != Integer.MAX_VALUE) {
                        ans = Math.min(ans, num + count);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    public static int way3(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 0; rest <= aim; rest++) {
            dp[N][rest] = rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 这里没有意识到加条件 && dp[index][rest - arr[index]] != Integer.MAX_VALUE
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    // 这里没有意识到 + 1
                    dp[index][rest] = Math.min(dp[index + 1][rest], dp[index][rest - arr[index]] + 1);
                } else {
                    dp[index][rest] = Math.min(dp[index + 1][rest], Integer.MAX_VALUE);
                }
            }
        }
        return dp[0][aim];
    }


    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = way1(arr, aim);
            int ans2 = way2(arr, aim);
            int ans3 = way3(arr, aim);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}
