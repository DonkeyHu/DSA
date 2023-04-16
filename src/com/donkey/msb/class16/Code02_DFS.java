package com.donkey.msb.class16;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Code02_DFS {

    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            for (Node next : node.nexts) {
                if (!set.contains(next)) {
                    stack.push(node);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }


}
