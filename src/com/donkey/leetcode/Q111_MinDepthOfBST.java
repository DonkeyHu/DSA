package com.donkey.leetcode;

public class Q111_MinDepthOfBST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public int minDepth(TreeNode root) {
        return process(root);
    }


    public static int process(TreeNode n) {
        if (n == null) {
            return 0;
        }

        if (n.left == null) {
            return process(n.right) + 1;
        }

        if (n.right == null) {
            return process(n.left) + 1;
        }

        return Math.min(process(n.right), process(n.left)) + 1;
    }
}
