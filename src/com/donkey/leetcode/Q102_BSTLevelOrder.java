package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q102_BSTLevelOrder {

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

    public List<List<Integer>> levelOrder(TreeNode root) {
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

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                list.add(n.val);
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    // Z Level Order
    public static List<List<Integer>> levelOrder2(TreeNode root) {
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

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                list.add(n.val);
                if (flag) {
                    if (n.right != null) {
                        queue.add(n.right);
                    }
                    if (n.left != null) {
                        queue.add(n.left);
                    }
                } else {
                    if (n.left != null) {
                        queue.add(n.left);
                    }
                    if (n.right != null) {
                        queue.add(n.right);
                    }
                }

            }
            flag = !flag;
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(3);
        TreeNode ll = new TreeNode(4);
        TreeNode rr = new TreeNode(4);

        l.left = ll;
        r.right = rr;
        root.left = l;
        root.right = r;

        levelOrder2(root);

    }


}
