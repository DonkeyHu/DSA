package com.donkey.msb.class24;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class Code01_SlideWindowMaxValue {

    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }

        int[] res = new int[arr.length - w + 1];

        int[] window = new int[w];

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i + w <= arr.length) {
                for (int j = 0; j < w; j++) {
                    window[j] = arr[j + i];
                }
                int max = Integer.MIN_VALUE;
                for (int j = 0; j < window.length; j++) {
                    max = Math.max(max, window[j]);
                }
                res[index++] = max;
            }
        }
        return res;
    }


    public static int[] window(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        LinkedList<Integer> window = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!window.isEmpty() && arr[window.peekLast()] <= arr[i]) {
                window.pollLast();
            }
            window.addLast(i);
            // 只有保存的是位置 + 这一步才能保证窗口是滑动的
            if (i - window.peekFirst() == w) {
                window.pollFirst();
            }
            if (i + 1 >= w) {
                res[index++] = arr[window.peekFirst()];
            }
        }


        return res;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue + 1);
        }
        return arr;
    }


    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1 == null && arr2 != null) {
            return false;
        }

        if (arr1 != null && arr2 == null) {
            return false;
        }

        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int times = 1000;
        int maxValue = 20;
        int maxLen = 12;
        System.out.println("start");
        for (int i = 0; i < times; i++) {
            int[] testArr = generateRandomArray(maxLen, maxValue);
            int w = (int) (Math.random() * testArr.length + 1);
            int[] ans1 = right(testArr, w);
            int[] ans2 = window(testArr, w);
            if (!isEqual(ans1,ans2)) {
                System.out.println(Arrays.toString(testArr));
                System.out.println(w);
                System.out.println(Arrays.toString(ans1));
                System.out.println(Arrays.toString(ans2));
                break;
            }
        }
        System.out.println("end");
    }
}
