package com.donkey.msb.class26;

/**
 * 尝试用o(logN)的复杂度计算Fibonacci问题
 */
public class Code02_FibonacciProblem {

    public static int f1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public static int f2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int pre = 1;
        int res = 0;
        int prepre = 1;
        for (int i = 3; i <= n; i++) {
            res = prepre + pre;
            prepre = pre;
            pre = res;
        }
        return res;
    }

    // o(logn)
    public static int f3(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, t);
            }
            t = multiMatrix(t, t);
        }
        return res;
    }

    // 求两矩阵相乘得到的结果
    public static int[][] multiMatrix(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int ans = 0;
                for (int l = 0; l < k; l++) {
                    ans += a[i][l] * b[l][j];
                }
                res[i][j] = ans;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 19;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
    }
}
