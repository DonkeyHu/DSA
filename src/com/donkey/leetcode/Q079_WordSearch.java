package com.donkey.leetcode;

/**
 * 我的思路是先找到首字母，然后再上下左右去遍历
 *
 * 我的思路是对的.....但是少考虑一种情况，遍历下去以后是不能回退的，比如"ABCB"，这应该是false，但是我却输出了true
 *                 {'A', 'B', 'C', 'E' },
 *                 {'S', 'F', 'C', 'S' },
 *                 {'A', 'D', 'E', 'E' }
 */
public class Q079_WordSearch {

    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == words[0]) {
                    boolean b = process(i, j, m, n, board, words, 0);
                    if (b) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static boolean process(int i, int j, int m, int n, char[][] board, char[] words, int index) {
        if (index == words.length) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }

        if (board[i][j] != words[index]) {
            return false;
        }

        // 这里这种情况我忘记了....不能回退，巧妙的回溯可以解决
        char tmp = board[i][j];
        board[i][j] = 0;
        boolean p1 = process(i + 1, j, m, n, board, words, index + 1);
        boolean p2 = process(i - 1, j, m, n, board, words, index + 1);
        boolean p3 = process(i, j + 1, m, n, board, words, index + 1);
        boolean p4 = process(i, j - 1, m, n, board, words, index + 1);
        board[i][j] = tmp;
        return p1 || p2 || p3 || p4;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E' },
                {'S', 'F', 'C', 'S' },
                {'A', 'D', 'E', 'E' }
        };

        String word = "ABCB";
        System.out.println(exist(board, word));
    }

}
