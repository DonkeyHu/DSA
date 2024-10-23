package com.donkey.msb.class25;


import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 * <p>
 * https://leetcode.com/problems/maximum-subarray-min-product/
 */
public class Code02_AllTimeMinToMax {

    public static int max(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                    sum += arr[k];
                }
                max = Math.max(max, min * sum);
            }

        }
        return max;
    }


    // 思路：遍历每个数，假定这个数就是子数组的最小值，用单调栈来判断子数组的范围
    public static int max2(int[] arr) {
        int length = arr.length;
        // 前缀和数组，为了方便计算数据 i....j子数组大小
        int[] sum = new int[length];
        sum[0] = arr[0];
        for (int i = 1; i < length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                // 这里不是子数组的右侧，而是被遍历的数
                int x = stack.pop();
                int sumSubArr = stack.isEmpty() ? sum[i - 1] : sum[i - 1] - sum[stack.peek()];
                max = Math.max(max, arr[x] * sumSubArr);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int x = stack.pop();
            int sumSubArr = stack.isEmpty() ? sum[length - 1] : sum[length - 1] - sum[stack.peek()];
            max = Math.max(max, arr[x] * sumSubArr);
        }

        return max;
    }

    public static int[] generateRandomArr() {
        int[] res = new int[(int) (Math.random() * 10) + 10];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 100);
        }
        return res;
    }

    public static void main(String[] args) {
        int times = 100000;
        System.out.println("test start");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArr();
            if (max(arr) != max2(arr)){
                System.out.println("Fuck!");
                break;
            }
        }
        System.out.println("test done");
    }

}
