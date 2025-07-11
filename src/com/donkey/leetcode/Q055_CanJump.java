package com.donkey.leetcode;

public class Q055_CanJump {

    public static boolean canJump(int[] nums) {
        return process(0, nums.length, nums);
    }


    public static boolean process(int index, int len, int[] nums) {
        if (index == len - 1) {
            return true;
        }

        if (index >= len) {
            return false;
        }

        int step = nums[index];
        for (int i = 1; i <= step; i++) {
            if (process(index + i, len, nums)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canJump2(int[] nums) {
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[len - 1] = true;

        for (int i = len - 1; i >= 0; i--) {
            int step = nums[i];
            for (int j = 1; j <= step; j++) {
                if (i + j < len && dp[i + j]) {
                    dp[i] = true;
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,1,4};
        System.out.println(canJump2(arr));
    }

}
