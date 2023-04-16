package com.donkey.msb.class16;

import java.util.ArrayList;

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
        return null;
    }

}
