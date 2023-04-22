package com.donkey.msb.class16;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// 最小生成树P算法
public class Code05_Prim {


    public Set<Edge> primMST(Graph graph) {

        PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        HashSet<Node> union = new HashSet<>();

        Set<Edge> res = new HashSet<>();

        for (Node node : graph.nodes.values()) {
            if (!union.contains(node)) {
                union.add(node);
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()) {
                    Edge edge = queue.poll();
                    Node to = edge.to;
                    if (!union.contains(to)) {
                        res.add(edge);
                        union.add(to);
                        for (Edge e : to.edges) {
                            queue.add(e);
                        }
                    }
                }
            }
        }
        return res;
    }

}
