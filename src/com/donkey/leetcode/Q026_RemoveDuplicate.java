package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *  26.删除有序数组的重复项
 */
public class Q026_RemoveDuplicate {

    public static int removeDuplicates(int[] nums) {
        int l = 0;
        int r = 0;
        int len = nums.length;
        int ans = 0;
        while (r < len) {
            if (l == r) {
                nums[ans++] = nums[l];
            }
            if (nums[l] == nums[r]) {
                r++;
            }else {
                l++;
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        int[] arr = {1,1,2};
        System.out.println(removeDuplicates(arr));
    }

}
