package com.donkey.msb.class09;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * <p>
 * 1）把链表放入数组里，在数组上做partition（笔试用）
 * <p>
 * 2）分成小、中、大三部分，再把各个部分之间串起来（面试用）
 */
public class SmallerEqualBigger {

    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        int x = 0;
        Node cur = head;
        while (cur != null) {
            x++;
            cur = cur.next;
        }
        Node[] arr = new Node[x];
        cur = head;
        x = 0;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = null; // 这里不把链表裂断，我都不知道后续代码为什么会成功
            arr[x++] = cur;
            cur = next;
        }
        arrPartition(arr, pivot);
        for (x = 1; x < arr.length; x++) {
            arr[x - 1].next = arr[x];
        }
        arr[x - 1].next = null;
        return arr[0];
    }

    public static void arrPartition(Node[] arr, int pivot) {
        int small = -1;
        int big = arr.length;
        int index = 0;
        while (index < big) {
            if (arr[index].value < pivot) {
                swap(arr, ++small, index++);
            } else if (arr[index].value == pivot) {
                index++;
            } else {
                swap(arr, --big, index);
            }
        }
    }

    public static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Node listPartition2(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        Node st = null;
        Node sh = null;
        Node et = null;
        Node eh = null;
        Node bt = null;
        Node bh = null;

        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sh == null) {
                    st = head;
                    sh = head;
                } else {
                    st.next = head;
                    st = head; //注意这里相当于尾部指针移动了到head节点了，也确实需要往后挪动一位
                }
            } else if (head.value == pivot) {
                if (eh == null) {
                    eh = head;
                    et = head;
                } else {
                    et.next = head;
                    et = head;
                }
            } else {
                if (bh == null) {
                    bh = head;
                    bt = head;
                } else {
                    bt.next = head;
                    bt = head;
                }
            }
            head = next;
        }

        if (sh != null) {
            st.next = eh != null ? eh : bh;
        }
        if (eh != null) {
            et.next = bh;
        }
        return sh != null ? sh : (eh != null ? eh : bh);
    }

    public static void printLinkedList(Node head){
        System.out.println("Linked List: ");
        while (head != null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(4);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(4);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//         head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);
    }

}
