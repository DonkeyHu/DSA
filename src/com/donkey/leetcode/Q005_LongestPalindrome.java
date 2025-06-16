package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 5. 最长回文子串
 */
public class Q005_LongestPalindrome {

    public static String longestPalindrome(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        char[] hashChars = new char[2 * len + 1];
        int index = 0;
        int newLen = hashChars.length;
        for (int i = 0; i < newLen; i++) {
            hashChars[i] = (i & 1) == 0 ? '#' : chars[index++];
        }

        int max = 0;
        List<Character> ans = new ArrayList<>();

        for (int i = 1; i < newLen; i++) {
            int l = i;
            int r = i;
            int j = 1;
            while ((l - j) >= 0 && (r + j) < newLen && hashChars[l - j] == hashChars[r + j]) {
                if (j > max) {
                    max = j;
                    ans.clear();
                    for (int k = l - j; k <= r + j; k++) {
                        ans.add(hashChars[k]);
                    }
                }

                j++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            if (ans.get(i) != '#') {
                sb.append(ans.get(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }
}
