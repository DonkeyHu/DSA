package com.donkey.msb.class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印一个字符串的全部子序列
 * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 */
public class Code02_PrintAllSubsequences {

    public static List<String> sub01(String str) {
        if (str == null || str.strip() == "") {
            return null;
        }
        List<String> res = new ArrayList<>();
        char[] arr = str.toCharArray();
        String path = "";
        process01(res, 0, arr, path);
        return res;
    }

    public static void process01(List<String> ans, int index, char[] arr, String path) {
        if (index == arr.length) {
            ans.add(path);
            return;
        }
        process01(ans, index + 1, arr, path);
        process01(ans, index + 1, arr, path + String.valueOf(arr[index]));
    }


    public static List<String> sub02(String str) {
        if (str == null || str.strip() == "") {
            return null;
        }
        List<String> res = new ArrayList<>();
        char[] arr = str.toCharArray();
        String path = "";
        Set<String> set = new HashSet<>();
        process02(set, 0, arr, path);
        for (String s : set) {
            res.add(s);
        }
        return res;

    }

    public static void process02(Set<String> ans, int index, char[] arr, String path) {
        if (index == arr.length) {
            ans.add(path);
            return;
        }
        process02(ans, index + 1, arr, path);
        process02(ans, index + 1, arr, path + String.valueOf(arr[index]));
    }

    public static void main(String[] args) {
        String s = "abb";
        List<String> list1 = sub01(s);
        for (String str : list1) {
            System.out.println(str);
        }
        System.out.println("====");
        List<String> list2 = sub02(s);
        for (String str2 : list2) {
            System.out.println(str2);
        }
    }

}
