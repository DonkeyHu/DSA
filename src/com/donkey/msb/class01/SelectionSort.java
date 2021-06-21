package com.donkey.msb.class01;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 方案一
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[i] > arr[j]){
//                    int temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }
//            }
//        }

        // 方案二 （更优）
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
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
        int times = 5000;
        int arrMaxSize = 100;
        int arrMaxValue = 100;
        boolean success = true;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(arrMaxSize, arrMaxValue);
            int[] contrastArr = copyArray(arr);
            selectionSort(arr);
            comparator(contrastArr);
            if (!isEqual(arr, contrastArr)) {
                success = false;
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(contrastArr));
                break;
            }
        }
        System.out.println(success ? "nice" : "fucking");
        int[] test = generateRandomArray(arrMaxSize, arrMaxValue);
        System.out.println(Arrays.toString(test));
        selectionSort(test);
        System.out.println(Arrays.toString(test));
    }
}
