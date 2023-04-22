package com.donkey.msb.class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Code03_TopoSortDFS2 {

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int label) {
            this.label = label;
            this.neighbors = new ArrayList<>();
        }
    }

    // 从节点的个数出发
    public static class Record {
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
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
        list.sort((a, b) -> a.nodes == b.nodes ? 0 : (a.nodes > b.nodes ? -1 : 1));
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

        long nodes = 0;
        // 查看所有子集的节点个数，然后累加
        for (DirectedGraphNode neighbor : node.neighbors) {
            nodes += f(neighbor, cache).nodes;
        }
        // node深度 = 所有子集的节点个数 + 1
        Record record = new Record(node, nodes + 1);
        cache.put(node, record);
        return record;
    }
}
