package com.donkey.msb.class05;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * 请选择一个合适的排序策略，对这个数组进行排序
 */
public class SortArrayDistanceLessK {

    public static void sortArrayDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length - 1, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; index++, i++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static int[] generateRandomArrayNoMoreK(int maxLen, int maxVal, int k) {
        int[] arr = new int[(int) (Math.random() * (maxLen + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ((int) (Math.random() * (maxLen + 1))) - ((int) (Math.random() * (maxLen + 1)));
        }
        Arrays.sort(arr);
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (k + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
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

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxVal = 1000;
        int times = 10000;
        boolean succeed = true;
        for (int i = 0; i < times; i++) {
            int k = (int) (Math.random() * maxLen);
            int[] arr1 = generateRandomArrayNoMoreK(maxLen, maxVal, k);
            int[] arr2 = copyArray(arr1);
            Arrays.sort(arr2);
            sortArrayDistanceLessK(arr1,k);
            if (!isEqual(arr1,arr2)){
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed?"Great!":"Fucking");
    }

}
