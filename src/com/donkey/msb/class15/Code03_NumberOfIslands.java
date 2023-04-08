package com.donkey.msb.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/number-of-islands/
public class Code03_NumberOfIslands {

    // *************Solution one ********************
    public static void infect(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            return;
        }
        board[i][j] = 0;
        infect(board, i - 1, j);
        infect(board, i + 1, j);
        infect(board, i, j - 1);
        infect(board, i, j + 1);
    }

    public static int numOfIslands3(char[][] grid) {
        int lands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    lands++;
                    infect(grid, i, j);
                }
            }
        }
        return lands;
    }

    // *************Solution two ********************
    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionFind1<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parent;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind1(List<V> values) {
            nodes = new HashMap<>();
            parent = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parent.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            while (cur != parent.get(cur)) {
                stack.push(cur);
                cur = parent.get(cur);
            }
            while (!stack.isEmpty()) {
                parent.put(stack.pop(), cur);
            }
            return cur;
        }

        public void union(V a, V b) {
            Node<V> aFather = findFather(nodes.get(a));
            Node<V> bFather = findFather(nodes.get(b));
            if (aFather != bFather) {
                Integer aSize = sizeMap.get(aFather);
                Integer bSize = sizeMap.get(bFather);
                Node<V> big = aSize >= bSize ? aFather : bFather;
                Node<V> small = big == aFather ? bFather : aFather;
                parent.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }

    public static class Dot {

    }

    public static int numOfIslands2(char[][] board) {
        int row = board.length;
        int column = board[0].length;
        Dot[][] dots = new Dot[row][column];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        UnionFind1<Dot> uf = new UnionFind1<>(dotList);
        for (int i = 1; i < column; i++) {
            if (board[0][i - 1] == '1' && board[0][i] == '1') {
                uf.union(dots[0][i - 1], dots[0][i]);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(dots[i - 1][0], dots[i][0]);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (board[i][j] == '1') {
                    if (board[i][j - 1] == '1') {
                        uf.union(dots[i][j], dots[i][j - 1]);
                    }
                    if (board[i - 1][j] == '1') {
                        uf.union(dots[i][j], dots[i - 1][j]);
                    }
                }
            }
        }
        return uf.sets();
    }

    // *************Solution three ********************
    public static class UnionFind2 {
        public int[] parent;
        public int[] size;
        public int[] help;
        public int sets;
        public int col;

        public UnionFind2(char[][] board) {
            int row = board.length;
            col = board[0].length;
            parent = new int[row * col];
            size = new int[row * col];
            help = new int[row * col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == '1') {
                        int index = index(i, j);
                        size[index] = 1;
                        parent[index] = index;
                        sets++;
                    }
                }
            }
        }

        public int index(int i, int j) {
            return i * col + j;
        }

        public int find(int i) {
            int x = 0;
            while (i != parent[i]) {
                help[x++] = i;
                i = parent[i];
            }
            for (x--; x >= 0; x--) {
                parent[help[x]] = i;
            }
            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            int index1 = index(r1, c1);
            int index2 = index(r2, c2);
            int f1 = find(index1);
            int f2 = find(index2);
            if (f1 != f2) {
                int s1 = size[f1];
                int s2 = size[f2];
                if (s1 >= s2) {
                    parent[f2] = f1;
                    size[f1] = s1 + s2;
                } else {
                    parent[f1] = f2;
                    size[f2] = s1 + s2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }

    public static int numOfIslands1(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionFind2 uf = new UnionFind2(board);
        for (int i = 1; i < col; i++) {
            if (board[0][i - 1] == '1' && board[0][i] == '1') {
                uf.union(0, i - 1, 0, i);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(i - 1, 0, i, 0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i][j - 1] == '1') {
                        uf.union(i, j, i, j - 1);
                    }
                    if (board[i - 1][j] == '1') {
                        uf.union(i, j, i - 1, j);
                    }
                }
            }
        }
        return uf.sets;
    }

    public static char[][] generateRandomMatrix(int row, int col) {
        char[][] res = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[i][j] = Math.random() > 0.5 ? '1' : '0';
            }
        }
        return res;
    }

    public static char[][] copy(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        char[][] res = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[i][j] = board[i][j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int row = 10000;
        int col = 10000;

        char[][] b1 = generateRandomMatrix(row, col);
        char[][] b2 = copy(b1);
        char[][] b3 = copy(b1);

        System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numOfIslands3(b1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行结果: " + numOfIslands2(b2));
        end = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + numOfIslands1(b3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");
    }

}
