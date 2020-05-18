package com.donkey.leetcode;

/**
 * 3. 无重复字符的最长子串
 */
public class LongestSubString {
    public static void main(String[] args) {
        LongestSubString ins = new LongestSubString();
        System.out.println(ins.lengthOfLongestSubstring("bbbbb"));
    }

    /**
     * 暴力法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int len = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++){
                String str = s.substring(i, j);
                // 判断一个字符串有没有重复字符
                if (containRepeatChar(str)){
                    continue;
                }else {
                    if (str.length() > len){
                        len = str.length();
                    }
                }
            }
        }
        return len;
    }

    public boolean containRepeatChar(String str){
        if (str == null || str.isEmpty()){
            return false;
        }
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (str.indexOf(aChar) != str.lastIndexOf(aChar)){
                return true;
            }
        }
        return false;
    }
}
