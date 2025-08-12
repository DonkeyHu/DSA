package com.donkey.leetcode;

public class Q024_SwapPairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode t1 = cur.next;
            ListNode t2 = cur.next.next;
            t1.next = t2.next;
            cur.next = t2;
            t2.next = t1;
            cur = t1;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode target1 = new ListNode(1);
        ListNode target2 = new ListNode(2);
        ListNode target3 = new ListNode(3);
        ListNode target4 = new ListNode(4);
        target3.next = target4;
        target2.next = target3;
        target1.next = target2;
        swapPairs(target1);
    }

}
