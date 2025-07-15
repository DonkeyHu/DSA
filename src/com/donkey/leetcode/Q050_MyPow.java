package com.donkey.leetcode;

/**
 * 这题没什么思路，这是个套路技巧，幂拆分成二进制
 * 快速幂、斐波拉契数列？没一点印象了....
 */
public class Q050_MyPow {

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1D;
        }
        if (n == Integer.MIN_VALUE) {
            return (x == 1D || x == -1D) ? 1D : 0;
        }

        int pow = Math.abs(n);
        double tmp = x;
        double ans = 1D;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                ans = ans * tmp;
            }
            pow = pow >> 1;
            tmp = tmp * tmp;
        }
        return n < 0 ? 1D / ans : ans;
    }


    public static void main(String[] args) {
        System.out.println(Math.pow(1D, Integer.MIN_VALUE));
//        System.out.println(myPow(0.5, Integer.MIN_VALUE));
    }
}
