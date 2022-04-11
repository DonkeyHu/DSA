package com.donkey.msb.class09;

import java.util.ArrayList;
import java.util.List;

/**
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * <p>
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * <p>
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * <p>
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 */
public class LinkedListMid {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node upMidOrUpPreMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node upMidOrDownPreMidNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node right1(Node head) {
        if (head == null) {
            return head;
        }
        List<Node> res = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            res.add(cur);
            cur = cur.next;
        }
        return res.get((res.size() - 1) / 2);
    }

    public static Node right2(Node head) {
        if (head == null) {
            return head;
        }
        List<Node> res = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            res.add(cur);
            cur = cur.next;
        }
        return res.get(res.size() / 2);
    }

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        List<Node> res = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            res.add(cur);
            cur = cur.next;
        }
        return res.get((res.size() - 3) / 2);
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        List<Node> res = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            res.add(cur);
            cur = cur.next;
        }
        return res.get((res.size() - 2) / 2);
    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);
        test.next.next.next.next.next.next.next.next.next = new Node(9);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);

        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);

        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = upMidOrUpPreMidNode(test);
        ans2 = right3(test);

        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = upMidOrDownPreMidNode(test);
        ans2 = right4(test);

        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

    }
}
