package com.donkey.msb.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树的头节点head，
 * 返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class Code01_SubMaxBSTHead {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public int min;
        public int max;
        public int subMaxBSTSize;
        public Node subMaxBSTHead;

        public Info(int min, int max, int subMaxBSTSize, Node subMaxBSTHead) {
            this.min = min;
            this.max = max;
            this.subMaxBSTSize = subMaxBSTSize;
            this.subMaxBSTHead = subMaxBSTHead;
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
        int maxSize = 0;
        Node maxNode = null;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            maxSize = left.subMaxBSTSize;
            maxNode = left.subMaxBSTHead;
        }

        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            if (right.subMaxBSTSize > maxSize) {
                maxSize = right.subMaxBSTSize;
                maxNode = right.subMaxBSTHead;
            }
        }

        if (
                (left == null ? true : (left.subMaxBSTHead == node.left && left.max < node.value))
                        &&
                        (right == null ? true : (right.subMaxBSTHead == node.right && right.min > node.value))
        ) {
            maxNode = node;
            maxSize = (left != null ? left.subMaxBSTSize : 0) + (right != null ? right.subMaxBSTSize : 0) + 1;
        }
        return new Info(min, max, maxSize, maxNode);
    }

    public static Node subMaxBSTHead(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).subMaxBSTHead;
    }

    /**
     * ======================================
     * 方法二：对数器
     */

    public static int getSize(Node node) {
        if (node == null) {
            return 0;
        }
        List<Node> ans = new ArrayList<>();
        in(node, ans);
        for (int i = 1; i < ans.size(); i++) {
            if (ans.get(i).value <= ans.get(i - 1).value) {
                return 0;
            }
        }
        return ans.size();
    }

    public static void in(Node node, List<Node> ans) {
        if (node == null) {
            return;
        }
        in(node.left, ans);
        ans.add(node);
        in(node.right, ans);
    }

    public static Node subMaxBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        if (getSize(head) != 0) {
            return head;
        }
        Node left = subMaxBSTHead2(head.left);
        Node right = subMaxBSTHead2(head.right);
        return getSize(left) >= getSize(right) ? left : right;
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }


    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (subMaxBSTHead(head) != subMaxBSTHead2(head)) {
                System.out.println(subMaxBSTHead(head).value);
                System.out.println(subMaxBSTHead2(head).value);
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("finish!");
    }

}
