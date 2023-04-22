package com.donkey.msb.class16;

import java.util.*;

// 拓扑排序
// OJ链接：https://www.lintcode.com/problem/topological-sorting
public class Code03_TopoSortBFS {

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int label) {
            this.label = label;
            this.neighbors = new ArrayList<>();
        }
    }

    public static ArrayList<DirectedGraphNode> topoSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> indegreeMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            indegreeMap.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegreeMap.put(neighbor, indegreeMap.get(neighbor) + 1);
            }
        }
        Queue<DirectedGraphNode> zero = new LinkedList<>();
        for (DirectedGraphNode node : indegreeMap.keySet()) {
            if (indegreeMap.get(node) == 0) {
                zero.add(node);
            }
        }
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!zero.isEmpty()) {
            DirectedGraphNode node = zero.poll();
            ans.add(node);
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1);
                if (indegreeMap.get(neighbor) == 0) {
                    zero.add(neighbor);
                }
            }
        }
        return ans;
    }

}
