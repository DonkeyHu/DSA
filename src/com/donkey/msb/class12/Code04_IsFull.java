package com.donkey.msb.class12;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是满二叉树
 */
public class Code04_IsFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 收集子树是否是满二叉树
    // 收集子树的高度
    // 左树满 && 右树满 && 左右树高度一样 -> 整棵树是满的
    public static class Info {
        public boolean isFull;
        public int height;

        public Info(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(true, 0);
        }

        Info left = process(node.left);
        Info right = process(node.right);

        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        int height = Math.max(left.height, right.height) + 1;
        return new Info(isFull, height);
    }

    public static boolean isFull(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isFull;
    }

    // 收集整棵树的高度h，和节点数n
    // 只有满二叉树满足 : 2 ^ h - 1 == n
    public static class Info2 {
        public int nodes;
        public int height;

        public Info2(int nodes, int height) {
            this.nodes = nodes;
            this.height = height;
        }
    }

    public static Info2 process2(Node node) {
        if (node == null) {
            return new Info2(0, 0);
        }

        Info2 left = process2(node.left);
        Info2 right = process2(node.right);

        int n = left.nodes + right.nodes + 1;
        int height = Math.max(left.height, right.height) + 1;
        return new Info2(n, height);
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        Info2 info = process2(head);
        return (1 << info.height) - 1 == info.nodes;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull(head) != isFull2(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
