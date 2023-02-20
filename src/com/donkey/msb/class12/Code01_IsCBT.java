package com.donkey.msb.class12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否是完全二叉树
 * <p>
 * 会在后面的题目中用二叉树的递归套路来解这个题
 */
public class Code01_IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        // 是否存在左右子树不同时为null的情况
        boolean leaf = false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            Node l = cur.left;
            Node r = cur.right;
            if (
                    (l == null && r != null) || (leaf && (l != null || r != null))
            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (r == null || l == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static class Info {
        public boolean isCBT;
        public boolean isFull;
        public int height;

        public Info(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(true, true, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        boolean isCBT = false;
        if (left.isCBT && right.isFull && left.height == right.height + 1) {
            isCBT = true;
        } else if (left.isFull && right.isCBT && left.height == right.height) {
            isCBT = true;
        } else if (left.isFull && right.isFull && left.height == right.height) {
            isCBT = true;
        } else if (left.isFull && right.isFull && left.height == right.height + 1) {
            isCBT = true;
        }
        return new Info(isCBT, isFull, height);
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    public static Node generateRandomBT(int maxLevel, int maxValue) {
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
        int maxLevel = 5;
        int maxValue = 100;
        int times = 10000000;
        for (int i = 0; i < times; i++) {
            Node node = generateRandomBT(maxLevel, maxValue);
            if (isCBT(node) != isCBT1(node)) {
                System.out.println("BUG");
                return;
            }
        }
        System.out.println("Finish");
    }

}
