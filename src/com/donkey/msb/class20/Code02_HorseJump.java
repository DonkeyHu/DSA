package com.donkey.msb.class20;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 */
public class Code02_HorseJump {

    public static int way1(int x, int y, int k) {
        return process(x, y, k, 0, 0);
    }

    public static int process(int x, int y, int rest, int a, int b) {
        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }

        if (a > 8 || b > 9 || a < 0 || b < 0) {
            return 0;
        }

        int p1 = process(x, y, rest - 1, a + 1, b + 2);
        int p2 = process(x, y, rest - 1, a + 2, b + 1);
        int p3 = process(x, y, rest - 1, a + 2, b - 1);
        int p4 = process(x, y, rest - 1, a + 1, b - 2);
        int p5 = process(x, y, rest - 1, a - 1, b - 2);
        int p6 = process(x, y, rest - 1, a - 2, b - 1);
        int p7 = process(x, y, rest - 1, a - 2, b + 1);
        int p8 = process(x, y, rest - 1, a - 1, b + 2);

        return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
    }


    public static int dp(int x, int y, int k) {
        // K的边界条件没搞清楚，弄错了的，以为是K
        int[][][] dp = new int[k + 1][9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                dp[0][i][j] = (x == i && y == j) ? 1 : 0;
            }
        }

        for (int s = 1; s <= k; s++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    int p1 = pick(dp, s - 1, i + 1, j + 2);
                    int p2 = pick(dp, s - 1, i + 2, j + 1);
                    int p3 = pick(dp, s - 1, i + 2, j - 1);
                    int p4 = pick(dp, s - 1, i + 1, j - 2);
                    int p5 = pick(dp, s - 1, i - 1, j - 2);
                    int p6 = pick(dp, s - 1, i - 2, j - 1);
                    int p7 = pick(dp, s - 1, i - 2, j + 1);
                    int p8 = pick(dp, s - 1, i - 1, j + 2);
                    dp[s][i][j] = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
                }
            }
        }

        return dp[k][0][0];
    }


    public static int pick(int[][][] dp, int s, int a, int b) {
        if (a > 8 || b > 9 || a < 0 || b < 0) {
            return 0;
        }
        return dp[s][a][b];
    }


    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int k = 10;
        System.out.println(way1(x, y, k));
        System.out.println(dp(x, y, k));
    }

}
