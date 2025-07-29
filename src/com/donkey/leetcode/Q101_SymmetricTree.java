package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 这题目我以为中序遍历能解决，可惜不行，这还不是完全对称的 [1,2,2,2,null,2]
 */
public class Q101_SymmetricTree {
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

    // [1,2,2,2,null,2]
    public static boolean isSymmetric(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.left;
            } else {
                TreeNode x = stack.pop();
                ans.add(x.val);
                cur = x.right;
            }
        }

        int len = ans.size();
        if (len % 2 == 0) {
            return false;
        }

        for (int i = 0; i <= len / 2; i++) {
            if (ans.get(i) != ans.get(len - i - 1)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isSymmetric2(TreeNode root) {
        return process(root, root);
    }


    public static boolean process(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 == null) {
            return true;
        }

        if (head1 != null && head2 != null) {
            boolean b1 = head1.val == head2.val;
            boolean b2 = process(head1.left, head2.right);
            boolean b3 = process(head1.right, head2.left);
            return b1 && b2 && b3;
        }

        return false;
    }

}
