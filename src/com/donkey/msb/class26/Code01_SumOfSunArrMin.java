package com.donkey.msb.class26;


import java.util.Arrays;
import java.util.Stack;

/**
 * 测试链接：https://leetcode.com/problems/sum-of-subarray-minimums/
 * 给定一个数组arr，返回所有子数组最小值的累加和.
 */
public class Code01_SumOfSunArrMin {

    // 暴力解
    public static int f1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                res += min;
            }
        }
        return res;
    }


    // 单调栈
    public static int f2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer x = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                res += (x - left) * (i - x) * arr[x];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer x = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            res += (x - left) * (arr.length - x) * arr[x];
        }
        return res;
    }

    public static int[] randomArr(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) + 1;
        }
        return res;
    }


    public static void main(String[] args) {
        int len = 10;
        int maxVal = 20;
        System.out.println("Start!");
        for (int i = 0; i < 10000; i++) {
            int[] arr = randomArr(len, maxVal);
            int a1 = f1(arr);
            int a2 = f2(arr);
            if (a1 != a2) {
                System.out.println(Arrays.toString(arr));
                System.out.println("暴力解:" + a1 + " 单调栈:" + a2);
                break;
            }
        }
        System.out.println("End!");
    }
}
