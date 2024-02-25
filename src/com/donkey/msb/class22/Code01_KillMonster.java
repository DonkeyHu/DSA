package com.donkey.msb.class22;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class Code01_KillMonster {

    public static double way1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        return (double) process(K, N, M) / Math.pow(M + 1, K);
    }


    public static long process(int times, int blood, int M) {
        if (times == 0) {
            return blood <= 0 ? 1 : 0;
        }
        // 这种case没有考虑到，提前结束，需要把后续分支都加上
        if (blood <= 0) {
            return (long) Math.pow(M + 1, times);
        }
        int way = 0;
        for (int i = 0; i <= M; i++) {
            way += process(times - 1, blood - i, M);
        }
        return way;
    }

    public static double way2(int blood, int M, int times) {
        if (blood < 1 || M < 1 || times < 1) {
            return 0;
        }

        long[][] dp = new long[times + 1][blood + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= times; i++) {
            dp[i][0] = (long) Math.pow(M + 1, i); //参数一定要小心....这里一不小心就写成Math.pow(M + 1, times)
        }
        for (int i = 1; i <= times; i++) {
            for (int j = 1; j <= blood; j++) {
                int way = 0;
                for (int x = 0; x <= M; x++) {
                    if (j - x <= 0) {
                        way += dp[i - 1][0];
                    } else {
                        way += dp[i - 1][j - x];
                    }
                }
                dp[i][j] = way;
            }
        }
        return ((double) dp[times][blood]) / Math.pow(M + 1, times);
    }

    public static double way3(int blood, int M, int times) {
        if (blood < 1 || M < 1 || times < 1) {
            return 0;
        }

        long[][] dp = new long[times + 1][blood + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= times; i++) {
            dp[i][0] = (long) Math.pow(M + 1, i); //参数一定要小心....这里一不小心就写成Math.pow(M + 1, times)
        }
        for (int i = 1; i <= times; i++) {
            for (int j = 1; j <= blood; j++) {
                if (j - 1 >= M) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - M - 1];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][0]; //太容易犯错了，思路没错，dp[i - 1][0]不小心写成dp[i][0]
                }
            }
        }
        return ((double) dp[times][blood]) / Math.pow(M + 1, times);
    }


    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = way1(N, M, K);
            double ans2 = way2(N, M, K);
            double ans3 = way3(N, M, K);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");

    }
}
