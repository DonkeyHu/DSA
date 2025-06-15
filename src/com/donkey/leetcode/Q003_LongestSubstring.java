package com.donkey.leetcode;


import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 *
 * 第一反应想到滑动窗口
 */
public class Q003_LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int l = 0;
        int r = 0;
        Set<Character> set = new HashSet<>();
        int ans = 0;
        while (r < s.length()) {
            char c = chars[r];
            if (!set.contains(c)) {
                set.add(c);
                r++;
            } else {
                ans = Math.max(ans, set.size());
                char lc = chars[l];
                set.remove(lc);
                l++;
            }
        }
        ans = Math.max(ans, set.size());
        return ans;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

}
