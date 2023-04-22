package com.donkey.msb.class16;

import java.util.*;

//  undirected graph only
// 最小生成树，K算法
public class Code04_Kruskal {

    public static class UnionFind {
        public Map<Node, Node> parentsMap;
        public Map<Node, Integer> setsMap;

        public UnionFind() {
            parentsMap = new HashMap<>();
            setsMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            parentsMap.clear();
            setsMap.clear();
            for (Node node : nodes) {
                parentsMap.put(node, node);
                setsMap.put(node, 1);
            }
        }

        public Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != parentsMap.get(node)) {
                stack.push(node);
                node = parentsMap.get(node);
            }
            while (!stack.isEmpty()) {
                Node item = stack.pop();
                parentsMap.put(item, node);
            }
            return node;
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aF = findFather(a);
            Node bF = findFather(b);
            if (aF != bF) {
                Integer aSize = setsMap.get(aF);
                Integer bSize = setsMap.get(bF);
                if (aSize >= bSize) {
                    parentsMap.put(bF, aF);
                    setsMap.put(aF, aSize + bSize);
                    setsMap.remove(bF);
                } else {
                    parentsMap.put(aF, bF);
                    setsMap.put(bF, aSize + bSize);
                    setsMap.remove(aF);
                }
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }
    }

    /**
     * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
     * 2）当前的边要么进入最小生成树的集合，要么丢弃
     * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
     * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
     * 5）考察完所有边之后，最小生成树的集合也得到了
     */
    public Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        Set<Edge> res = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                res.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return res;
    }

}
