package com.donkey.msb.class02;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除被给定的数
 */
public class DeleteGivenNum {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeValue(Node head, int value) {
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static List<Integer> compareList(Node node, int value) {
        List<Integer> res = new ArrayList<>();
        while (node != null) {
            if (node.value != value) {
                res.add(node.value);
            }
            node = node.next;
        }
        return res;
    }

    public static boolean checkValid(Node node, List<Integer> list) {
        for (Integer value : list) {
            if (value != node.value) {
                return false;
            }
            node = node.next;
        }
        return true;
    }

    public static Node generateRandomNode(int maxLen, int maxValue) {
        int size = (int) (Math.random() * maxLen) + 1;
        Node head = new Node((int) (Math.random() * maxValue));
        Node pre = head;
        for (int i = 1; i < size; i++) {
            Node cur = new Node((int) (Math.random() * maxValue));
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 5;
        int times = 100;
        for (int i = 0; i < times; i++) {
            Node node = generateRandomNode(maxLen, maxValue);
            int value = (int) (Math.random() * maxValue);
            List<Integer> compareList = compareList(node, value);
            Node res = removeValue(node, value);
            if (!checkValid(res, compareList)) {
                System.out.println("BUG");
            }
        }
        System.out.println("Only Success!");
    }

}
