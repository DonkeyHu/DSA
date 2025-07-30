package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 自己写的时候总差点意思，主要没想到从头读，从尾读要交换着来
 */
public class Q103_ZLevelOrder {

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        if (root.left == null && root.right == null) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            res.add(list);
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 是否从头读
        boolean headFlag = true;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (headFlag) {
                    TreeNode n = queue.pollFirst();
                    list.add(n.val);
                    if (n.left != null) {
                        queue.addLast(n.left);
                    }
                    if (n.right != null) {
                        queue.addLast(n.right);
                    }
                }else {
                    TreeNode n = queue.pollLast();
                    list.add(n.val);
                    if (n.right != null) {
                        queue.addFirst(n.right);
                    }
                    if (n.left != null) {
                        queue.addFirst(n.left);
                    }
                }
            }
            headFlag = !headFlag;
            res.add(list);
        }
        return res;
    }

}
