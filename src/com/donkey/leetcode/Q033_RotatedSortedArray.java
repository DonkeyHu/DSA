package com.donkey.leetcode;

/**
 * 33. 搜索旋转数组
 */
public class Q033_RotatedSortedArray {

    public static int search(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        while (L <= R) {
            int M = (L + R) / 2;
            if (nums[M] == target) {
                return M;
            }
            if (nums[M] >= nums[L]) {
                if (target >= nums[L] && nums[M] > target) {
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            } else {
                if (target > nums[M] && nums[R] >= target) {
                    L = M + 1;
                } else {
                    R = M - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1};
        System.out.println(search(arr, 1));
    }
}
