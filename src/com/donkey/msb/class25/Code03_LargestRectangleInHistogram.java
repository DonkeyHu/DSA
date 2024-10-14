package com.donkey.msb.class25;


import java.util.Stack;

/**
 * 给定一个非负数组arr，代表直方图
 * 返回直方图的最大长方形面积
 * <p>
 * 测试链接：https://leetcode.com/problems/largest-rectangle-in-histogram
 */
public class Code03_LargestRectangleInHistogram {

    public static void main(String[] args) {

    }

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            // 只要height[i]比栈顶大，那么直接push进栈即可，不用计算
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int h = stack.pop();
                int w = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - w - 1) * heights[h];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int h = stack.pop();
            int w = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (heights.length - w - 1) * heights[h];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

}
