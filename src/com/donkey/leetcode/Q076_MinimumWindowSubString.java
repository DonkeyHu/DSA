package com.donkey.leetcode;


/**
 * 思路没找到，之前以为思路以为是动态规划的样本对应模型
 * <p>
 * 思路是滑动窗口+子数组问题（以i开头的子数组，最短子数组能满足要求的）
 */
public class Q076_MinimumWindowSubString {

    public static String minWindow(String s, String t) {
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int[] map = new int[256];
        char[] xx = new char[256];
        int all = 0;
        for (char c : target) {
            map[c]++;
            all++;
        }

        int L = 0;
        int R = 0;

        int minLen = -1;
        int ansL = -1;
        int ansR = -1;
        // [L,R)
        while (R != str.length) {
            map[str[R]]--;
            if (map[str[R]] >= 0) {
                all--;
            }
            if (all == 0) {
                while (map[str[L]] < 0) {
                    map[str[L++]]++;
                }
                if (minLen == -1 || minLen > R - L + 1) {
                    minLen = R - L + 1;
                    ansL = L;
                    ansR = R;
                }
                map[str[L]]++;
                L++;
                all++;
            }
            R++;
        }
        return minLen == -1 ? "" : s.substring(ansL, ansR + 1);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minWindow(s,t));
    }

}
