package com.donkey.leetcode;


import java.util.*;

/**
 * 字符串如何按自然序排序出来？
 */
public class Q049_GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String key = sortStr(str);
            if (map.containsKey(key)) {
                List<String> v = map.get(key);
                v.add(str);
                map.put(key, v);
            } else {
                List<String> v = new ArrayList<>();
                v.add(str);
                map.put(key, v);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> v : map.values()) {
            res.add(v);
        }
        return res;
    }


    public static String sortStr(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String res = String.valueOf(chars);
        return res;
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        groupAnagrams(strs);
    }


}
