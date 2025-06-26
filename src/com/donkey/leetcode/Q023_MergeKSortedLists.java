package com.donkey.leetcode;

import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 */
public class Q023_MergeKSortedLists {

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


    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }

        if (heap.isEmpty()) {
            return null;
        }

        ListNode node = heap.poll();
        if (node.next != null) {
            heap.add(node.next);
        }
        ListNode pre = node;
        while (!heap.isEmpty()) {
            ListNode n = heap.poll();
            pre.next = n;
            pre = n;
            if (n.next != null) {
                heap.add(n.next);
            }
        }
        return node;
    }
}
