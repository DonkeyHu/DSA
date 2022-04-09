package com.donkey.msb.class01;

import java.util.Arrays;

/**
 * 二分查找：在一个有序数组中，找某个数是否存在
 */
public class BSExist {

    public static boolean exist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid;
        // 这里很精妙解决边界条件的情况，LR至少两位数或者仅有两位数的情况
        while (L < R) {
            mid = L + ((R - L) >> 1); //这里注意移位符的优先级
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return arr[L] == num;
    }

    public static boolean comparator(int[] arr, int num) {
        for (int a : arr) {
            if (a == num) {
                return true;
            }
        }
        return false;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        boolean success = true;
        int times = 1000;
        int maxSize = 50;
        int maxValue = 100;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (comparator(arr,value) != exist(arr,value)){
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
    }
}
