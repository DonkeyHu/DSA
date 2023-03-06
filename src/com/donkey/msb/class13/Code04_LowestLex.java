package com.donkey.msb.class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 */
public class Code04_LowestLex {

    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return (a + b).compareTo(b + a);
            }
        });

        String ans = "";
        for (String str : strs) {
            ans += str;
        }
        return ans;
    }

    // {"abc", "cks", "bct"}
    // 0 1 2
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] strs, int index) {
        int n = strs.length;
        String[] ans = new String[n - 1];
        int next = 0;
        for (int i = 0; i < n; i++) {
            if (i != index) {
                ans[next++] = strs[i];
            }
        }
        return ans;
    }

    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    // strs中所有字符串全排列，返回所有可能的结果
    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    public static String generateRandomString(int strLen) {
        char[] chars = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < chars.length; i++) {
            int value = (int) (Math.random() * 5);
            chars[i] = Math.random() < 0.5 ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(chars);
    }

    public static String[] generateRandomStringArr(int arrLen, int strLen) {
        String[] strArr = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = generateRandomString(strLen);
        }
        return strArr;
    }

    public static String[] copyStringArr(String[] strArr) {
        String[] newArr = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            newArr[i] = strArr[i];
        }
        return newArr;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            String[] arr1 = generateRandomStringArr(arrLen, strLen);
            String[] arr2 = copyStringArr(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))){
                System.out.println("Oops");
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                for (String str : arr2) {
                    System.out.print(str + ",");
                }
                return;
            }
        }
        System.out.println("Finish!");
    }

}
