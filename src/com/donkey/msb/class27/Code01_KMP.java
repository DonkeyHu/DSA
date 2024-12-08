package com.donkey.msb.class27;

public class Code01_KMP {


    public static int kmp(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s2.length() > s1.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = next(str2);
        // s1主串位置
        int x = 0;
        // s2子串位置
        int y = 0;
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (y == 0) { // 这里意味着子串位置到起始位置0，仍然和s1 x位置不匹配，那么x位置可以往下挪动了
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public static int[] next(char[] str) {
        if (str.length == 1) {
            return new int[]{-1};
        }
        int[] res = new int[str.length];
        res[0] = -1;
        res[1] = 0;
        // index代表需要求的next数组值的下表
        int index = 2;
        // 这是index-1位置的next数组的值，也是需要和index-1比较是否相等的下标
        // 明确next数组的含义，是指index前面所有字符的最长前缀后缀匹配，所以是cn与index-1比较
        int cn = 0;
        while (index < str.length) {
            if (str[index - 1] == str[cn]) {
                res[index++] = ++cn;
            } else if (res[cn] != -1) {
                cn = res[cn];
            } else {
                res[index++] = 0;
            }
        }
        return res;
    }


    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }


    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (kmp(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
