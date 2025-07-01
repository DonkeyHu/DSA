package com.donkey.leetcode;

/**
 * 29.两数相除
 */
public class Q029_DivideTowInteger {

    public static int add(int a, int b) {
        int res = a;
        while (b != 0) {
            res = a ^ b;
            b = a & b;
            a = res;
            b <<= 1;
        }
        return res;
    }

    public static int minus(int a, int b) {
        return add(a, ~b + 1);
    }


    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = add(res, a);
            }
            b = b >>> 1;
            a = a << 1;
        }
        return res;
    }


    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (a != Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE && b != Integer.MIN_VALUE) {
            if (b == -1) {
                return Integer.MAX_VALUE;
            }
            int tmp = add(a, 1);
            int res = divideOnRange(tmp, b);
            int rest = divideOnRange(minus(a, multi(res, b)), b);
            return add(res, rest);
        } else {
            return divideOnRange(a, b);
        }
    }

    public static boolean isNeg(int x) {
        return x < 0;
    }

    public static int divideOnRange(int a, int b) {
        int x = isNeg(a) ? -a : a;
        int y = isNeg(b) ? -b : b;

        int ans = 0;
        for (int i = 31; i >= 0; i = minus(i, 1)) {
            // 这里用x无符号右移去判断，如果用y左移的话，怕y变成负数
            if ((x >> i) >= y) {
                ans = add(ans, 1 << i);
                x = minus(x, y << i);
            }
        }
        if ((isNeg(a) && isNeg(b)) || (!isNeg(a) && !isNeg(b))) {
            return ans;
        } else {
            return -ans;
        }
    }

    public static int div(int a, int b) {
        int x = a;
        int y = b;
        int res = 0;
        for (int i = 31; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return res;
    }


    public static void main(String[] args) {
//        System.out.println(add(0, 5));
//        System.out.println(minus(10, 7));
//        System.out.println(multi(-10, -7));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(divideOnRange(2147483647, 3));
        System.out.println(div(2147483647, 3));
    }

}
