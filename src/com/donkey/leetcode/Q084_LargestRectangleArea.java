package com.donkey.leetcode;

import java.util.Stack;

/**
 * 可以想到是单调栈，从小到大的栈
 */
public class Q084_LargestRectangleArea {

    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                Integer x = stack.pop();
                int y = stack.isEmpty() ? 0 : stack.peek() + 1;
                int area = (i - y) * heights[x];
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer x = stack.pop();
            int y = stack.isEmpty() ? 0 : stack.peek() + 1;
            maxArea = Math.max(maxArea, (heights.length - y) * heights[x]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 2};
        System.out.println(largestRectangleArea(arr));
    }

}
