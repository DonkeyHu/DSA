package com.donkey.leetcode;

/**
 * 2. 两数相加
 *
 *  这题也是有技巧的，有一个pre指针的概念，不然边界条件特别难处理。提前定义pre指针，再把pre.next = new ListNode()，这就很完美
 */
public class Q002_TwoSumAdd {

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {

        }

        public ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode right(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode cur = ans;
        int k = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int sum = v1 + v2 + k;
            if (sum < 10) {
                k = 0;
                cur.next = new ListNode(sum);
            } else {
                int m = sum % 10;
                k = 1;
                cur.next = new ListNode(m);
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            cur = cur.next;
        }
        if (k > 0) {
            cur.next = new ListNode(k);
        }
        return ans.next;
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode();
        ListNode cur = ans;
        int k = 0;
        while (l1 != null && l2 != null) {
            int v1 = l1.val;
            int v2 = l2.val;
            if (v1 + v2 + k < 10) {
                cur.val = v1 + v2 + k;
                k = 0;
            } else {
                int m = (v1 + v2 + k) % 10;
                k = 1;
                cur.val = m;
            }
            l1 = l1.next;
            l2 = l2.next;
            if (l1 != null || l2 != null) {
                cur.next = new ListNode();
            }
            cur = cur.next;
        }

        while (l1 != null) {
            int v1 = l1.val;
            if (v1 + k < 10) {
                cur.val = v1 + k;
                k = 0;
            } else {
                int m = (v1 + k) % 10;
                k = 1;
                cur.val = m;
            }
            l1 = l1.next;
            if (l1 != null) {
                cur.next = new ListNode();
            }
            cur = cur.next;
        }

        while (l2 != null) {
            int v2 = l2.val;
            if (v2 + k < 10) {
                cur.val = v2 + k;
                k = 0;
            } else {
                int m = (v2 + k) % 10;
                k = 1;
                cur.val = m;
            }
            l2 = l2.next;
            if (l2 != null) {
                cur.next = new ListNode();
            }
            cur = cur.next;
        }

        return ans;
    }

    public static void main(String[] args) {
//        ListNode lbai = new ListNode(3);
//        ListNode lshi = new ListNode(4, lbai);
//        ListNode lge = new ListNode(2,lshi);
//
//        ListNode rbai = new ListNode(4);
//        ListNode rshi = new ListNode(6, rbai);
//        ListNode rge = new ListNode(5,rshi);
//
//        ListNode node = addTwoNumbers(lge, rge);
//        while (node!=null) {
//            System.out.print(" "+node.val);
//            node = node.next;
//        }

        ListNode a = new ListNode(9);
        ListNode b = new ListNode(9, a);
        ListNode c = new ListNode(9, b);
        ListNode d = new ListNode(9, c);
        ListNode e = new ListNode(9, d);
        ListNode f = new ListNode(9, e);
        ListNode g = new ListNode(9, f);

        ListNode a1 = new ListNode(9);
        ListNode b1 = new ListNode(9, a1);
        ListNode c1 = new ListNode(9, b1);
        ListNode d1 = new ListNode(9, c1);

        ListNode node = right(g, d1);
        while (node != null) {
            System.out.print(" " + node.val);
            node = node.next;
        }

    }

}
