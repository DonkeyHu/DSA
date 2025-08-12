package com.donkey.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 */
public class Q001_TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] result = {i, j};
                    return result;
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (map.containsKey(target - x)){
                return new int[]{map.get(target - x), i};
            }else {
                map.put(x, i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] input = {2, 7, 11, 15};
        int[] result = twoSum(input, 9);
        System.out.println(Arrays.toString(result));
    }
}
