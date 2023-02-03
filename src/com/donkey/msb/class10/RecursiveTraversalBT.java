package com.donkey.msb.class10;

/**
 * 递归的方式打印二叉树
 */
public class RecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 二路递归三次回到自己。脑海里要有画面，往左递归
     *        /
     *      /
     *   \/_
     */
    public static void f(Node head) {
        if (head == null) {
            return;
        }
        // 1
        f(head.left);
        // 2
        f(head.right);
        // 3
    }

    //先序遍历
    public static void pre(Node head) {
        if (head == null) {
            return;
        }

        System.out.println(head.value);

        pre(head.left);

        pre(head.right);
    }

    //中序遍历
    public static void in(Node head){
        if (head == null) {
            return;
        }

        in(head.left);

        System.out.println(head.value);

        in(head.right);
    }

    // 后序遍历
    public static void pos(Node node){
        if (node == null) {
            return;
        }

        pos(node.left);

        pos(node.right);

        System.out.println(node.value);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("=====");
        in(head);
        System.out.println("=====");
        pos(head);
    }

}
