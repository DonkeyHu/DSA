package com.donkey.msb.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode:
 * 431.Encode N-ary Tree to Binary Tree
 */
public class EncodeNaryTreeToBinaryTree {

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {

        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.children = children;
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    class Code {

        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode tNode = new TreeNode(root.val);
            tNode.left = en(root.children);
            return tNode;
        }

        /**
         * 每层都放在左树右边界上
         *     /
         *     \
         *      \
         *       \
         *        \
         */
        public TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;

            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null) {
                    head = tNode;
                } else {
                    cur.right = tNode;
                }
                cur = tNode;
                cur.left = en(child.children);
            }
            return head;
        }

        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        public List<Node> de(TreeNode root) {
            List<Node> children = new ArrayList<>();
            while (root != null) {
                Node node = new Node(root.val, de(root.left));
                children.add(node);
                root = root.right;
            }
            return children;
        }

    }
}
