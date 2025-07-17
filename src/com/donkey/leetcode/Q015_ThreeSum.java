package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 *
 *  注意，这题返回的是数组的值，不是下标，所以有个去重的过程
 *
 */
public class Q015_ThreeSum {

    // 会超时，不能这么干
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> x = new ArrayList<>();
                        x.add(nums[i]);
                        x.add(nums[j]);
                        x.add(nums[k]);
                        x.sort(Integer::compareTo);
                        if (!contain(res, x)) {
                            res.add(x);
                        }
                    }
                }
            }
        }
        return res;
    }

    public static boolean contain(List<List<Integer>> total, List<Integer> x) {
        for (int i = 0; i < total.size(); i++) {
            List<Integer> y = total.get(i);
            if (x.get(0) == y.get(0) && x.get(1) == y.get(1) && x.get(2) == y.get(2)) {
                return true;
            }
        }
        return false;
    }


    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > nums.length - 3) {
                break;
            }
            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            int x = nums[i];
            List<List<Integer>> lists = towSum2(nums, i + 1, nums.length - 1, -x);
            for (int j = 0; j < lists.size(); j++) {
                List<Integer> ans = lists.get(j);
                ans.add(0, x);
                res.add(ans);
            }
        }
        return res;
    }

    public static List<List<Integer>> towSum2(int[] num, int start, int end, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int begin = start;
        while (begin < end) {
            if (num[begin] + num[end] < target) {
                begin++;
            } else if (num[begin] + num[end] > target) {
                end--;
            } else {
                // 这里是去重的过程
                if (begin > start && num[begin] == num[begin - 1]) {
                    begin++;
                    continue;
                }
                List<Integer> ans = new ArrayList<>();
                ans.add(num[begin]);
                ans.add(num[end]);
                res.add(ans);
                begin++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum2(arr);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

    }
}
