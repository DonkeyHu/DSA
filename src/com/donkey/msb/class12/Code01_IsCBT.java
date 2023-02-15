package com.donkey.msb.class12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否是完全二叉树
 * <p>
 * 会在后面的题目中用二叉树的递归套路来解这个题
 */
public class Code01_IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        // 是否存在左右子树不同时为null的情况
        boolean leaf = false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            Node l = cur.left;
            Node r = cur.right;
            if (
                    (l == null && r != null) || (leaf && (l != null || r != null))
            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (r == null || l == null) {
                leaf = true;
            }
        }
        return true;
    }
}
