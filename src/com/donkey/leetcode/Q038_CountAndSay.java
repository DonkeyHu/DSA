package com.donkey.leetcode;

/**
 * 字符压缩的时候没有立马想到双指针移动，还想了挺久...
 */
public class Q038_CountAndSay {

    public static String countAndSay(int n) {
        return process(n);
    }

    public static String process(int n) {
        if (n == 1) {
            return "1";
        }
        return convert(process(n - 1));
    }

    public static String convert(String s) {
        int l = 0;
        int r = 0;
        int len = s.length();
        String ans = "";
        while (r < len) {
            char rC = s.charAt(r);
            char lC = s.charAt(l);
            if (rC != lC) {
                int n = r - l;
                ans = ans + n + lC;
                l = r;
            }
            r++;
        }
        ans = ans + (r - l) + s.charAt(l);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

}
