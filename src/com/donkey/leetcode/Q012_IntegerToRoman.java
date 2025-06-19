package com.donkey.leetcode;

/**
 * 12. 整数转罗马数字
 */
public class Q012_IntegerToRoman {

    public static String intToRoman(int num) {
        int res = num;
        String ans = "";
        int index = 1;
        while (res > 0) {
            int m = res % 10;
            ans = convert(index++, m) + ans;
            res = res / 10;
        }
        return ans;
    }

    public static String convert(int index, int m) {
        if (index == 1) {
            return simply("I", "V", "X", m);
        } else if (index == 2) {
            return simply("X", "L", "C", m);
        } else if (index == 3) {
            return simply("C", "D", "M", m);
        } else {
            String ans = "";
            while (0 < m) {
                ans = ans + "M";
                m--;
            }
            return ans;
        }
    }

    public static String simply(String x, String y, String z, int m) {
        if (m < 4) {
            String ans = "";
            while (0 < m) {
                ans = ans + x;
                m--;
            }
            return ans;
        } else if (m == 4) {
            return x + y;
        } else if (m == 5) {
            return y;
        } else if (m < 9) {
            String ans = y;
            while (0 < m - 5) {
                ans = ans + x;
                m--;
            }
            return ans;
        } else {
            return x + z;
        }
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }

}
