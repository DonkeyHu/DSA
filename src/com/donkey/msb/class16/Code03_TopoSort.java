package com.donkey.msb.class16;

import java.util.*;

public class Code03_TopoSort {

    public static List<Node> topoSort(Graph graph) {
        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroNode = new LinkedList<>();

        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroNode.add(node);
            }
        }

        List<Node> ans = new ArrayList<>();
        while (!zeroNode.isEmpty()) {
            Node node = zeroNode.poll();
            ans.add(node);
            for (Node next : node.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroNode.add(next);
                }
            }
        }
        return ans;
    }

}
