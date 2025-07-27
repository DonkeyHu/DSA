package com.donkey.leetcode;

import java.util.Arrays;

/**
 * 排序算法都忘了....像是荷兰国旗问题...
 * <p>
 * 左边界一直没想明白...
 */
public class Q075_SortedColor {

    public static void sortColors(int[] nums) {
        int len = nums.length;
        int l = -1;
        int index = 0;
        int r = len - 1;
        while (index <= r) {
            if (nums[index] < 1) {
                swap(nums, ++l, index++);
            } else if (nums[index] == 1) {
                index++;
            } else {
                swap(nums, index, r--);
            }
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        int[] arr = {2,0,2,1,2,0,1,0};
        sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }

}
