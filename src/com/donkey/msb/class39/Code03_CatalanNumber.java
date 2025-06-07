package com.donkey.msb.class39;

/**
 * 卡特兰数最经典的解释是"括号匹配问题"：n对括号的正确匹配方式有多少种？
 * <p>
 * n=1: () → 1种
 * n=2: (()) 和 ()() → 2种
 * n=3: ((())), (()()), (())(), ()(()), ()()() → 5种
 * <p>
 * 这个问题的本质是：从左到右扫描时，任何位置上左括号的数量都不能少于右括号的数量
 */
public class Code03_CatalanNumber {

    public static int way(int n) {
        return process(0, 0, n);
    }

    public static int process(int index, int lsum, int rsum, int n) {
        if (index == 2 * n - 1) {
            return lsum == n && rsum == n ? 1 : 0;
        }

        if (rsum > lsum) {
            return 0;
        }

        return process(index + 1, lsum + 1, rsum, n) + process(index + 1, lsum, rsum + 1, n);
    }

    public static int process(int lsum, int rsum, int n) {
        if (lsum == n && rsum == n) {
            return 1;
        }

        if (rsum > lsum) {
            return 0;
        }
        int totalWays = 0;

        if (lsum < n) {
            totalWays += process(lsum + 1, rsum, n);
        }

        if (rsum < lsum) {
            totalWays += process(lsum, rsum + 1, n);
        }

        return totalWays;
    }


    // DP
    public static int way2(int n) {
        // dp[i][j] 已放置i个左括号和j个右括号的方法数
        int[][] dp = new int[n + 1][n + 1];

        dp[n][n] = 1;

        for (int i = n ; i >= 0; i--) {
            for (int j = n ; j >= 0; j--) {
                // 跳过已初始化的终点状态(n, n)
                if (i == n && j == n) continue;

                int ways = 0;
                // 选择增加左和：若l < n，则转移到(l+1, r)
                if (i < n) {
                    ways += dp[i+1][j];
                }
                // 选择增加右和：需满足r < l且r < n，则转移到(l, r+1)
                if (j < i && j < n) {
                    ways += dp[i][j+1];
                }
                dp[i][j] = ways;
            }
        }

        return dp[0][0];
    }


    public static void main(String[] args) {
        int n = 7;
        System.out.println(way(n));
        System.out.println(way2(n));
    }

}
