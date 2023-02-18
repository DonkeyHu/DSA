package com.donkey.msb.class12;

import java.util.*;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，
 * 返回整棵二叉树的最大距离
 */
public class Code06_MaxDistance {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int m, int h) {
            this.maxDistance = m;
            this.height = h;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }

        Info left = process(head.left);
        Info right = process(head.right);

        int height = Math.max(left.height, right.height) + 1;
        int p1 = left.maxDistance;
        int p2 = right.maxDistance;
        int p3 = left.height + right.height + 1;

        int maxDistance = Math.max(Math.max(p1, p2), p3);
        return new Info(maxDistance, height);
    }

    public static int maxDistance1(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxDistance;
    }


    public static int distance(Map<Node, Node> map, Node n1, Node n2) {
        HashSet<Node> set = new HashSet<>();
        Node cur = n1;
        set.add(cur);
        while (map.get(cur) != null) {
            cur = map.get(cur);
            set.add(cur);
        }

        cur = n2;
        while (!set.contains(cur)) {
            cur = map.get(cur);
        }

        Node lowestAncestor = cur;
        cur = n1;
        int h1 = 1;
        while (cur != lowestAncestor) {
            cur = map.get(cur);
            h1++;
        }

        cur = n2;
        int h2 = 1;
        while (cur != lowestAncestor) {
            cur = map.get(cur);
            h2++;
        }

        return h1 + h2 - 1;
    }

    public static int maxDistance2(Node head) {
        if (head == null) {
            return 0;
        }
        List<Node> arr = new ArrayList<>();
        pre(head, arr);
        Map<Node, Node> map = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(map, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    public static void pre(Node node, List<Node> arr) {
        if (node == null) {
            return;
        }
        arr.add(node);
        pre(node.left, arr);
        pre(node.right, arr);
    }

    public static Map<Node, Node> getParentMap(Node head) {
        Map<Node, Node> map = new HashMap<>();
        map.put(head, null);
        in(map, head);
        return map;
    }

    public static void in(Map<Node, Node> map, Node head) {
        if (head.left != null) {
            map.put(head.left, head);
            in(map, head.left);
        }
        if (head.right != null) {
            map.put(head.right, head);
            in(map, head.right);
        }
    }

    public static Node generateRandomBT(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.35) {
            return null;
        }
        Node node = new Node((int) (Math.random() * maxValue));
        node.left = generate(level + 1, maxLevel, maxValue);
        node.right = generate(level + 1, maxLevel, maxValue);
        return node;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            Node head = generateRandomBT(maxLevel, maxValue);
            if (maxDistance1(head) != maxDistance2(head)){
                System.out.println(maxDistance1(head));
                System.out.println(maxDistance2(head));
                System.out.println("BUG");
                return;
            }
        }
        System.out.println("FINISH");
    }

}
