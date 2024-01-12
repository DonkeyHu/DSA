package com.donkey.msb.class20;

/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * <p>
 * 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class Code01_PalindromeSubsequence {

    public static int way1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] arr = str.toCharArray();
        return process(arr, 0, arr.length - 1);
    }

    public static int process(char[] arr, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return arr[L] == arr[R] ? 2 : 1;
        }
        int p1 = process(arr, L + 1, R - 1);
        int p2 = process(arr, L + 1, R);
        int p3 = process(arr, L, R - 1);
        // 起初我很疑惑，为什么要判断arr[L] == arr[R]
        // 这里有很强的逻辑性，它吧arr[L,R]的世界分为这四种可能性，p4这种可能性一定是arr[L] == arr[R]处于最长回文子序列的最边界的两个值
        int p4 = arr[L] == arr[R] ? (2 + process(arr, L + 1, R - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    public static void main(String[] args) {

    }

}
