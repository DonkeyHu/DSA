package com.donkey.msb.class13;

import java.util.*;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。
 * 返回a和b的最低公共祖先
 */
public class Code02_LowestAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public boolean findA;
        public boolean findB;
        public Node ans;

        public Info(boolean findA, boolean findB, Node ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    public static Info process(Node x, Node a, Node b) {
        if (x == null) {
            return new Info(false, false, null);
        }

        Info left = process(x.left, a, b);
        Info right = process(x.right, a, b);

        boolean findA = (x == a) || left.findA || right.findA;
        boolean findB = (x == b) || left.findB || right.findB;
        Node ans = null;
        if (left.ans != null) {
            ans = left.ans;
        } else if (right.ans != null) {
            ans = right.ans;
        } else {
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }

    public static Node lowestAnc1(Node head, Node a, Node b) {
        if (head == null) {
            return null;
        }
        return process(head, a, b).ans;
    }


    public static void fillParentMap(Node node, Map<Node, Node> map) {
        if (node.left != null) {
            map.put(node.left, node);
            fillParentMap(node.left, map);
        }
        if (node.right != null) {
            map.put(node.right, node);
            fillParentMap(node.right, map);
        }
    }

    public static Node lowestAnc2(Node head, Node a, Node b) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        HashSet<Node> set = new HashSet<>();
        Node cur = a;
        set.add(cur);
        while (map.get(cur) != null) {
            cur = map.get(cur);
            set.add(cur);
        }
        cur = b;
        while (!set.contains(cur)) {
            cur = map.get(cur);
        }
        return cur;
    }


    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.35) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static Node generateRandomBT(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static void fillPreList(Node node, List<Node> list) {
        if (node == null) {
            return;
        }
        list.add(node);
        fillPreList(node.left, list);
        fillPreList(node.right, list);
    }

    public static Node findRandomOne(Node node) {
        if (node == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        fillPreList(node, list);
        return list.get((int) (Math.random() * list.size()));
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            Node node = generateRandomBT(maxLevel, maxValue);
            Node a = findRandomOne(node);
            Node b = findRandomOne(node);
            if (lowestAnc1(node, a, b) != lowestAnc2(node, a, b)) {
                System.out.println("Bug!");
                return;
            }
        }
        System.out.println("finish!");
    }
}
