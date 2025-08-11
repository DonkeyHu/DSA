package com.donkey.leetcode;


/**
 * 思路是：除非边界条件能命中，那么必然存在先上升后下降的趋势，那么必然存在峰值
 *
 * 有边界条件，没那么容易想到
 */
public class Q162_FindPeekElement {

    public static int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        }

        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }

//        int L = 0;
//        int R = len - 1;
        // 边界条件！！注意！！这里一定要是下面的值，才避免下面边界条件的错误
        int L = 1;
        int R = len - 2;
        while (L <= R) {
            int M = (L + R) / 2;
            // 假设此时M为[0]或者M为[len-1]，这里的边界条件如何处理的呢？
            if (nums[M] > nums[M - 1] && nums[M] > nums[M + 1]) {
                return M;
            }
            if (nums[M] < nums[M - 1]) {
                R = M - 1;
            } else if (nums[M] < nums[M + 1]) {
                L = M + 1;
            }
        }
        return L;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 3, 2, 1};
        System.out.println(findPeakElement(arr));
    }
}
