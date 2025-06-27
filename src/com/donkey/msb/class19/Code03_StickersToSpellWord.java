package com.donkey.msb.class19;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 *
 * 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
 */
public class Code03_StickersToSpellWord {

    public static int way1(String target, String[] arr) {
        int ans = process1(target, arr);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 什么时候代表用了一张贴纸了？
    // (1) 每递归process一次用掉一次贴纸
    // (2) 一定是target.length()==0的递归才是有效解的递归
    // (3) for循环里面(i)与(i+1)之间的关系是或的关系，意味着你要么用了(i)的贴纸数，要么是(i+1)的贴纸数，(i)与(i+1)之间是要比较的，取最小值
    public static int process1(String target, String[] arr) {
        if (target.length() == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            String res = minis(target, arr[i]);
            if (res.length() != target.length()) {
                min = Math.min(min, process1(res, arr));
            }
        }
        // 这里的1代表刚开始就用了一张贴纸
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    // 里面还是存在一些小技巧的，字符相减，字符加数字
    public static String minis(String rest, String sticker) {
        char[] c1 = rest.toCharArray();
        char[] c2 = sticker.toCharArray();
        int[] tmp = new int[26];
        for (char c : c1) {
            tmp[c - 'a'] += 1;
        }
        for (char c : c2) {
            tmp[c - 'a'] -= 1;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
            while (tmp[i] > 0) {
                res.append((char) (i + 'a'));
                tmp[i] -= 1;
            }
        }
        return res.toString();
    }


    public static int way2(String target, String[] stickers) {
        int length = stickers.length;
        int[][] arr = new int[length][26];
        for (int i = 0; i < length; i++) {
            String sticker = stickers[i];
            char[] chars = sticker.toCharArray();
            for (char c : chars) {
                arr[i][c - 'a'] += 1;
            }
        }
        int ans = process2(target, arr);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public static int process2(String target, int[][] arr) {
        if (target.length() == 0) {
            return 0;
        }
        char[] cTarget = target.toCharArray();
        int[] tmp = new int[26];
        for (char c : cTarget) {
            tmp[c - 'a'] += 1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int[] sticker = arr[i];
            // 包含target的首字母再去minis
            if (sticker[cTarget[0] - 'a'] > 0) {
                StringBuilder res = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tmp[j] > 0) {
                        int num = tmp[j] - sticker[j];
                        for (int k = 0; k < num; k++) {
                            res.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = res.toString();
                if (rest.length() != target.length()) {
                    min = Math.min(min, process2(rest, arr));
                }
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int way3(String target, String[] stickers) {
        int length = stickers.length;
        int[][] arr = new int[length][26];
        for (int i = 0; i < length; i++) {
            String sticker = stickers[i];
            char[] chars = sticker.toCharArray();
            for (char c : chars) {
                arr[i][c - 'a'] += 1;
            }
        }
        Map<String, Integer> cache = new HashMap<>();
        int ans = process3(target, arr, cache);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(String target, int[][] arr, Map<String, Integer> cache) {
        if (cache.containsKey(target)) {
            return cache.get(target);
        }
        if (target.length() == 0) {
            return 0;
        }
        char[] cTarget = target.toCharArray();
        int[] tmp = new int[26];
        for (char c : cTarget) {
            tmp[c - 'a'] += 1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int[] sticker = arr[i];
            // 包含target的首字母再去minis
            if (sticker[cTarget[0] - 'a'] > 0) {
                StringBuilder res = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tmp[j] > 0) {
                        int num = tmp[j] - sticker[j];
                        for (int k = 0; k < num; k++) {
                            res.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = res.toString();
                if (rest.length() != target.length()) {
                    min = Math.min(min, process3(rest, arr,cache));
                }
            }
        }
        int x = min + (min == Integer.MAX_VALUE ? 0 : 1);
        cache.put(target, x);
        return x;
    }


    public static void main(String[] args) {
        String str = "babacabcabcabc";
        String[] arr = {"ba", "c", "abcd"};
        System.out.println(way1(str, arr));
        System.out.println(way2(str, arr));
        System.out.println(way3(str, arr));
    }
}
