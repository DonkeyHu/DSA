package com.donkey.msb.class03;

import java.util.Arrays;

/**
 * 在一个数组中，
 * 任何一个前面的数a，和任何一个后面的数b，
 * 如果(a,b)是降序的，就称为逆序对
 * 返回数组中所有的逆序对
 */
public class ReversePair {

    public static int reversePair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        int lNum = process(arr, L, M);
        int rNUm = process(arr, M + 1, R);
        return lNum + rNUm + merge(arr, L, M, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int p1 = M;
        int p2 = R;
        int i = help.length - 1;
        int sum = 0;
        while (p1 >= L && p2 >= M + 1) {
            sum += arr[p1] > arr[p2] ? p2 - M : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 >= M + 1) {
            help[i--] = arr[p2--];
        }

        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        return sum;
    }


    public static int compareM(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[i] > arr[j] ? 1 : 0;
            }
        }
        return sum;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
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

    public static void main(String[] args) {
        int maxSize = 10;
        int maxVal = 100;
        int times = 10000;
        System.out.println("start");
        for (int i = 0; i < times; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxVal);
            int[] arr2 = copyArray(arr1);
            if (reversePair(arr1) != compareM(arr2)){
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                System.out.println("fucking error");
                break;
            }
        }
        System.out.println("end");
    }

}
