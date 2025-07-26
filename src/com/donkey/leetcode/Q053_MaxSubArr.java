package com.donkey.leetcode;

/**
 * 子数组问题，求了前缀和以后要怎么滑动呢？
 * <p>
 * （1）遍历前缀和数组，保存一个最小值
 * （2）当前值减最小值
 */
public class Q053_MaxSubArr {

    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] preSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int minSum = Integer.MAX_VALUE;
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < preSum.length - 1; i++) {
            if (minSum > preSum[i]) {
                minSum = preSum[i];
            }
            if (preSum[i+1] - minSum > ans) {
                ans = preSum[i+1] - minSum;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-2,-1};
        System.out.println(maxSubArray(arr));
    }
}
