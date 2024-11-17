package com.donkey.msb.class26;

/**
 * 一个人可以一次往上迈1个台阶，也可以迈2个台阶
 * 返回这个人迈上N级台阶的方法数
 * <p>
 * 理解这个问题：F(N) = F(N-1) + F(N-2)
 * F(1) = 1
 * F(2) = 2
 * <p>
 * 想想递推的关系，F(N)的台阶方法数不就是 ：F(N-2)方法数 + 迈2个台阶， F(N-1)方法数 + 迈1个台阶。这里有个思维逻辑的训练推导
 */
public class Code03_StepProblem {


    public static int f1(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return f1(n - 1) + f1(n - 2);
    }

    // fibonacci 解法
    public static int f2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[][] base = {
                {1, 1},
                {1, 0}
        };

        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] base, int p) {
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        int[][] t = base;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = matrixMulti(res, t);
            }
            t = matrixMulti(t, t);
        }
        return res;
    }


    public static int[][] matrixMulti(int[][] a, int[][] b) {
        int r = a.length;
        int c = b[0].length;
        int k = a[0].length;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int l = 0; l < k; l++) {
                    res[i][j] += a[i][l] * b[l][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println("===");
    }
}
