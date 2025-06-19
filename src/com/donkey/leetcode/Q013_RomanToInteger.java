package com.donkey.leetcode;

/**
 * 13. 罗马数字转整数
 */
public class Q013_RomanToInteger {

    public static int romanToInt(String s) {
        int ans = 0;
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            switch (c) {
                case 'M':
                    ans += 1000;
                    break;
                case 'D':
                    ans += 500;
                    break;
                case 'C':
                    if ((i + 1 < len) && (chars[i + 1] == 'D' || chars[i + 1] == 'M')) {
                        ans -= 100;
                    } else {
                        ans += 100;
                    }
                    break;
                case 'L':
                    ans += 50;
                    break;
                case 'X':
                    if ((i + 1 < len) && (chars[i + 1] == 'L' || chars[i + 1] == 'C')) {
                        ans -= 10;
                    } else {
                        ans += 10;
                    }
                    break;
                case 'V':
                    ans += 5;
                    break;
                case 'I':
                    if ((i + 1 < len) && (chars[i + 1] == 'V' || chars[i + 1] == 'X')) {
                        ans -= 1;
                    } else {
                        ans += 1;
                    }
                    break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }


}
