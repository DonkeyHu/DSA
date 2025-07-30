package com.donkey.leetcode;

/**
 * 环形链表，我的脑海里似乎浮现过这道题，不幸运的是我不知道怎么解，可能也忘了，导致携程出这道题我挂了
 * <p>
 * 快慢指针
 */
public class Q141_LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }

        ListNode s = head.next;
        ListNode f = head.next.next;

        while (f != s) {
            if (f.next == null || f.next.next == null) {
                return false;
            }
            s = s.next;
            f = f.next.next;
        }
        return true;
    }

}
