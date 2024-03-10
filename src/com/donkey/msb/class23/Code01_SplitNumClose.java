package com.donkey.msb.class23;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class Code01_SplitNumClose {

    public static int way1(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        return process(0, sum, arr);
    }

    public static int process(int index, int compare, int[] arr) {
        if (index == arr.length) {
            return 0;
        }
        int A = process(index + 1, compare, arr);
        int B = 0;
        if (compare >= arr[index]) {
            B = arr[index] + process(index + 1, compare - arr[index], arr);
        }
        return Math.max(A, B);
    }

    public static int way2(int arr[]) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];

        for (int index = N - 1; index >= 0; index--) {
            for (int compare = 0; compare <= sum; compare++) {
                int A = dp[index + 1][compare];
                int B = 0;
                if (compare >= arr[index]) {
                    B = arr[index] + dp[index + 1][compare - arr[index]];
                }
                // 傻逼了这里都忘记改
                dp[index][compare] = Math.max(A, B);
            }
        }

        return dp[0][sum];
    }


    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = way1(arr);
            int ans2 = way2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
