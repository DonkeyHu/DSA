package com.donkey.leetcode;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * 我居然在数组两点中求中点也会放错..... M = (L+R)/2 而不是(R-L)/2
 */
public class Q034_FindFisrtLastPosition {

    public static int[] searchRange(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        while (L <= R) {
            int M = (R + L) / 2;
            if (nums[M] == target) {
                int r = M;
                int l = M;
                while (r < R && nums[r + 1] == target) {
                    r++;
                }
                while (L < l && nums[l - 1] == target) {
                    l--;
                }
                return new int[]{l, r};
            } else if (nums[M] < target) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return new int[]{-1, -1};
    }


    public static int findFirstPos(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        int ans = -1;
        while (L <= R) {
            int M = (R + L) / 2;
            if (nums[M] == target) {
                ans = M;
                R = M - 1;
            } else if (nums[M] < target) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return ans;
    }

    public static int findLastPos(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        int ans = -1;
        while (L <= R) {
            int M = (R + L) / 2;
            if (nums[M] == target) {
                ans = M;
                L = M + 1;
            } else if (nums[M] < target) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return ans;
    }

    public static int[] searchRange2(int[] nums, int target) {
        int l = findFirstPos(nums, target);
        int r = findLastPos(nums, target);
        return new int[]{l, r};
    }


    public static void main(String[] args) {
        int[] arr = {5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println(Arrays.toString(searchRange2(arr, target)));
    }
}
