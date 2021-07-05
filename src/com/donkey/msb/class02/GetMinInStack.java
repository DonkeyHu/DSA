package com.donkey.msb.class02;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2）设计的栈类型可以使用现成的栈结构。
 */
public class GetMinInStack {

    public static class MyStack{
        private Stack<Integer> stackData;
        private Stack<Integer> minStack;

        public MyStack(){
            this.stackData = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int value){
            if (minStack.isEmpty()){
                minStack.push(value);
            }else if (value <= minStack.peek()){
                minStack.push(value);
            }else {
                minStack.push(minStack.peek());
            }
            stackData.push(value);
        }

        public int pop(){
            if (stackData.isEmpty()){
                throw new RuntimeException("stack empty");
            }
            minStack.pop();
            return stackData.pop();
        }

        public int getMinStack(){
            if (minStack.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return minStack.peek();
        }
    }


    public static void main(String[] args) {
        MyStack m1 = new MyStack();
        m1.push(5);
        System.out.println("min "+m1.getMinStack());
        m1.push(6);
        System.out.println("min "+m1.getMinStack());
        m1.push(4);
        System.out.println("min "+m1.getMinStack());
        m1.pop();
        System.out.println("min "+m1.getMinStack());
    }

}
