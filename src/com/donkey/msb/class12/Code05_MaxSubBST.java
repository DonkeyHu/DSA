package com.donkey.msb.class12;

/**
 * 给定一棵二叉树的头节点head，
 * 返回这颗二叉树中最大的二叉搜索子树的大小
 *
 * 在线测试链接 : https://leetcode.com/problems/largest-bst-subtree (待测试)
 */
public class Code05_MaxSubBST {

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
        public int maxSubBSTSize;
        public int allSize;

        public Info(int min, int max, int maxSubBSTSize, int allSize) {
            this.min = min;
            this.max = max;
            this.maxSubBSTSize = maxSubBSTSize;
            this.allSize = allSize;
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
        int allSize = 1;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            allSize += left.allSize;
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            allSize += right.allSize;
        }
        int p1 = -1;
        if (left != null) {
            p1 = left.maxSubBSTSize;
        }
        int p2 = -1;
        if (right != null) {
            p2 = right.maxSubBSTSize;
        }
        int p3 = -1;
        boolean leftBST = left == null ? true : left.maxSubBSTSize == left.allSize;
        boolean rightBST = right == null ? true : right.maxSubBSTSize == right.allSize;
        if (leftBST && rightBST) {
            boolean leftMaxLessX = left == null ? true : left.max < node.value;
            boolean rightMinMoreX = right == null ? true : right.min > node.value;
            if (leftMaxLessX && rightMinMoreX) {
                int l = left == null ? 0 : left.maxSubBSTSize;
                int r = right == null ? 0 : right.maxSubBSTSize;
                p3 = l + r + 1;
            }
        }
        return new Info(min, max, Math.max(Math.max(p1, p2), p3), allSize);
    }

}
