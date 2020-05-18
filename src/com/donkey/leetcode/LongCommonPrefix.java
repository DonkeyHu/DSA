package com.donkey.leetcode;

/**
 * 14. 最长公共前缀
 */
public class LongCommonPrefix {
    public static void main(String[] args) {

    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length ==1) return strs[0];
        String prefix = strs[0];
        for (int i=1;i<strs.length;i++){
            int j = 0;
            for (; j < prefix.length() && j < strs[i].length(); j++) {
                if (prefix.charAt(j) != strs[i].charAt(j)){
                    break;
                }
            }
            prefix = strs[i].substring(0,j);
            if (prefix.equals("")){
                return "";
            }
        }
        return prefix;
    }
}
