package com.donkey.leetcode;

/**
 * 11. 盛最多水的容器
 * <p>
 * 这题我第一反应是子数组问题，遍历以i为结尾的所有子数组[....i]，但是这思路是错误的，我无法在前面子数组中定位到area面积最大
 * <p>
 * 我也想到过滑动数组，但没想到是从两边到中间滑动
 */
public class Q011_ContainerMaxWater {

    public static int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int x = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, x);
            }
        }
        return maxArea;
    }


    public static int maxArea2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right){
            maxArea = Math.max(maxArea, (right- left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            }else {
                right--;
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea2(arr));
    }
}
