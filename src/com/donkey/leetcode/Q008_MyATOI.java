package com.donkey.leetcode;


/**
 * 8. 字符串转换整数 (atoi)
 */
public class Q008_MyATOI {


    public static int myAtoi(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        String newStr = s.trim();
        if (newStr.isEmpty()) {
            return 0;
        }
        char[] chars = newStr.toCharArray();

        int n = Integer.MIN_VALUE / 10;
        int m = Integer.MIN_VALUE % 10;

        boolean neg = false;
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && chars[i] == '-') {
                neg = true;
                continue;
            }
            if (i == 0 && chars[i] == '+') {
                continue;
            }
            int x = chars[i] - '0';
            if (x >= 0 && x <= 9) {
                int y = -x;
                // 这里是有些特殊的边界条件的 2147483648， 所以这里y <= m
                if (res < n || (res == n) && (y <= m)) {
                    return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                res = res * 10 + y;
            } else {
                break;
            }
        }
        return neg ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("2147483648"));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

}
