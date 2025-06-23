package com.donkey.msb.class25;

import java.util.Arrays;
import java.util.Stack;

/**
 * 单调栈
 */
public class Code01_MonotonousStack {


    /**
     * 遍历数组每一个元素，得到这个元素左侧和右侧都比这个数小的位置。
     */
    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                int x = stack.pop();
                int left = stack.isEmpty() ? -1: stack.peek();
                res[x][0] = left;
                res[x][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int x = stack.pop();
            int left = stack.isEmpty() ? -1: stack.peek();
            res[x][0] = left;
            res[x][1] = -1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3,5,1,10,6};
        int[][] nearLess = getNearLess(arr);
        for (int i = 0; i < nearLess.length; i++) {
            System.out.println(Arrays.toString(nearLess[i]));
        }
    }

}
