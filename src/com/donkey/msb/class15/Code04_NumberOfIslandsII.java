package com.donkey.msb.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/number-of-islands-ii/
public class Code04_NumberOfIslandsII {

    public static class UnionFind1 {
        public int[] parent;
        public int[] size;
        public int[] help;
        public int sets;
        public int row;
        public int col;

        public UnionFind1(int r, int c) {
            row = r;
            col = c;
            int n = r * c;
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = 0;
        }

        public int find(int x) {
            int hi = 0;
            while (x != parent[x]) {
                help[hi++] = x;
                x = parent[x];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = x;
            }
            return x;
        }

        public int index(int r, int c) {
            return r * col + c;
        }

        public void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int x1 = index(r1, c1);
            int x2 = index(r2, c2);
            if (size[x1] == 0 || size[x2] == 0) {
                return;
            }
            int f1 = find(x1);
            int f2 = find(x2);
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

        public int connect(int r, int c) {
            int x = index(r, c);
            if (size[x] == 0) {
                size[x] = 1;
                parent[x] = x;
                sets++;
                union(r, c, r, c - 1);
                union(r, c, r - 1, c);
                union(r, c, r, c + 1);
                union(r, c, r + 1, c);
            }
            return sets;
        }
    }

    public static List<Integer> numOfIslandsII1(int m, int n, int[][] pos) {
        List<Integer> ans = new ArrayList<>();
        UnionFind1 uf = new UnionFind1(m, n);
        for (int[] v : pos) {
            ans.add(uf.connect(v[0], v[1]));
        }
        return ans;
    }


    public static class UnionFind2 {
        public Map<String, String> parent;
        public Map<String, Integer> size;
        public List<String> help;
        public int sets;

        public UnionFind2() {
            parent = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;
        }

        public String find(String s) {
            while (!s.equals(parent.get(s))) {
                help.add(s);
                s = parent.get(s);
            }
            for (String v : help) {
                parent.put(v, s);
            }
            help.clear();
            return s;
        }

        public void union(String s1, String s2) {
            if (parent.containsKey(s1) && parent.containsKey(s2)) {
                String f1 = find(s1);
                String f2 = find(s2);
                if (!f1.equals(f2)) {
                    Integer size1 = size.get(f1);
                    Integer size2 = size.get(f2);
                    if (size1 >= size2) {
                        parent.put(f2, f1);
                        size.put(f1, size1 + size2);
                    } else {
                        parent.put(f1, f2);
                        size.put(f2, size1 + size2);
                    }
                    sets--;
                }
            }
        }

        public int connect(int r, int c) {
            String val = String.valueOf(r) + "_" + String.valueOf(c);
            if (!parent.containsKey(val)) {
                parent.put(val, val);
                size.put(val, 1);
                sets++;
                String left = String.valueOf(r) + "_" + String.valueOf(c - 1);
                String up = String.valueOf(r - 1) + "_" + String.valueOf(c);
                String right = String.valueOf(r) + "_" + String.valueOf(c + 1);
                String down = String.valueOf(r + 1) + "_" + String.valueOf(c);

                union(val, left);
                union(val, up);
                union(val, right);
                union(val, down);
            }
            return sets;
        }

    }

    public static List<Integer> numOfIslandII2(int m, int n, int[][] pos) {
        List<Integer> ans = new ArrayList<>();
        UnionFind2 uf = new UnionFind2();
        for (int[] v : pos) {
            ans.add(uf.connect(v[0], v[1]));
        }
        return ans;
    }

}
