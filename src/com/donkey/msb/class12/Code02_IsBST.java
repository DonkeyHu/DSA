package com.donkey.msb.class12;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断二叉树是否是搜索二叉树
 */
public class Code02_IsBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }

        List<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).value >= arr.get(i + 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node node, List<Node> list) {
        if (node == null) {
            return;
        }
        in(node.left, list);
        list.add(node);
        in(node.right, list);
    }

    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }


    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info left = process(node.left);
        Info right = process(node.right);

        int max = node.value;
        int min = node.value;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }
        boolean isBST = true;
        if (left != null && (!left.isBST || left.max >= node.value)) {
            isBST = false;
        }
        if (right != null && (!right.isBST || node.value >= right.min)) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public static Node generateRandomBT(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
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

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            Node head = generateRandomBT(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("1:" + isBST1(head));
                System.out.println("2:" + isBST2(head));
                System.out.println("BUG");
                return;
            }
        }
        System.out.println("Finish");
    }
}
