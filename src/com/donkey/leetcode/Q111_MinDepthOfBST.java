package com.donkey.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度优先遍历这种方法没想到，当一个节点没有左节点和右节点的时候，此时是最小高度了
 */
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

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                if (n.left == null && n.right == null) {
                    return level;
                }
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
        }
        return level;
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
