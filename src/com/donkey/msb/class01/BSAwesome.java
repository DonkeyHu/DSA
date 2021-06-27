package com.donkey.msb.class01;

/**
 * 找出一个数组局部最小值，数组相邻两个数不相等
 */
public class BSAwesome {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;

        while (L < R) {
            int min = L + ((R - L) >> 1);
            if (arr[min] > arr[min - 1]) {
                R = min - 1;
            } else if (arr[min] > arr[min + 1]) {
                L = min + 1;
            } else {
                return min;
            }
        }
        return L;
    }

    public static void main(String[] args) {
        int[] arr = {15, 11, 9, 5, 7, 8, 6, 45, 55};
        System.out.println(getLessIndex(arr));
    }
}
