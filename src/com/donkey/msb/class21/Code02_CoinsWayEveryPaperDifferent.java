package com.donkey.msb.class21;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class Code02_CoinsWayEveryPaperDifferent {

    public static int way1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int aim) {
        if (aim < 0) {
            return 0;
        }

        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }

        int p1 = process(arr, index + 1, aim - arr[index]);
        int p2 = process(arr, index + 1, aim);

        return p1 + p2;
    }

    // 思路没错，aim的推到稍微恍神了一下
    public static int way2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                dp[index][rest] = rest - arr[index] < 0 ? dp[index + 1][rest] : dp[index + 1][rest] + dp[index + 1][rest - arr[index]];
            }
        }

        return dp[0][aim];
    }

    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = way1(arr, aim);
            int ans2 = way2(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
