package com.donkey.msb.class21;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class Code05_BobDie {


    public static double way1(int N, int M, int row, int col, int k) {
        return (double) process(N, M, row, col, k) / Math.pow(4, k);
    }

    public static int process(int N, int M, int row, int col, int k) {
        if (row > N || row < 0 || col > M || col < 0) {
            return 0;
        }


        if (k == 0) {
            return 1;
        }

        int p1 = process(N, M, row + 1, col, k - 1);
        int p2 = process(N, M, row - 1, col, k - 1);
        int p3 = process(N, M, row, col + 1, k - 1);
        int p4 = process(N, M, row, col - 1, k - 1);
        return p1 + p2 + p3 + p4;
    }

    public static double way2(int N, int M, int row, int col, int k) {
        int[][][] dp = new int[N + 1][M + 1][k + 1];

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M + 1; j++) {
                dp[i][j][0] = 1;
            }
        }
        // 特别要注意符号呀，用着用着h就变成k了
        for (int h = 1; h < k + 1; h++) {
            for (int i = 0; i < N + 1; i++) {
                for (int j = 0; j < M + 1; j++) {
                    dp[i][j][h] = pick(dp, i + 1, j, h - 1, N, M) +
                            pick(dp, i - 1, j, h - 1, N, M) +
                            pick(dp, i, j + 1, h - 1, N, M) +
                            pick(dp, i, j - 1, h - 1, N, M);
                }
            }
        }

        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    public static int pick(int[][][] dp, int row, int col, int k, int N, int M) {
        if (row > N || row < 0 || col > M || col < 0) {
            return 0;
        }
        return dp[row][col][k];
    }


    public static void main(String[] args) {
        // 这是异或运算不是次方运算
        System.out.println(3 ^ 4);
        System.out.println(Math.pow(3, 4));

        System.out.println(way1(10, 10, 6, 6, 10));
        System.out.println(way2(10, 10, 6, 6, 10));
    }
}
