package com.donkey.msb.class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点:
 * <p>
 * 思路：可以通过设置flag变量的方式，来发现某一层的结束
 */
public class TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node, Integer> map = new HashMap<>();
        map.put(head, 1);
        int max = 0;
        int curLevelNode = 0;
        int curLevel = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int level = map.get(cur);

            if (cur.left != null) {
                queue.add(cur.left);
                map.put(cur.left, level + 1);
            }

            if (cur.right != null) {
                queue.add(cur.right);
                map.put(cur.right, level + 1);
            }

            if (level == curLevel) {
                curLevelNode++;
            } else {
                max = Math.max(max, curLevelNode);
                curLevel = level;
                curLevelNode = 1;
            }
        }
        max = Math.max(max, curLevelNode);
        return max;
    }


    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }

        int max = 0;
        // 当前层节点数
        int curLevelNode = 0;
        // 当前层的末尾节点
        Node curEnd = head;
        // 下一层的末尾节点
        Node nextEnd = null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNode++;
            // 这一段判等条件没想到
            if (cur == curEnd) {
                max = Math.max(max, curLevelNode);
                curLevelNode = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.4) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthNoMap(head) != maxWidthUseMap(head)){
                System.out.println("Bug");
                return;
            }
        }
        System.out.println("Finish!");
    }

}
