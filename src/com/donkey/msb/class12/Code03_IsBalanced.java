package com.donkey.msb.class12;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是平衡二叉树
 */
public class Code03_IsBalanced {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public int height;
        public boolean isBalanced;

        public Info(int h, boolean b) {
            this.height = h;
            this.isBalanced = b;
        }
    }

    public static boolean isBalance1(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBalanced;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, true);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isBalance = true;
        if (!left.isBalanced) {
            isBalance = false;
        }
        if (!right.isBalanced) {
            isBalance = false;
        }
        if (Math.abs(left.height - right.height) > 1) {
            isBalance = false;
        }
        return new Info(height, isBalance);
    }

    public static boolean isBalance2(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process(head, ans);
        return ans[0];
    }

    public static int process(Node head, boolean[] ans) {
        if (head == null || !ans[0]) {
            return 0;
        }

        int leftHeight = process(head.left, ans);
        int rightHeight = process(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalance1(head) != isBalance2(head)) {
                System.out.println(isBalance1(head));
                System.out.println(isBalance2(head));
                System.out.println("BUG!");
                return;
            }
        }
        System.out.println("finish!");
    }

}
