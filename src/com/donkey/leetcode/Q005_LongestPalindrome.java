package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 5. 最长回文子串
 */
public class Q005_LongestPalindrome {

    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }

        char[] m = manacherStr(s);
        int len = m.length;

        int max = Integer.MIN_VALUE;
        String ans = "";
        for (int i = 0; i < len; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < len && m[L] == m[R]) {
                L--;
                R++;
            }
            int l = L + 1;
            int r = R - 1;
            if (l >= 0 && r < len && (r - l + 1) > max) {
                max = r - l + 1;
                StringBuilder sb = new StringBuilder();
                for (int j = l; j <= r; j++) {
                    if (m[j] != '#') {
                        sb.append(m[j]);
                    }
                }
                ans = sb.toString();
            }
        }
        return ans;
    }

    public static char[] manacherStr(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        char[] res = new char[2 * len + 1];
        int index = 0;
        for (int i = 0; i < 2 * len + 1; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbcd"));
    }
}
