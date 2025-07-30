package com.donkey.leetcode;

/**
 * 环形链表，我的脑海里似乎浮现过这道题，不幸运的是我不知道怎么解，可能也忘了，导致携程出这道题我挂了
 * <p>
 * 快慢指针:
 * (1) 第一次相遇，肯定有环，重置f快指针到开头位置，快指针也等于一步
 * （2）第二次相遇的结点就是环的位置
 */
public class Q142_LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode s = head.next;
        ListNode f = head.next.next;

        while (f != s) {
            if (f.next == null || f.next.next == null) {
                return null;
            }
            s = s.next;
            f = f.next.next;
        }
        f = head;
        while (f != s) {
            f = f.next;
            s = s.next;
        }
        return f;
    }
}
