package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q094_BinaryTreeInorderTraversal {

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

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    public static void process(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }

        process(root.left, ans);
        ans.add(root.val);
        process(root.right, ans);
    }

    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 左 -> 头 -> 右
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur == null) {
                TreeNode t = stack.pop();
                ans.add(t.val);
                cur = t.right;
            } else {
                stack.add(cur);
                cur = cur.left;
            }
        }
        return ans;
    }

    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 头 -> 左 -> 右
        TreeNode cur = root;
        stack.add(cur);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return ans;
    }


    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 左 -> 右 -> 头
        Stack<TreeNode> out = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode x = stack.pop();
            out.add(x);
            // 头 -> 右 -> 左
            if (x.left != null) {
                stack.add(x.left);
            }
            if (x.right != null) {
                stack.add(x.right);
            }
        }

        while (!out.isEmpty()) {
            TreeNode node = out.pop();
            ans.add(node.val);
        }
        return ans;
    }

}
