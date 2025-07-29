package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q098_IsValidBST {
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

    public boolean isValidBST(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }

        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.left;
            } else {
                TreeNode n = stack.pop();
                ans.add(n.val);
                cur = n.right;
            }
        }

        for (int i = 1; i < ans.size(); i++) {
            if (ans.get(i - 1) >= ans.get(i)) {
                return false;
            }
        }
        return true;
    }
}
