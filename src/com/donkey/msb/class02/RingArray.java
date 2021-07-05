package com.donkey.msb.class02;

/**
 * 怎么用数组实现不超过固定大小的队列和栈
 */
public class RingArray {

    public static class MyQueue {
        private int[] arr;
        private int pushi;
        private int polli;
        private int limit;
        private int size;

        public MyQueue(int limit) {
            this.arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了");
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列空了");
            }
            size--;
            int res = arr[polli];
            polli = nextIndex(polli);
            return res;
        }

        public int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

}
