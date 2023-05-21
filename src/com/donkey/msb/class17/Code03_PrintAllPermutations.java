package com.donkey.msb.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class Code03_PrintAllPermutations {

    public static void process1(List<Character> list, String path, List<String> ans) {
        if (list.isEmpty()) {
            ans.add(path);
        } else {
            for (int i = 0; i < list.size(); i++) {
                char cur = list.get(i);
                list.remove(i);
                process1(list, path + cur, ans);
                // 恢复现场
                list.add(i, cur);
            }
        }
    }

    public static List<String> permutation1(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c : chars) {
            list.add(c);
        }
        process1(list, "", ans);
        return ans;
    }

    public static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void process2(char[] arr, int index, List<String> ans) {
        if (index == arr.length) {
            ans.add(String.valueOf(arr));
        } else {
            for (int i = index; i < arr.length; i++) {
                swap(arr, i, index);
                process2(arr, index + 1, ans);
                swap(arr, i, index);
            }
        }
    }

    public static List<String> permutation2(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        process2(chars, 0, ans);
        return ans;
    }

    public static void process3(char[] arr, int index, List<String> ans) {
        if (index == arr.length) {
            ans.add(String.valueOf(arr));
        } else {
            boolean[] visit = new boolean[256];
            for (int i = index; i < arr.length; i++) {
                if (!visit[arr[i]]) {
                    visit[arr[i]] = true;
                    swap(arr, i, index);
                    process3(arr, index + 1, ans);
                    swap(arr, i, index);
                }

            }
        }
    }

    public static List<String> permutation3(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        process3(chars, 0, ans);
        return ans;
    }

    public static void main(String[] args) {
        String s = "abc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }
    }


}
