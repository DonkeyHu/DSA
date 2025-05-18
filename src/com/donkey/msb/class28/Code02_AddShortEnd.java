package com.donkey.msb.class28;

/**
 * 给定一个字符串，在字符串后面最少加几位数可以让其变成回文字符串
 */
public class Code02_AddShortEnd {

    public static String fun(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] chars = manacherStr(s);
        int[] pArr = new int[chars.length];
        int R = -1;
        int C = -1;
        int index = -1;
        // 这个算法加速的关键在于，(i,R)区域类的下一个i可以加速计算
        for (int i = 0; i < chars.length; i++) {
            int len = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + len < chars.length && i - len >= 0 && chars[i + len] == chars[i - len]) {
                len++;
            }
            if (i + len > R) {
                R = i + len;
                C = i;
            }
            pArr[i] = len;
            if (i + len == chars.length) {
                index = i - len;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i >= 0; i--) {
            if (chars[i] != '#') {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    // aba -> #a#b#a#
    // abba -> #a#b#b#a#
    // （1）理解原始串的最长回文长度：aba -> 3, abba -> 4, a -> 1, ab -> 1
    // （2）理解拓展串的回文半径：#a#b#a# -> 4, #a#b#b#a# -> 5, #a# ->2, #a#b# ->2
    public static char[] manacherStr(String s) {
        char[] str = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : str[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(fun("xdxdabcddcba"));
    }

}
