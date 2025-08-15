package com.donkey.leetcode;

import java.util.Arrays;

public class Q148_SortList {

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        int n = 0;
        while(cur != null) {
            n++;
            cur = cur.next;
        }

        ListNode[] arr = new ListNode[n];
        ListNode c = head;
        int index = 0;
        while(c != null) {
            ListNode tmp = c.next;
            c.next = null;
            arr[index++] = c;
            c = tmp;
        }

        Arrays.sort(arr, (a, b) -> a.val - b.val);
        for(int i = 1; i < n; i++) {
            arr[i-1].next = arr[i];
        }

        return arr[0];
    }

}
