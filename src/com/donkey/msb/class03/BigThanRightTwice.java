package com.donkey.msb.class03;

import java.util.Arrays;

/**
 * 在一个数组中，
 * 对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 */
public class BigThanRightTwice {

    public static int biggerTwice(int[] arr) {
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
        int lSum = process(arr, L, M);
        int rSum = process(arr, M + 1, R);
        return lSum + rSum + merge(arr, L, M, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int sum = 0;
        int windowR = M + 1;
        for (int i = L; i <= M; i++) {
            while (windowR <= R && arr[i] > arr[windowR] * 2) {
                windowR++;
            }
            sum += windowR - M - 1;
        }
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
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
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                res += arr[i] > arr[j] * 2 ? 1 : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
        int maxSize = 100;
        int maxVal = 500;
        int times = 50000;
        System.out.println("start");
        for (int i = 0; i < times; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxVal);
            int[] arr2 = copyArray(arr1);
            if (biggerTwice(arr1) != compareM(arr2)){
                System.out.println("Fucking error");
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }
        System.out.println("end");
    }
}
