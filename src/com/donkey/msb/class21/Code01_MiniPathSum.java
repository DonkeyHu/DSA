package com.donkey.msb.class21;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class Code01_MiniPathSum {

    public static int way1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        return process(m, row, col, 0, 0);
    }

    // 递归停的讨论不够充足
    public static int process(int[][] matrix, int R, int C, int x, int y) {
//        if (x >= R || y >= C) {
//            return 0;
//        }

        if (x == R - 1 && y == C - 1) {
            return matrix[x][y];
        }

        if (x == R - 1) {
            return matrix[x][y] + process(matrix, R, C, x, y + 1);
        }

        if (y == C - 1) {
            return matrix[x][y] + process(matrix, R, C, x + 1, y);
        }

        int p1 = matrix[x][y] + process(matrix, R, C, x + 1, y);
        int p2 = matrix[x][y] + process(matrix, R, C, x, y + 1);
        return Math.min(p1, p2);
    }

    public static int way2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int r = m.length;
        int c = m[0].length;
        int[][] dp = new int[r][c];
        dp[r - 1][c - 1] = m[r - 1][c - 1];

        for (int x = r - 2; x >= 0; x--) {
            dp[x][c - 1] = dp[x + 1][c - 1] + m[x][c - 1];
        }

        for (int y = c - 2; y >= 0; y--) {
            dp[r - 1][y] = dp[r - 1][y + 1] + m[r - 1][y];
        }

        for (int x = r - 2; x >= 0; x--) {
            for (int y = c - 2; y >= 0; y--) {
                dp[x][y] = m[x][y] + Math.min(dp[x + 1][y], dp[x][y + 1]);
            }
        }

        return dp[0][0];
    }

    public static int way3(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int r = m.length;
        int c = m[0].length;
        int[][] dp = new int[r][c];
        dp[0][0] = m[0][0];
        for (int y = 1; y < c; y++) {
            dp[0][y] = m[0][y] + dp[0][y - 1];
        }
        for (int x = 1; x < r; x++) {
            dp[x][0] = m[x][0] + dp[x - 1][0];
        }
        for (int x = 1; x < r; x++) {
            for (int y = 1; y < c; y++) {
                dp[x][y] = m[x][y] + Math.min(dp[x - 1][y], dp[x][y - 1]);
            }
        }
        return dp[r - 1][c - 1];
    }

    public static int way4(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int r = m.length;
        int c = m[0].length;
        int[] dp = new int[c];
        // r==0
        dp[0] = m[0][0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + m[0][i];
        }

        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int tmp = dp[j];
                if (j == 0) {
                    dp[j] = tmp + m[i][j];
                } else {
                    dp[j] = Math.min(tmp, dp[j - 1]) + m[i][j];
                }
            }
        }
        return dp[c - 1];
    }

    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(way4(m));
        System.out.println(way3(m));
        System.out.println(way2(m));
        System.out.println(way1(m));
    }

}
