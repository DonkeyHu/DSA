package com.donkey.leetcode;

/**
 * 我对含*号的patter不知道怎么去处理
 * <p>
 * 如果是从左往右模型：abc  *d  这种类型该如何判断
 * 如果是样本对应模型：abc  d*  这种类型该如何判断
 * <p>
 * 噢，原来这确实是最难理解的一点，要用for循环里面去递归判断...
 */
public class Q044_WildCardMatch {

    public boolean isMatch(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        return process(sArr, pArr, 0, 0);
    }

    public boolean process(char[] s, char[] p, int si, int pi) {
        if (si == s.length) {
            if (pi == p.length) {
                return true;
            } else {
                return p[pi] == '*' && process(s, p, si, pi + 1);
            }
        }

        if (pi == p.length) {
            return si == s.length;
        }

        if (p[pi] != '*' && p[pi] != '?') {
            return p[pi] == s[si] && process(s, p, si + 1, pi + 1);
        }

        if (p[pi] == '?') {
            return process(s, p, si + 1, pi + 1);
        }

        // 讨论p[pi]为*这种情况了， 假设s[7,8,9,10], len=11
        for (int i = 0; i <= s.length - si; i++) {
            if (process(s, p, si + i, pi + 1)) {
                return true;
            }
        }
        return false;
    }

    // 忘记加continue了，该死！
    public boolean isMatch2(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[sLen][pLen] = true;
        for (int i = pLen - 1; i >= 0; i--) {
            dp[sLen][i] = pArr[i] == '*' && dp[sLen][i + 1];
        }

        for (int i = sLen - 1; i >= 0; i--) {
            for (int j = pLen - 1; j >= 0; j--) {

                if (pArr[j] != '*' && pArr[j] != '?') {
                    dp[i][j] = pArr[j] == sArr[i] && dp[i + 1][j + 1];
                    continue;
                }

                if (pArr[j] == '?') {
                    dp[i][j] = dp[i + 1][j + 1];
                    continue;
                }

                for (int k = 0; k <= sLen - i; k++) {
                    if (dp[i + k][j + 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }


        return dp[0][0];
    }


}
