package com.donkey.leetcode;

/**
 * 没什么思路，以为是位运算，结果是二分去解题
 *
 * 还要注意溢出这种情况.....M*M溢出
 */
public class Q069_SqrtX {

    public static int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        long L = 0;
        long R = x;
        long ans = -1;
        while (L <= R) {
            long M = (L + R) / 2;
            long tmp = M * M;
            if (tmp > x) {
                R = M - 1;
            } else {
                ans = M;
                L = M + 1;
            }
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        int x = 2147395599;
        System.out.println(mySqrt(x));
    }
}
