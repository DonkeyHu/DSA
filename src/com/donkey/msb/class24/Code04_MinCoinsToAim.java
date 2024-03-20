package com.donkey.msb.class24;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：
 * 因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 */
public class Code04_MinCoinsToAim {

    public static int way1(int[] arr, int aim) {
        return process(0, aim, arr);
    }

    public static int process(int index, int rest, int[] arr) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int A = process(index + 1, rest, arr);
        int B = process(index + 1, rest - arr[index], arr);
        if (B != Integer.MAX_VALUE) {
            B++;
        }
        return Math.min(A, B);
    }

    public static int way2(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int A = dp[index + 1][rest];
                int B = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
                if (B != Integer.MAX_VALUE) {
                    B++;
                }
                dp[index][rest] = Math.min(A, B);
            }
        }
        return dp[0][aim];
    }

    // 换一种思路，把arr转换成面值数组value[]以及张数数组count[]
    public static int way3(int[] arr, int aim) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int coin : arr) {
            if (map.containsKey(coin)) {
                map.put(coin, map.get(coin) + 1);
            } else {
                map.put(coin, 1);
            }
        }
        int size = map.keySet().size();
        int[] value = new int[size];
        int[] count = new int[size];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            value[i] = entry.getKey();
            count[i] = entry.getValue();
            i++;
        }
        return process2(0, aim, value, count);
    }

    public static int process2(int index, int rest, int[] value, int[] count) {
        if (index == value.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        int res = Integer.MAX_VALUE;
        for (int zhang = 0; zhang <= count[index]; zhang++) {
            int tmp = process2(index + 1, rest - zhang * value[index], value, count);
            if (tmp != Integer.MAX_VALUE) {
                res = Math.min(res, tmp + zhang);
            }
        }
        return res;
    }


    public static int way4(int[] arr, int aim) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int coin : arr) {
            if (map.containsKey(coin)) {
                map.put(coin, map.get(coin) + 1);
            } else {
                map.put(coin, 1);
            }
        }
        int size = map.keySet().size();
        int[] value = new int[size];
        int[] count = new int[size];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            value[i] = entry.getKey();
            count[i] = entry.getValue();
            i++;
        }
        int N = value.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 尝试看枚举，做斜率优化
                int res = Integer.MAX_VALUE;
                for (int zhang = 0; zhang <= count[index]; zhang++) {
                    int tmp = rest >= zhang * value[index] ? dp[index + 1][rest - zhang * value[index]] : Integer.MAX_VALUE;
                    if (tmp != Integer.MAX_VALUE) {
                        res = Math.min(res, tmp + zhang);
                    }
                }
                dp[index][rest] = res;
            }
        }
        return dp[0][aim];
    }

    // 动态规划+窗口最小值
    public static int way5(int[] arr, int aim) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int coin : arr) {
            if (map.containsKey(coin)) {
                map.put(coin, map.get(coin) + 1);
            } else {
                map.put(coin, 1);
            }
        }
        int size = map.keySet().size();
        int[] value = new int[size];
        int[] count = new int[size];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            value[i] = entry.getKey();
            count[i] = entry.getValue();
            i++;
        }
        int N = value.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }

        // 脑海填格子得有画面，这次是通过间隔的跳跳跳去填格子
        for (int index = N - 1; index >= 0; index--) {
            // 太容易出错了，是aim+1不是aim
            for (int mod = 0; mod < Math.min(value[index], aim + 1); mod++) {
                LinkedList<Integer> window = new LinkedList<>();
                dp[index][mod] = dp[index + 1][mod];
                window.add(mod);

                for (int rest = mod + value[index]; rest <= aim; rest += value[index]) {
                    // 这个window的意义还是表示方格子即为张数，但是保存的时候却是保存rest的值, 存在映射关系
                    while (!window.isEmpty() && ((dp[index + 1][window.peekLast()] == Integer.MAX_VALUE) ||
                            (dp[index + 1][window.peekLast()] + compensate(window.peekLast(), rest, value[index])) >= dp[index + 1][rest])) {
                        window.pollLast();
                    }
                    window.addLast(rest);

                    // 面值对应的张数有限制
                    int overdue = rest - value[index] * (count[index] + 1);
                    if (window.peekFirst() == overdue) {
                        window.pollFirst();
                    }

                    dp[index][rest] = dp[index + 1][window.peekFirst()] + compensate(window.peekFirst(), rest, value[index]);
                }
            }
        }
        return dp[0][aim];
    }


    public static int compensate(int pre, int cur, int value) {
        return (cur - pre) / value;
    }

    public static int[] randomArray(int N, int maxValue) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

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
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = way1(arr, aim);
            int ans2 = way2(arr, aim);
            int ans3 = way3(arr, aim);
            int ans4 = way4(arr, aim);
            int ans5 = way5(arr, aim);
            if (ans1 != ans2 || ans1 != ans3 || ans3 != ans4 || ans4 != ans5) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                System.out.println(ans5);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}
