package com.donkey.leetcode;

/**
 * 思路真没有一下想到：
 * （1）要先算出长链表比短链表多几个 （2）然后长链表再先多挪动几个位置 （3）最后再一起挪动看看是否相交
 */
public class Q160_IntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA;
        ListNode curB = headB;

        int n = 0;

        while(curA.next != null) {
            n++;
            curA = curA.next;
        }
        while(curB.next != null) {
            n--;
            curB = curB.next;
        }

        if(curA != curB) {
            return null;
        }

        ListNode L = n > 0 ? headA: headB;
        ListNode S = L==headA?headB:headA;

        n = Math.abs(n);

        while(n != 0){
            L = L.next;
            n--;
        }

        while(L != S) {
            L = L.next;
            S = S.next;
        }
        return L;
    }

}
