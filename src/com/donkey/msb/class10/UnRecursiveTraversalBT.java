package com.donkey.msb.class10;

import java.util.Stack;

/**
 * 非递归，迭代的方式遍历二叉树
 */
public class UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            this.value = v;
        }
    }

    /**
     * 先序遍历：
     * (1) 栈顶弹出来记为cur
     * （2）有右先压右，有左先压左
     */
    public static void pre(Node head) {
        System.out.println("pre order:");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.empty()) {
                head = stack.pop();  // 头 -> 左 -> 右
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 后序遍历
     */
    public static void post(Node head) {
        System.out.println("post order:");
        if (head != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop(); // 头 -> 右 -> 左
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            // 左 -> 右 -> 头
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    /**
     * 中序遍历
     */
    public static void in(Node cur) {
        System.out.println("in-order:");
        if (cur != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || cur != null){
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }else {
                    cur = stack.pop();
                    System.out.print(cur.value+" ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.left.right.left = new Node(8);
        head.left.right.right = new Node(9);
        head.left.right.right.right = new Node(10);
        head.left.right.right.right.right = new Node(11);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        post(head);
        System.out.println("========");
    }

}
