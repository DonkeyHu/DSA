package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 大概思路是循环里面有个递归，(1)但是这个nums数组该怎么缩呢 (2)怎么接成功的数字呢？
 */
public class Q046_Permutation {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans =  new ArrayList<>();
        process(0, nums.length, nums, new ArrayList<Integer>(), ans);
        return ans;
    }

    public static void process(int index, int len, int[] nums, List<Integer> s, List<List<Integer>> ans) {
        if (index == len) {
            ans.add(s);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 这里创建一个新的List是关键，我一直没想到用什么去接数字
            List<Integer> newList = new ArrayList<>(s);
            int[] shrinkArr = shrinkArr(nums, nums[i]);
            newList.add(nums[i]);
            process(index+1, len, shrinkArr, newList, ans);
        }
    }

    public static int[] shrinkArr(int[] arr, int num) {
        int[] res = new int[arr.length - 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != num) {
                res[index++] = arr[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0,-1,1};
        List<List<Integer>> permute = permute(arr);
        for (List<Integer> integers : permute) {
            System.out.print(Arrays.toString(integers.toArray())+" ");
        }
    }

}
