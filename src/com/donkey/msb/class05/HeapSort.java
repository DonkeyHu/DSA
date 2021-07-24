package com.donkey.msb.class05;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {

    public static void heapSort(int[] arr) {

        // 构建堆：方法一
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }

    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int leftChildIndex = index * 2 + 1;
        while (leftChildIndex < heapSize) {
            int largetIndex = (leftChildIndex + 1) < heapSize && arr[leftChildIndex] < arr[leftChildIndex + 1] ? leftChildIndex + 1 : leftChildIndex;
            int m = arr[index] > arr[largetIndex] ? index : largetIndex;
            if (m == index) {
                break;
            }
            swap(arr, index, largetIndex);
            index = largetIndex;
            leftChildIndex = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] generateRondomArray(int maxLen, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ((int) (Math.random() * maxValue) + 1) - ((int) (Math.random() * maxValue) + 1);
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
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
        int maxLen = 100;
        int maxValue = 1000;
        int times = 100000;
        boolean b = true;
        System.out.println("start");
        for (int i = 0; i < times; i++) {
            int[] arr1 = generateRondomArray(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1,arr2)){
                b = false;
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(b?"Great!":"Fucking");
    }
}
