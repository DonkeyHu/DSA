package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q234_IsPalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        List<Integer> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head.val);
            head = head.next;
        }

        int size = arr.size();
        for (int i = 0; i < size / 2; i++) {
            if (arr.get(i) != arr.get(size - 1 - i)) {
                return false;
            }
        }
        return true;
    }

}
