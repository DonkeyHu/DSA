package com.donkey.msb.class19;

/**
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * <p>
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 *
 * 链接：https://leetcode.com/problems/longest-common-subsequence/
 */
public class Code04_LongCommonSubSequence {

    public static int way1(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();
        return process1(arr1, arr2, arr1.length - 1, arr2.length - 1);
    }

    // 怎么想到转换成arr1[0...i]与arr2[0...j]的最大公共子序列的
    public static int process1(char[] arr1, char[] arr2, int i, int j) {
        if (i == 0 && j == 0) {
            return arr1[i] == arr2[j] ? 1 : 0;
        } else if (i == 0) {
            if (arr1[i] == arr2[j]) {
                return 1;
            } else {
                return process1(arr1, arr2, i, j - 1);
            }
        } else if (j == 0) {
            if (arr1[i] == arr2[j]) {
                return 1;
            } else {
                return process1(arr1, arr2, i - 1, j);
            }
        } else {
            // 样本对应模型，讨论结尾的可能性
            int p1 = process1(arr1, arr2, i - 1, j);
            int p2 = process1(arr1, arr2, i, j - 1);
            int p3 = arr1[i] == arr2[j] ? (1 + process1(arr1, arr2, i - 1, j - 1)) : 0;
            // 其实有种可能是 int p4 = process1(arr1,arr2,i-1,j-1) 但p4一定小于p3的，所以
            //int p3 = 1+ process1(arr1,arr2,i-1,j-1);
            return Math.max(p3, Math.max(p1, p2));
        }
    }

    public static int way2(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();

        int N = arr1.length;
        int M = arr2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = arr1[0] == arr2[0] ? 1 : 0;

        // 傻逼了，填充不了，应该j=1开始来推
        for (int j = 1; j < M; j++) {
            dp[0][j] = arr1[0] == arr2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = arr1[i] == arr2[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = arr1[i] == arr2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p3, Math.max(p1, p2));
            }
        }
        return dp[N - 1][M - 1];
    }

    // 注意，这种方法没有真正理解什么是子序列，子序列是有顺序的。 text1 = "ezupkr"，text2 ="ubmrapg"，输出=3，预期结果=2
    public static int compare(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        StringBuilder builder = new StringBuilder();
        int len = s2.length();
        for (char c1 : chars1) {
            s2 = removeChar(c1, s2);
            if (s2.length() != len) {
                len = s2.length();
                builder.append(c1);
            }
        }
        String s = builder.toString();
        return s.length();
    }

    public static String removeChar(char c, String s) {
        char[] arr = s.toCharArray();
        char[] res = new char[s.length() - 1];
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (c == arr[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return s;
        } else {
            int x = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i != index) {
                    res[x++] = arr[i];
                }
            }
            return String.valueOf(res);
        }
    }


    public static void main(String[] args) {
        System.out.println(compare("a122b3c4567d", "1ef23ghi4j56k"));
        System.out.println(way1("a122b3c4567d", "1ef23ghi4j56k"));
        System.out.println(way2("a122b3c4567d", "1ef23ghi4j56k"));
    }

}
