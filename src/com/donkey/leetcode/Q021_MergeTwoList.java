package com.donkey.leetcode;


public class Q021_MergeTwoList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null) {
            return null;
        }
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }

        ListNode ans = new ListNode(0);
        ListNode cur = ans;
        while(list1 != null && list2 != null) {
            if(list1.val > list2.val) {
                cur.next = list2;
                list2 = list2.next;
            }else{
                cur.next = list1;
                list1 = list1.next;
            }
            cur = cur.next;
        }

        while(list1 != null) {
            cur.next = list1;
            list1 = list1.next;
            cur = cur.next;
        }

        while(list2 != null) {
            cur.next = list2;
            list2 = list2.next;
            cur = cur.next;
        }

        return ans.next;
    }
}
