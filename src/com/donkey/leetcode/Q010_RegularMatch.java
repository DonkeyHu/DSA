package com.donkey.leetcode;

/**
 * 10. 正则表达式匹配
 */
public class Q010_RegularMatch {

    // 这是我面对题目自己写的一版代码，完全没往子问题（递归）方面想，怎么搞都搞不出来
    public static boolean isMatch(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();

        int sLen = sChars.length;
        int pLen = pChars.length;
        int s1 = 0;
        int p1 = 0;
        // "aab","c*a*b"
        while (s1 < sLen && p1 < pLen) {
            if (sChars[s1] == pChars[p1]) {
                s1++;
                p1++;
            } else {
                char pChar = pChars[p1];
                if (pChar == '.') {
                    s1++;
                    p1++;
                } else if (pChar == '*') {
                    if (s1 == 0 || p1 == 0) {
                        return false;
                    }
                    if (sChars[s1 - 1] != pChars[p1 - 1]) {
                        p1++;
                    } else {
                        if (sChars[s1 - 1] == sChars[s1]) {
                            s1++;
                        }
                        // 写不下去了，这里的条件分析不清楚了
                        p1++;
//                        if (sChars[s1 - 1] == sChars[s1]) {
//                            s1++;
//                        } else {
//                            if (pChars[p1-1] == '.'){
//                                s1++;
//                            }else {
//                                return false;
//                            }
//                        }
                    }
                } else {
                    p1++;
                }
            }
        }
        if (s1 < sLen) {
            return false;
        }
        return true;
    }


    public static boolean isMatch2(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        if (!isValid(sChars, pChars)) {
            return false;
        }
        return process(sChars, pChars, 0, 0);
    }

    public static boolean isValid(char[] sChars, char[] pChars) {
        for (int i = 0; i < sChars.length; i++) {
            if (sChars[i] == '*' || sChars[i] == '.') {
                return false;
            }
        }
        for (int i = 0; i < pChars.length; i++) {
            if (i == 0 && pChars[i] == '*') {
                return false;
            }
            if ((i + 1) < pChars.length && pChars[i] == '*' && pChars[i + 1] == '*') {
                return false;
            }
        }
        return true;
    }

    // 把问题分解为子问题
    // 在sChars数组中[s...]和pChars数组中[p...]是否匹配，前面的就不用管了
    // 注意这题意：a* -> 这类可以相当于是空串的
    public static boolean process(char[] sChars, char[] pChars, int s, int p) {
        // base case
        if (s == sChars.length) {
            if (p == pChars.length) {
                return true;
            } else {
                if ((p + 1) < pChars.length && pChars[p + 1] == '*') {
                    return process(sChars, pChars, s, p + 2);
                } else {
                    return false;
                }
            }
        }

        if (p == pChars.length) {
            return s == sChars.length;
        }

        // 正式开始判断，按照子问题分析p是不能问*的
        // abcdab
        // a*ab
        if ((p + 1) < pChars.length && pChars[p + 1] != '*') {
            if (sChars[s] == pChars[p] || pChars[p] == '.') {
                return process(sChars, pChars, s + 1, p + 1);
            } else {
                return false;
            }
        }

        // 第二位数为*时
        if ((p + 1) < pChars.length && pChars[p + 1] == '*') {
            if (sChars[s] != pChars[p] && pChars[p] != '.') {
                return process(sChars, pChars, s, p + 2);
            } else if (sChars[s] == pChars[p]) {
                // 这种写法是错误的，比如这个例子："aaa","a*a"
//                while (s + 1 < sChars.length && sChars[s] == sChars[s + 1]) {
//                    s++;
//                }
//                return process(sChars, pChars, s + 1, p + 2);

                // "aaa","a*a"
                // [s,s,i,p,p,i], [ s,*,p,*,.]
                // a, a*a

                // 这种case，我想了很久的边界条件，总没对，原来可以先if条件判断一下头的位置，后面就很丝滑了
                if (process(sChars, pChars, s, p + 2)) {
                    return true;
                }

                while (s < sChars.length && sChars[s] == pChars[p]) {
                    if (process(sChars, pChars, s + 1, p + 2)) {
                        return true;
                    }
                    s++;
                }
                return false;
            } else {
                // 如果是.*这种，应该怎么挪动位置呢?
                // ab
                // .*c
                while (s <= sChars.length) {
                    if (process(sChars, pChars, s, p + 2)) {
                        return true;
                    }
                    s++;
                }
                return false;
            }
        }

        // p = pChars.length-1
        if (s == sChars.length - 1 && (sChars[s] == pChars[p] || pChars[p] == '.')) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean process2(char[] sChars, char[] pChars, int s, int p, int[][] dp) {
        if (dp[s][p] != -1) {
            return dp[s][p] == 1;
        }

        // base case
        if (s == sChars.length) {
            if (p == pChars.length) {
                dp[s][p] = 1;
                return true;
            } else {
                if ((p + 1) < pChars.length && pChars[p + 1] == '*') {
                    boolean ans = process2(sChars, pChars, s, p + 2, dp);
                    dp[s][p] = ans ? 1 : 0;
                    return ans;
                } else {
                    dp[s][p] = 0;
                    return false;
                }
            }
        }

        if (p == pChars.length) {
            boolean ans = s == sChars.length;
            dp[s][p] = ans ? 1 : 0;
            return ans;
        }

        // 正式开始判断，按照子问题分析p是不能问*的
        // abcdab
        // a*ab
        if ((p + 1) < pChars.length && pChars[p + 1] != '*') {
            if (sChars[s] == pChars[p] || pChars[p] == '.') {
                boolean ans = process2(sChars, pChars, s + 1, p + 1, dp);
                dp[s][p] = ans ? 1 : 0;
                return ans;
            } else {
                dp[s][p] = 0;
                return false;
            }
        }

        // 第二位数为*时
        if ((p + 1) < pChars.length && pChars[p + 1] == '*') {
            if (sChars[s] != pChars[p] && pChars[p] != '.') {
                boolean ans = process2(sChars, pChars, s, p + 2, dp);
                dp[s][p] = ans ? 1 : 0;
                return ans;
            } else if (sChars[s] == pChars[p]) {
                if (process2(sChars, pChars, s, p + 2, dp)) {
                    dp[s][p] = 1;
                    return true;
                }

                while (s < sChars.length && sChars[s] == pChars[p]) {
                    if (process2(sChars, pChars, s + 1, p + 2,dp)) {
                        dp[s][p] = 1;
                        return true;
                    }
                    s++;
                }
                dp[s][p] = 0;
                return false;
            } else {
                while (s <= sChars.length) {
                    if (process2(sChars, pChars, s, p + 2, dp)) {
                        dp[s][p] = 1;
                        return true;
                    }
                    s++;
                }
                if (s <= sChars.length) {
                    dp[s][p] = 0;
                }
                return false;
            }
        }

        // p = pChars.length-1
        if (s == sChars.length - 1 && (sChars[s] == pChars[p] || pChars[p] == '.')) {
            dp[s][p] = 1;
            return true;
        } else {
            dp[s][p] = 0;
            return false;
        }
    }

    public static boolean isMatch3(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        if (!isValid(sChars, pChars)) {
            return false;
        }
        int[][] dp = new int[sChars.length + 1][pChars.length + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        return process2(sChars, pChars, 0, 0, dp);
    }


    public static void main(String[] args) {
        System.out.println(isMatch3("ab", ".*c"));
    }

}
