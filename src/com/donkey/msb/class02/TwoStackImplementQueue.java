package com.donkey.msb.class02;

import java.util.Stack;

/**
 * 如何用栈结构实现队列结构
 */
public class TwoStackImplementQueue {

    public static class TwoStackQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStackQueue() {
            this.stackPop = new Stack<>();
            this.stackPush = new Stack<>();
        }

        public void pushToPop() {
            if (stackPop.isEmpty()){
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void push(int value) {
            stackPush.push(value);
            pushToPop();
        }

        public int pop() {
            if (stackPop.isEmpty() && stackPush.isEmpty()){
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek(){
            if (stackPop.isEmpty() && stackPush.isEmpty()){
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwoStackQueue a = new TwoStackQueue();
        a.push(2);
        a.push(3);
        a.push(4);
        System.out.println(a.peek());
        System.out.println(a.pop());
        System.out.println(a.peek());
        System.out.println(a.pop());
    }
}
