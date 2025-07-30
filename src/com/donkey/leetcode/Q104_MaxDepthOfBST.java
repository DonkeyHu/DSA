package com.donkey.leetcode;

import java.util.*;

public class Q104_MaxDepthOfBST {

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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
            depth++;
        }

        return depth;
    }

    public static int maxDepth2(TreeNode root) {
        return process(root);
    }

    public static int process(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int p1 = process(node.left) + 1;
        int p2 = process(node.right) + 1;
        return Math.max(p1, p2);
    }

}
