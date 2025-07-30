package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int depth = 0;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                depth = Math.max(depth, stack.size());
                stack.add(cur);
                cur = cur.left;
            } else {
                TreeNode x = stack.pop();
                cur = x.right;
            }
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
