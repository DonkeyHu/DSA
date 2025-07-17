package com.donkey.leetcode;


import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * <p>
 * 第一反应想到滑动窗口，但是怎么滑的，我是没想明白的，尤其是l怎么挪动的，我起初以为有重复字符的时候l=r就行了，这是会漏掉dvdf一些case的，这个输出是3
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

    public static int lengthOfLongestSubstring2(String s) {
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
                ans = Math.max(ans, r - l);
                set.remove(chars[l]);
                l++;
            }
        }
        ans = Math.max(ans, r - l);
        return ans;
    }

    public static void main(String[] args) {
        String s = "dvdf";
        System.out.println(lengthOfLongestSubstring2(s));
    }

}
