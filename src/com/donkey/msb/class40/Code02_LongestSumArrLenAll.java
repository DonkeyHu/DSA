package com.donkey.msb.class40;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 */
public class Code02_LongestSumArrLenAll {

    public static int fun(int[] arr, int k) {

        int[] preSum = new int[arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }
        int ans = 0;
        // 思路没错，但是时间复杂度高了，是否需要改变一个数据结构来存前缀和数组呢？
        for (int i = 0; i < arr.length; i++) {
            int x = preSum[i + 1] - k;
            for (int j = 0; j < i + 1; j++) {
                if (x == preSum[j]) {
                    ans = Math.max(ans, i + 1 - j);
                }
            }
        }
        return ans;
    }

    // 前缀和数组，我喜欢多定义一个key=0，value=0的值
    public static int fun2(int[] arr, int k) {
        int ans = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 0);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int x = sum - k;
            if (preSum.containsKey(x)) {
                ans = Math.max(ans, i + 1 - preSum.get(x));
            }
            if (!preSum.containsKey(sum)) {
                preSum.put(sum, i + 1);
            }
        }
        return ans;
    }


    // for test
    public static int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generateRandomArray(int size, int value) {
        int[] ans = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;

        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int K = (int) (Math.random() * value) - (int) (Math.random() * value);
            int ans1 = fun2(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");

    }

}
