package com.donkey.msb.class30;

/**
 *  Morris遍历: 一种遍历二叉树的方式，并且时间复杂度O(N)，额外空间复杂度O(1)。 通过利用原树中大量空闲指针的方式，达到节省空间的目的
 *
 * 假设来到当前节点cur，开始时cur来到头节点位置
 * 1）如果cur没有左孩子，cur向右移动(cur = cur.right)
 * 2）如果cur有左孩子，找到左子树上最右的节点mostRight：
 * 	a.如果mostRight的右指针指向空，让其指向cur，
 * 	然后cur向左移动(cur = cur.left)
 * 	b.如果mostRight的右指针指向cur，让其指向null，
 * 	然后cur向右移动(cur = cur.right)
 * 3）cur为空时遍历停止
 */
public class Code01_MorrisTraversal {

    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public void process(Node root) {
        if (root == null) {
            return ;
        }

        process(root.left);

        process(root.right);
    }


    public static void morris(Node root) {
        Node cur = root;
        while (cur != null) {
            Node mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);

        morris(head);
    }

}
