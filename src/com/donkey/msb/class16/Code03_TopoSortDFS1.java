package com.donkey.msb.class16;

import java.util.*;

public class Code03_TopoSortDFS1 {

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int label) {
            this.label = label;
            this.neighbors = new ArrayList<>();
        }
    }

    // 从节点的深度出发
    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static ArrayList<DirectedGraphNode> topoSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> cache = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            f(node, cache);
        }
        List<Record> list = new ArrayList<>();
        for (Record record : cache.values()) {
            list.add(record);
        }
        list.sort((a, b) -> b.deep - a.deep);
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : list) {
            ans.add(record.node);
        }
        return ans;
    }

    // 动态规划!!
    // Hash表理解为缓存
    public static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> cache) {
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        int follow = 0;
        // 查看所有子集的深度，然后取一个最大的
        for (DirectedGraphNode neighbor : node.neighbors) {
            follow = Math.max(follow, f(neighbor, cache).deep);
        }
        // node深度 = max(子集深度) + 1
        Record record = new Record(node, follow + 1);
        cache.put(node, record);
        return record;
    }
}
