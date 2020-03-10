package com.donkey.leetcode;

/**
 * 二叉树的直径
 */
public class DiameterOfBinTree {
    int ans = 0;
    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        DiameterOfBinTree instance = new DiameterOfBinTree();
        System.out.println(instance.diameterOfBinaryTree(a));
    }

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans-1;
    }

    public int depth(TreeNode node){
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans,L+R+1);
        return Math.max(L,R) + 1;
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        this.val = x;
    }
}