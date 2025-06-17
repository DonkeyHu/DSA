package com.donkey.leetcode;

/**
 * 7. 整数反转
 * <p>
 * 思路：一个整数 %10 可以拿到每一个余数，这里不太熟，这是核心思路
 * int res = 0;
 * while (x != 0) {
 * res = res * 10 + x % 10;
 * x = x / 10;
 * }
 *
 */
public class Q007_IntegerReverse {

    // 失败的方法
    public static int reverse(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        String mark = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '-') {
                mark = "-";
            } else {
                sb.append(chars[i]);
            }
        }
        String res = sb.toString();
        Integer ans = Integer.valueOf(mark + res);
        return ans;
    }

    //正确的方法
    public static int reverse2(int x) {
        boolean neg = ((x >>> 31) & 1) == 1;
        x = neg ? x : -x;

        int n = Integer.MIN_VALUE / 10;
        int m = Integer.MIN_VALUE % 10;

        int res = 0;
        while (x != 0) {
            // 这里判断溢出情况，统一转为负数，只判断一种溢出情况即可
            if (res < n || ((res == n) && (x % 10 < m))) {
                return 0;
            }

            res = res * 10 + x % 10;
            x = x / 10;
        }
        return neg ? res : Math.abs(res);
    }


    public static void main(String[] args) {
        System.out.println(reverse2(1534236469));

        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }


}
