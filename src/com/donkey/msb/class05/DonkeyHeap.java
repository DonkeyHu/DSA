package com.donkey.msb.class05;

/**
 * 堆：大根堆/小根堆
 */
public class DonkeyHeap {

    public static class MyMaxHeap {
        public int[] heap;
        public final int limit;
        public int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        // 从下往上构建堆
        public void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 从上往下构建堆
        public void heapify(int[] arr, int index, int heapSize) {
            int leftChildIndex = index * 2 + 1;
            while (leftChildIndex < heapSize) {
                int largestIndex = (leftChildIndex + 1) < heapSize && arr[leftChildIndex] < arr[leftChildIndex + 1] ? leftChildIndex + 1 : leftChildIndex;
                int compare = arr[index] > arr[largestIndex] ? index : largestIndex;
                if (compare == index) {
                    break;
                }
                swap(arr, index, largestIndex);
                index = largestIndex;
                leftChildIndex = index * 2 + 1;
            }
        }

        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        public void push(int v) {
            if (limit == heapSize) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = v;
            heapInsert(heap, heapSize++);
        }
    }

    public static class RightMaxHeap {
        public int[] arr;
        public final int limit;
        public int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("Right max heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }


    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int curLimit = (int) Math.random() * limit + 1;
            MyMaxHeap myMaxHeap = new MyMaxHeap(curLimit);
            RightMaxHeap compareHeap = new RightMaxHeap(curLimit);
            int curOpTimes = (int) Math.random() * limit;
            for (int j = 0; j < curOpTimes; j++) {
                if (myMaxHeap.isEmpty() != compareHeap.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (myMaxHeap.isFull() != compareHeap.isFull()) {
                    System.out.println("Oops!!");
                }
                if (myMaxHeap.isEmpty()) {
                    int v = (int) Math.random() * value + 1;
                    myMaxHeap.push(v);
                    compareHeap.push(v);
                } else if (myMaxHeap.isFull()) {
                    if (myMaxHeap.pop() != compareHeap.pop()) {
                        System.out.println("Oops!!!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int)Math.random()*value;
                        myMaxHeap.push(curValue);
                        compareHeap.push(curValue);
                    }else {
                        if (myMaxHeap.pop() != compareHeap.pop()){
                            System.out.println("Oops!!!!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
