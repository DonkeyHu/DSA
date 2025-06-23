package com.donkey.leetcode;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 *
 *  我写了很奇怪的循环，这时候就该想到递归，子问题和整问题没有关联
 */
public class Q017_LetterCombination {

    public static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) {
            return res;
        }

        char[] chars = digits.toCharArray();
        if (chars.length == 1) {
            int k = Integer.valueOf(digits);
            String s = map.get(k);
            char[] array = s.toCharArray();
            for (char c : array) {
                res.add(String.valueOf(c));
            }
        }
        if (chars.length == 2) {
            int k1 = chars[0] - 48;
            int k2 = chars[1] - 48;
            String s1 = map.get(k1);
            String s2 = map.get(k2);
            for (int i = 0; i < s1.length(); i++) {
                for (int j = 0; j < s2.length(); j++) {
                    String ans = "" + s1.charAt(i) + s2.charAt(j);
                    res.add(ans);
                }
            }
        }

        if (chars.length == 3) {
            int k1 = chars[0] - 48;
            int k2 = chars[1] - 48;
            int k3 = chars[2] - 48;
            String s1 = map.get(k1);
            String s2 = map.get(k2);
            String s3 = map.get(k3);
            for (int i = 0; i < s1.length(); i++) {
                for (int j = 0; j < s2.length(); j++) {
                    for (int k = 0; k < s3.length(); k++) {
                        String ans = "" + s1.charAt(i) + s2.charAt(j) + s3.charAt(k);
                        res.add(ans);
                    }
                }
            }
        }

        if (chars.length == 4) {
            int k1 = chars[0] - 48;
            int k2 = chars[1] - 48;
            int k3 = chars[2] - 48;
            int k4 = chars[3] - 48;
            String s1 = map.get(k1);
            String s2 = map.get(k2);
            String s3 = map.get(k3);
            String s4 = map.get(k4);
            for (int i = 0; i < s1.length(); i++) {
                for (int j = 0; j < s2.length(); j++) {
                    for (int k = 0; k < s3.length(); k++) {
                        for (int l = 0; l < s4.length(); l++) {
                            String ans = "" + s1.charAt(i) + s2.charAt(j) + s3.charAt(k) + s4.charAt(l);
                            res.add(ans);
                        }
                    }
                }
            }
        }
        return res;
    }


    public static List<String> letterCombinations2(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) {
            return res;
        }
        process(0, "", digits, res);
        return res;
    }

    public static void process(int index, String path, String digits, List<String> res) {
        if (index == digits.length()) {
            res.add(path);
            return;
        }

        int k = digits.charAt(index) - 48;
        String v = map.get(k);
        char[] chars = v.toCharArray();
        for (char c : chars) {
            process(index + 1, path + c, digits, res);
        }
    }


    public static void main(String[] args) {
        System.out.println(letterCombinations2("23"));
    }

}
