package com.donkey.leetcode;

/**
 * 乍一看会用到递归+一些条件判断
 * <p>
 * 一些边界条件没系统性搞清，特别是对于0这种情况 --> 没做出来，思维不够清晰，str[index,...]意味着前面不用管了，所以这里对0判断即可
 */
public class Q091_DecodeWays {

    public static int numDecodings(String s) {
        char[] str = s.toCharArray();
        return process(0, str);
    }


    public static int process(int index, char[] str) {
        if (index == str.length) {
            return 1;
        }

        if (str[index] == '0') {
            return 0;
        }
        // 当前这个数：一个数判断
        int ways = process(index + 1, str);

        // 当前这个数和后面一个数合为整体
        if (index + 1 > str.length - 1) {
            return ways;
        }
        int num = (str[index] - '0') * 10 + (str[index + 1] - '0');
        if (num <= 26) {
            ways += process(index + 2, str);
        }
        return ways;
    }

    public static int numDecodings2(String s) {
        int len = s.length();
        char[] str = s.toCharArray();
        int[] dp = new int[len + 1];
        dp[len] = 1;

        for (int i = len - 1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
                continue;
            }
            dp[i] = dp[i + 1];
            if (i + 1 > str.length - 1) {
                continue;
            }
            int num = (str[i] - '0') * 10 + (str[i + 1] - '0');
            if (num <= 26) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        String s = "12";
        System.out.println(numDecodings2(s));
    }

}
