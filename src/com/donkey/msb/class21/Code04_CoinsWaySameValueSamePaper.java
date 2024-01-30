package com.donkey.msb.class21;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code04_CoinsWaySameValueSamePaper {

    public static int way1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        int len = map.keySet().size();
        int[] value = new int[len];
        int[] num = new int[len];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            value[i] = entry.getKey();
            num[i] = entry.getValue();
            i++;
        }
        return process(value, num, 0, aim);
    }

    public static int process(int[] value, int[] num, int index, int aim) {
        if (index == value.length) {
            return aim == 0 ? 1 : 0;
        }

        int n = num[index];
        int v = value[index];

        int res = 0;
        for (int i = 0; i <= n && (aim - i * v) >= 0; i++) {
            res += process(value, num, index + 1, aim - i * v);
        }
        return res;
    }


    public static int way2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        int len = map.keySet().size();
        int[] value = new int[len];
        int[] num = new int[len];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            value[i] = entry.getKey();
            num[i] = entry.getValue();
            i++;
        }

        int[][] dp = new int[len + 1][aim + 1];

        dp[len][0] = 1;

        for (int index = len - 1; index >= 0; index--) {
            int n = num[index];
            int v = value[index];
            for (int rest = 0; rest < aim + 1; rest++) {
                int x = 0;
                for (int j = 0; j <= n && (rest - v * j) >= 0; j++) {
                    x += dp[index + 1][rest - v * j];
                }
                dp[index][rest] = x;
            }
        }

        return dp[0][aim];
    }

    public static int way3(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        int len = map.keySet().size();
        int[] value = new int[len];
        int[] num = new int[len];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            value[i] = entry.getKey();
            num[i] = entry.getValue();
            i++;
        }

        int[][] dp = new int[len + 1][aim + 1];

        dp[len][0] = 1;

        for (int index = len - 1; index >= 0; index--) {
            int n = num[index];
            int v = value[index];
            for (int rest = 0; rest < aim + 1; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if ((rest - v) >= 0) {
                    dp[index][rest] += dp[index][rest - v];
                }
                // 这种case太难想到了，这是在n不够的情况下
                if ((rest - (n + 1) * v) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - (n + 1) * v];
                }
            }
        }

        return dp[0][aim];
    }

    // 为了测试
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

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = way1(arr, aim);
            int ans2 = way2(arr, aim);
            int ans3 = way3(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }



}
