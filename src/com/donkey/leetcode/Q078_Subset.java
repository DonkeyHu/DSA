package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 其实我不怎么理解回溯这个概念，这个确实有股二叉树递归的感觉，二路递归和二叉树深度优先遍历很类似
 */
public class Q078_Subset {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        process(0,nums,ans,list);
        return ans;
    }

    public static void process(int index, int[] nums, List<List<Integer>> ans, List<Integer> list) {
        if (index == nums.length) {
            List<Integer> x = new ArrayList<>();
            x.addAll(list);
            ans.add(x);
            return;
        }

        process(index + 1, nums, ans, list);
        list.add(nums[index]);
        process(index + 1, nums, ans, list);
        list.remove(list.size()-1);
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3};

        List<List<Integer>> subsets = subsets(arr);
        for (List<Integer> subset : subsets) {
            System.out.print(Arrays.toString(subset.toArray()) + ", ");
        }

    }

}
