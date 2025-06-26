package com.donkey.leetcode;

/**
 * 19. 删除链表的倒数第N个节点
 * <p>
 * 我开始的思路是翻转链表，原来大可不必.....
 */
public class Q019_RemoveNthFromEnd {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null && n == 1) {
            return null;
        }
        ListNode reversedNode = reverse(head);
        ListNode root = reversedNode;
        if (n == 1) {
            ListNode next = root.next;
            root.next = null;
            return reverse(next);
        }
        ListNode pre = reversedNode;
        ListNode cur = reversedNode.next;
        int index = 2;
        while (cur != null) {
            ListNode tmp = cur.next;
            if (index == n) {
                pre.next = tmp;
                break;
            }
            pre = cur;
            cur = tmp;
            index++;
        }
        return reverse(root);
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    // [1,2] 2
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
//        if (head.next == null && n == 1) {
//            return null;
//        }
//        ListNode dummy = new ListNode(0, head);
//        int len = getListLen(head);
//        ListNode cur = dummy.next;
//        int x = len - n;
//        for (int i = 1; i < x; i++) {
//            cur = cur.next;
//        }
//        if (x == 0) {
//            return cur.next;
//        }
//        cur.next = cur.next.next;
//        return dummy.next;

        ListNode dummy = new ListNode(0, head);
        int len = getListLen(head);
        ListNode cur = dummy;
        for (int i = 1; i < len - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    public static int getListLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode l = dummy;
        ListNode r = dummy;
        int index = 0;
        while (r != null) {
            if (index > n) {
                l = l.next;
            }
            r = r.next;
            index++;
        }
        l.next = l.next.next;
        return dummy.next;
    }


    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        d.next = e;
        c.next = d;
        b.next = c;
        a.next = b;
        print(removeNthFromEnd3(a, 2));
    }
}
