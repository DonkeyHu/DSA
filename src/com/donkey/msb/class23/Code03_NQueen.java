package com.donkey.msb.class23;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class Code03_NQueen {

    public static int way(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    // int[] record 代表皇后记录的位置
    // record[x] = y 之前的第x行的皇后，放在了y列上
    public static int process(int row, int[] record, int n) {
        if (row == n) {
            return 1;
        }
        int res = 0;
        for (int column = 0; column < n; column++) {
            if (isValid(row, column, record)) {
                record[row] = column;
                res += process(row + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int row, int column, int[] record) {
        // 这里有关键的一步 i < row, 不是i < record.length, 因为上面是没有对回溯进行处理的
        for (int i = 0; i < row; i++) {
            if (column == record[i] || Math.abs(record[i] - column) == (row - i)) {
                return false;
            }
        }
        return true;
    }

    // 请不要超过32皇后问题
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果你是13皇后问题，limit 最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 8;
        System.out.println(way(n));
        System.out.println(num2(n));
    }
}
