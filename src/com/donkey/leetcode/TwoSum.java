package com.donkey.leetcode;

import java.util.Arrays;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        for(int i = 0;i<nums.length;i++){
            for(int j = i+1;j<nums.length;j++){
                if(nums[i]+nums[j] == target){
                    int[] result = {i,j};
                    return result;
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
		int[] input = {2,7,11,15};
		int[] result = twoSum(input,9);
		System.out.println(Arrays.toString(result));
		int a = 2 ;
		int b = 7&2047;
		System.out.println(b);
	}
}
