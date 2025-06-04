package com.donkey.msb.class04;

/**
 * 327. 区间和的个数
 *
 * 给定一个数组arr，和两个整数a和b（a<=b）
 * 求arr中有多少个子数组，累加和在[a,b]这个范围上
 * 返回达标的子数组数量
 *
 * （1）这题前缀和数组只是基本操作
 * （2）最关键是归并的过程中，有个count的计算
 */
public class Code01_CountOfRangeNum {

    public static int countRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        long[] rangeSum = new long[arr.length];
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            rangeSum[i] = sum;
        }
        return process(rangeSum, 0, rangeSum.length - 1, lower, upper);
    }

    public static int process(long[] arr, int L, int R, int lower, int upper) {
        if (L == R) {
            return arr[L] >= lower && arr[L] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        int l = process(arr, L, M, lower, upper);
        int r = process(arr, M + 1, R, lower, upper);
        int merge = merge(arr, L, M, R, lower, upper);
        return l + r +merge;
    }

    public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int windowL = L;
        int windowR = L;
        int ans = 0;
        // 这里是最关键最难理解的地方，想一想为什么windowL/windowR的范围只是[L，R]，相当于左边数组。但是遍历却是[M+1，R]?
        // 这就是递归的奇妙之处，其实preSum[....,x]，由于这个递归关系，x前面的数都判断了是否在[x-upper,x-lower]这个范围了的
        // [windowL, windowR)
        for (int i = M + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (arr[windowR] <= max && windowR <= M) {
                windowR++;
            }
            while (arr[windowL] < min && windowL <= M) {
                windowL++;
            }
            ans += windowR - windowL;
        }

        long[] help = new long[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] test = {-2147483647,0,-2147483647,2147483647};
        System.out.println(countRangeSum(test, -564, 3864));
    }
}
