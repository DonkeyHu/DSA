package com.donkey.leetcode;

/**
 * 思路：i位置的装水量由 min(i左边的最大值lMax,i右边的最大值rMax) 决定的，如果i的高度比lMax和rMax都要大，则装水量为0
 *
 */
public class Q042_TrapRainWater {

    public static int trap(int[] height) {
        if (height.length == 1 || height.length == 2) {
            return 0;
        }
        int l = 0;
        int r = height.length - 1;
        int lMax = 0;
        int rMax = 0;
        int trap = 0;
        while (l <= r) {
            if (lMax > rMax) {
                trap += Math.max(rMax - height[r], 0);
                rMax = Math.max(height[r], rMax);
                r--;
            } else {
                trap += Math.max(lMax - height[l], 0);
                lMax = Math.max(height[l], lMax);
                l++;
            }
        }

        return trap;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(arr));
    }
}
