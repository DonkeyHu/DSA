package com.donkey.leetcode;

/**
 * 字符串最大公因子
 */
public class GCDOfStrings {
    public static void main(String[] args) {
        GCDOfStrings instance = new GCDOfStrings();
        System.out.println(instance.gcdOfStrings("ABABAB","ABAB"));
    }

    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        for (int len = Math.min(len1, len2); len > 0; len--) {
            if (len1 % len == 0 && len2 % len == 0) {
                String result = str1.substring(0, len);
                if (check(result,str1) && check(result,str2)){
                    return result;
                }
            }
        }
        return "";
    }

    public boolean check(String result, String desc) {
        int multi = desc.length() / result.length();
        String s = "";
        for (int i = 0; i < multi; i++) {
            s = s + result;
        }
        return s.equals(desc);
    }
}
