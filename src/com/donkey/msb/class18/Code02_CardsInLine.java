package com.donkey.msb.class18;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 */

public class Code02_CardsInLine {

    public static int way1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int f1 = f1(arr, 0, arr.length - 1);
        int g1 = g1(arr, 0, arr.length - 1);
        return Math.max(g1, f1);
    }

    public static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    public static int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        // 这里为什么这样写必然有很强大的逻辑我还没理解。先手拿走了L或者R，后手就变成了新手
        int p1 = f1(arr, L + 1, R);
        int p2 = f1(arr, L, R - 1);
        return Math.min(p1, p2);
    }


    public static int way2(int[] arr) {
        int N = arr.length;
        if (arr == null || N == 0) {
            return 0;
        }
        int[][] dpf = new int[N][N];
        int[][] dpg = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dpf[i][j] = -1;
                dpg[i][j] = -1;
            }
        }
        int p1 = f2(arr, 0, N - 1, dpf, dpg);
        int p2 = g2(arr, 0, N - 1, dpf, dpg);
        return Math.max(p1, p2);
    }

    public static int f2(int[] arr, int L, int R, int[][] dpf, int[][] dpg) {
        if (dpf[L][R] != -1) {
            return dpf[L][R];
        }
        int ans;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, dpf, dpg);
            int p2 = arr[R] + g2(arr, L, R - 1, dpf, dpg);
            ans = Math.max(p1, p2);
        }
        dpf[L][R] = ans;
        return ans;
    }

    public static int g2(int[] arr, int L, int R, int[][] dpf, int[][] dpg) {
        if (dpg[L][R] != -1) {
            return dpg[L][R];
        }
        int ans;
        if (L == R) {
            ans = 0;
        } else {
            int p1 = f2(arr, L + 1, R, dpf, dpg);
            int p2 = f2(arr, L, R - 1, dpf, dpg);
            ans = Math.min(p1, p2);
        }
        dpg[L][R] = ans;
        return ans;
    }


    public static int way3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];

        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }
        //如何构建这个三角形想了好久没想出来，心思杂了
        for (int col = 1; col < N; col++) {
            int L = 0;
            int R = col;

            while (R < N) {
                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                gmap[L][R] = Math.min(fmap[L][R - 1], fmap[L + 1][R]);
                L++;
                R++;
            }
        }
        return Math.max(fmap[0][N-1], gmap[0][N-1]);
    }


    public static void main(String[] args) {
        int[] input = {1, 2, 100, 4};
        System.out.println(way1(input));
        System.out.println(way2(input));
        System.out.println(way3(input));
    }

}
