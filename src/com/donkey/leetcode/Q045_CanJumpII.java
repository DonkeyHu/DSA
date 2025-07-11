package com.donkey.leetcode;

public class Q045_CanJumpII {

    public static int jump(int[] nums) {
        return process(0, nums.length, nums, 0);
    }


    public static int process(int index, int len, int[] nums, int times) {
        if (index == len - 1) {
            return times;
        }

        int ans = Integer.MAX_VALUE;
        int step = nums[index];
        for (int i = 1; i <= step; i++) {
            if (index + i < len) {
                ans = Math.min(ans, process(index + i, len, nums, times + 1));
            }
        }
        return ans;
    }

    public static int jump2(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            dp[len - 1][i] = i;
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = len - 2; j >= 0; j--) {
                int step = nums[i];
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 1; k <= step; k++) {
                    if (i + k < len) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + k][j + 1]);
                    }
                }
            }
        }

        return dp[0][0];
    }


    public static int jump3(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(0, nums.length, nums, 0, dp);
    }


    public static int process3(int index, int len, int[] nums, int times, int[][] dp) {
        if (dp[index][times] != -1) {
            return dp[index][times];
        }

        if (index == len - 1) {
            dp[index][times] = times;
            return times;
        }

        int ans = Integer.MAX_VALUE;
        int step = nums[index];
        for (int i = 1; i <= step; i++) {
            if (index + i < len) {
                ans = Math.min(ans, process3(index + i, len, nums, times + 1, dp));
            }
        }
        dp[index][times] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 1, 4};
        System.out.println(jump3(arr));
    }

}
