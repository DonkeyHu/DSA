package com.donkey.leetcode;

/**
 * 这个思路不可能想得到，有抽象出来的可能性？
 */
public class Q041_FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        int l = 0;
        int r = nums.length;
        // int[] arr = {3, 4, -1, 1, 2, 6};
        while (l < r) {
            if (nums[l] == l + 1) {
                l++;
            } else {
                if (nums[l] <= l || nums[l] > r) {
                    swap(nums, l, r - 1);
                    r--;
                } else {
                    if (nums[l] <= r && nums[nums[l] - 1] != nums[l]) {
                        swap(nums, l, nums[l] - 1);
                    } else {
                        if (nums[l] <= r && nums[nums[l] - 1] == nums[l]) {
                            swap(nums, l, r - 1);
                            r--;
                        }
                    }
                }
            }
        }
        return l + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {7,8,9,11,12};
        System.out.println(firstMissingPositive(arr));
    }

}
