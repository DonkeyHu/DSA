package com.donkey.msb.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Code01_BFS {

    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.value);
            for (Node n : node.nexts) {
                if (!set.contains(n)) {
                    queue.add(n);
                    set.add(n);
                }
            }
        }
    }

}
