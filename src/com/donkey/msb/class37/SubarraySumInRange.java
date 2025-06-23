package com.donkey.msb.class37;

import java.util.*;

public class SubarraySumInRange {

    /**
     * 计算数组中累加和在[a,b]范围内的子数组数量
     *
     * @param arr 输入数组
     * @param a   范围下界
     * @param b   范围上界
     * @return 满足条件的子数组数量
     */
    public static int countSubarraysInRange(int[] arr, int a, int b) {
        // 使用前缀和 + TreeMap 的方法
        // 时间复杂度: O(n log n)
        // 空间复杂度: O(n)

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        long[] prefixSum = new long[n + 1]; // 前缀和数组，防止溢出使用long

        // 计算前缀和
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }

        int count = 0;

        // 对于每个结束位置j，统计以j结尾的满足条件的子数组数量
        TreeMap<Long, Integer> prefixMap = new TreeMap<>();

        for (int j = 0; j <= n; j++) {
            if (j > 0) {
                // 对于子数组[i, j-1]，其和为 prefixSum[j] - prefixSum[i]
                // 要求 a <= prefixSum[j] - prefixSum[i] <= b
                // 即 prefixSum[j] - b <= prefixSum[i] <= prefixSum[j] - a

                long lower = prefixSum[j] - b;  // prefixSum[i]的下界
                long upper = prefixSum[j] - a;  // prefixSum[i]的上界

                // 使用TreeMap的subMap功能统计范围内的前缀和个数
                Map<Long, Integer> validPrefix = prefixMap.subMap(lower, true, upper, true);
                for (int freq : validPrefix.values()) {
                    count += freq;
                }
            }

            // 将当前前缀和加入map
            prefixMap.put(prefixSum[j], prefixMap.getOrDefault(prefixSum[j], 0) + 1);
        }

        return count;
    }

    /**
     * 暴力解法 - 用于验证结果
     * 时间复杂度: O(n^2)
     */
    public static int countSubarraysInRangeBruteForce(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int count = 0;
        int n = arr.length;

        // 枚举所有可能的子数组
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum >= a && sum <= b) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 测试用例1
        int[] arr1 = {1, 2, 3, 4};
        int a1 = 3, b1 = 6;
        System.out.println("测试用例1:");
        System.out.println("数组: " + Arrays.toString(arr1));
        System.out.println("范围: [" + a1 + ", " + b1 + "]");
        System.out.println("优化解法结果: " + countSubarraysInRange(arr1, a1, b1));
        System.out.println("暴力解法结果: " + countSubarraysInRangeBruteForce(arr1, a1, b1));
        System.out.println();

        // 测试用例2
        int[] arr2 = {2, -1, 2, 3, 1};
        int a2 = 0, b2 = 4;
        System.out.println("测试用例2:");
        System.out.println("数组: " + Arrays.toString(arr2));
        System.out.println("范围: [" + a2 + ", " + b2 + "]");
        System.out.println("优化解法结果: " + countSubarraysInRange(arr2, a2, b2));
        System.out.println("暴力解法结果: " + countSubarraysInRangeBruteForce(arr2, a2, b2));
        System.out.println();

        // 测试用例3 - 包含负数
        int[] arr3 = {-2, 5, -1};
        int a3 = -3, b3 = 1;
        System.out.println("测试用例3:");
        System.out.println("数组: " + Arrays.toString(arr3));
        System.out.println("范围: [" + a3 + ", " + b3 + "]");
        System.out.println("优化解法结果: " + countSubarraysInRange(arr3, a3, b3));
        System.out.println("暴力解法结果: " + countSubarraysInRangeBruteForce(arr3, a3, b3));
    }
}