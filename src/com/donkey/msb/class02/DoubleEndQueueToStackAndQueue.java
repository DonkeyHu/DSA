package com.donkey.msb.class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DoubleEndQueueToStackAndQueue {

    public static class Node<T> {
        public T value;
        public Node<T> next;
        public Node<T> last;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class DoubleEndQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T value) {
            Node<T> node = new Node(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.last = node;
                head = node;
            }
        }

        public void addFromTail(T value) {
            Node<T> node = new Node(value);
            if (head == null) {
                tail = node;
                head = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> res = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                res.next = null;
                head.last = null;
            }
            return res.value;
        }

        public T popFromTail() {
            if (head == null) {
                return null;
            }
            Node<T> res = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                res.last = null;
                tail.next = null;
            }
            return res.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class MyStack<T> {
        private DoubleEndQueue<T> queue;

        public MyStack() {
            this.queue = new DoubleEndQueue<>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static class MyQueue<T> {
        private DoubleEndQueue<T> queue;

        public MyQueue() {
            this.queue = new DoubleEndQueue<>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromTail();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int value = 10000;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < testTimes; j++) {
                int num = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    stack.push(num);
                    myStack.push(num);
                } else {
                    if (Math.random() < 0.5) {
                        stack.push(num);
                        myStack.push(num);
                    } else {
                        if (!isEqual(stack.pop(), myStack.pop())) {
                            System.out.println("Stack Error");
                        }
                    }
                }

                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    queue.offer(numq);
                    myQueue.push(numq);
                } else {
                    if (Math.random() < 0.5) {
                        queue.offer(numq);
                        myQueue.push(numq);
                    } else {
                        if (!isEqual(queue.poll(), myQueue.poll())) {
                            System.out.println("Queue Error");
                        }
                    }
                }
            }
        }
        System.out.println("finish");
    }
}

