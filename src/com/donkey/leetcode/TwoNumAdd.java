package com.donkey.leetcode;


import java.util.Stack;

/**
 * 2.两数相加
 */

class ListNode{
    int val;
    ListNode next;

    public ListNode() {
    }
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class TwoNumAdd {
    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode a,ListNode b){

        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();
        while (a != null){
            stackA.push(a.val);
            a = a.next;
        }
        while (b != null){
            stackB.push(b.val);
            b = b.next;
        }
        return null;
    }
}
