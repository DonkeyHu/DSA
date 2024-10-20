package com.donkey.msb.class25;

import java.util.Stack;

public class Code04_MaximalRectangle {

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[] height = new int[matrix[0].length];
        int maxRectangle = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxRectangle = Math.max(largestRectangleArea(height), maxRectangle);
        }
        return maxRectangle;
    }


    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int h = stack.pop();
                int w = stack.isEmpty() ? -1 : stack.peek();
                int area = (i - w - 1) * heights[h];
                maxArea = Math.max(area, maxArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int h = stack.pop();
            int w = stack.isEmpty() ? -1 : stack.peek();
            int area = (heights.length - w - 1) * heights[h];
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }


}
