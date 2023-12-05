package com.donkey.msb.class18;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class Code01_RobotWalk {

    public static int way1(int N, int M, int K, int P) {
        if (N <= 1 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        return process1(K, M, P, N);
    }

    public static int process1(int rest, int cur, int tar, int N) {
        if (rest == 0) {
            return cur == tar ? 1 : 0;
        }
        if (cur == 1) {
            return process1(rest - 1, 2, tar, N);
        }
        if (cur == N) {
            return process1(rest - 1, N - 1, tar, N);
        }
        return process1(rest - 1, cur + 1, tar, N) + process1(rest - 1, cur - 1, tar, N);
    }

    public static int way2(int N, int M, int K, int P) {
        if (N <= 1 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        int[][] cache = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                cache[i][j] = -1;
            }
        }
        return process2(K, M, P, N, cache);
    }


    public static int process2(int rest, int cur, int tar, int N, int[][] cache) {
        if (cache[cur][rest] != -1)
            return cache[cur][rest];

        int ans = 0;
        if (rest == 0) {
            ans = cur == tar ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(rest - 1, 2, tar, N, cache);
        } else if (cur == N) {
            ans = process2(rest - 1, N - 1, tar, N, cache);
        } else {
            ans = process2(rest - 1, cur + 1, tar, N, cache) + process2(rest - 1, cur - 1, tar, N, cache);
        }
        cache[cur][rest] = ans;
        return ans;
    }

    public static int way3(int N, int M, int K, int P) {
        if (N <= 1 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        int[][] cache = new int[N + 1][K + 1];
        cache[P][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            cache[1][rest] = cache[2][rest - 1];
            for (int i = 2; i < N; i++) {
                cache[i][rest] = cache[i - 1][rest - 1] + cache[i + 1][rest - 1];
            }
            cache[N][rest] = cache[N - 1][rest - 1];
        }
        return cache[M][K];
    }


    public static void main(String[] args) {
        System.out.println(way1(6, 2, 4, 4));
        System.out.println(way2(6, 2, 4, 4));
        System.out.println(way3(6, 2, 4, 4));
    }


}
