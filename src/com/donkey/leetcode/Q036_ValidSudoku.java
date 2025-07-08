package com.donkey.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 36. 有效的数独
 */
public class Q036_ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        int len = board.length;

        // 判断行
        for (int i = 0; i < len; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < len; j++) {
                char x = board[i][j];
                if (x != '.' && set.contains(x)) {
                    return false;
                } else {
                    set.add(x);
                }
            }
        }

        // 判断列
        for (int j = 0; j < len; j++) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < len; i++) {
                char x = board[i][j];
                if (x != '.' && set.contains(x)) {
                    return false;
                } else {
                    set.add(x);
                }
            }
        }

        int r = 0;
        while (r < 3) {
            int c = 0;
            while (c < 3) {
                Set<Character> set = new HashSet<>();
                for (int i = r * 3; i < r * 3 + 3; i++) {
                    for (int j = c * 3; j < c * 3 + 3; j++) {
                        char x = board[i][j];
                        if (x != '.' && set.contains(x)) {
                            return false;
                        } else {
                            set.add(x);
                        }
                    }
                }
                c++;
            }
            r++;
        }
        return true;
    }


    public boolean isValidSudoku2(char[][] board) {
        int len = board.length;
        boolean[][] col = new boolean[9][10];
        boolean[][] row = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int b = (i / 3) * 3 + j / 3;
                    if (col[i][num] || row[j][num] || bucket[b][num]) {
                        return false;
                    }
                    col[i][num] = true;
                    row[j][num] = true;
                    bucket[b][num] = true;
                }
            }
        }
        return true;
    }


}
