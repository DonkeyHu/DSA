package com.donkey.msb.class02;

import java.util.ArrayList;
import java.util.List;

/**
 * 单向链表反转
 * 双向链表反转
 *
 * 看似简单的题目，也是有点技巧的，要定义pre指针
 */
public class ReverseList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    /**
     * 单链表翻转
     * @param head
     * @return
     */
    public static Node reverseLinkList(Node head) {
        Node pre = null;
        while (head != null) {
            Node tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    /**
     * 双链表反转
     * @param head
     * @return
     */
    public static DoubleNode reverseDoubleLinkList(DoubleNode head) {
        DoubleNode pre = null;
        while (head != null) {
            DoubleNode tmp = head.next;
            head.next = pre;
            head.last = tmp;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    public static Node generateRandomLinkList(int maxLen, int maxValue) {
        int size = (int) (Math.random() * maxLen) + 1;
        Node head = new Node((int) (Math.random() * maxValue));
        Node pre = head;
        for (int i = size; i > 1; i--) {
            Node cur = new Node((int) (Math.random() * maxValue));
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    public static DoubleNode generateDoubleLinkList(int maxLen, int maxValue) {
        int size = (int) (Math.random() * maxLen) + 1;
        DoubleNode head = new DoubleNode((int) (Math.random() * maxValue));
        DoubleNode pre = head;
        for (int i = size; i > 1; i--) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * maxValue));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
        }
        return head;
    }

    public static List<Integer> getLinkListOriginSort(Node node) {
        List<Integer> res = new ArrayList<>();
        while (node != null) {
            res.add(node.value);
            node = node.next;
        }
        return res;
    }

    public static boolean checkLinkListReverse(List<Integer> list, Node node) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != node.value) {
                return false;
            }
            node = node.next;
        }
        return true;
    }

    public static List<Integer> getDoubleLinkListOrginSort(DoubleNode node) {
        List<Integer> res = new ArrayList<>();
        while (node != null) {
            res.add(node.value);
            node = node.next;
        }
        return res;
    }

    public static boolean checkDoubleLinkListReverse(List<Integer> list, DoubleNode node) {
        DoubleNode end = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != node.value) {
                return false;
            }
            end = node;
            node = node.next;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != end.value) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int times = 100;
        System.out.println("start");
        for (int i = 0; i < times; i++) {
            Node linkListNode = generateRandomLinkList(maxLen, maxValue);
            List<Integer> listSort = getLinkListOriginSort(linkListNode);
            Node reverseLinkList = reverseLinkList(linkListNode);
            if (!checkLinkListReverse(listSort,reverseLinkList)){
                System.out.println("check link list reverse error!");
            }

            DoubleNode doubleNode = generateDoubleLinkList(maxLen, maxValue);
            List<Integer> doubleLinkSort = getDoubleLinkListOrginSort(doubleNode);
            DoubleNode reverseDoubleLinkList = reverseDoubleLinkList(doubleNode);
            if (!checkDoubleLinkListReverse(doubleLinkSort,reverseDoubleLinkList)){
                System.out.println("check double link list reverse error!");
            }
        }
        System.out.println("End success");
    }
}
