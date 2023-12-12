package com.donkey.msb.class19;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Code01_Knapsack {

    public static int way1(int[] weight, int[] values, int bag) {
        if (weight == null || weight.length == 0 || values == null || values.length == 0 || values.length != weight.length || bag <= 0) {
            return 0;
        }
        return process1(weight, values, 0, bag);
    }

    public static int process1(int[] weight, int[] values, int index, int bag) {
        if (bag < 0) {
            return -1;
        }
        if (index == weight.length) {
            return 0;
        }
        int p1 = 0;
        // 注意bag - weight[index]小于0这种情况，这种情况说明背包容量不足，则不能拿value了
        int res = process1(weight, values, index + 1, bag - weight[index]);
        if (res != -1) {
            p1 = values[index] + res;
        }
        int p2 = process1(weight, values, index + 1, bag);
        return Math.max(p1, p2);
    }

    public static int dp(int[] weight, int[] values, int bag) {
        if (weight == null || weight.length == 0 || values == null || values.length == 0 || values.length != weight.length || bag <= 0) {
            return 0;
        }
        int N = weight.length;
        int[][] dp = new int[N + 1][bag + 1];

        // (1) 我起初认为bag是不连续的，不能填表。实际可以看做连续的去推，这有点神奇
        // (2) 边界条件
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= bag; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                if (j - weight[i] >= 0) {
                    p2 = values[i] + dp[i + 1][j - weight[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] weight = {3, 5, 6, 7, 2, 11, 8, 9};
        int[] values = {2, 4, 6, 1, 8, 2, 7, 11};
        int bag = 18;
        System.out.println(way1(weight, values, bag));
        System.out.println(dp(weight, values, bag));
    }

}
