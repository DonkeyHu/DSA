package com.donkey.msb.class15;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/friend-circles/
// 可以直接通过
public class Code02_FriendCircle {

    public static int findCircleNum(int[][] M) {
        int N = M.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }


    public static class UnionFind {
        // parent[i] = k ： i的父亲是k
        public int[] parents;
        // size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
        // i所在的集合大小是多少
        public int[] size;
        // 辅助结构
        public int[] help;
        // 一共有多少个集合
        public int sets;

        public UnionFind(int N) {
            parents = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int v) {
            int hi = 0;
            while (v != parents[v]) {
                help[hi++] = v;
                v = parents[v];
            }
            while (hi != 0) {
                parents[help[--hi]] = v;
            }
            return v;
        }

        public void union(int a, int b) {
            int headA = find(a);
            int headB = find(b);
            if (headA != headB) {
                int sizeA = size[headA];
                int sizeB = size[headB];
                if (sizeA >= sizeB) {
                    parents[headB] = headA;
                    size[headA] = sizeA + sizeB;
                } else {
                    parents[headA] = headB;
                    size[headB] = sizeA + sizeB;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }

    }

}
