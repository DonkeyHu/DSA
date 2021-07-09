package com.donkey.msb.class03;

public class MergeSort {

    public static void mergeSort1(int[] arr) {
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
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
    }

    public static void mergeSort2(int[] arr) {
        if (arr == null && arr.length == 1) {
            return;
        }
        int step = 1;
        int N = arr.length;
        while (step < N) {
            int L = 0;
            while (L < N) {
                if (step >= N - L) {
                    break;
                }
                int M = L + step - 1;
                int R = Math.min(M + step, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            step <<= 1;
        }
    }

    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ((int) (Math.random() * maxLen) + 1) - ((int) (Math.random() * maxLen) + 1);
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

    public static boolean isEqual(int[] a, int[] b) {
        if ((a == null && b != null) || (a != null && b == null)) {
            return false;
        }
        if (a == null && b == null) {
            return true;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int maxLen = 20;
        int maxVal = 100;
        int times = 10000;
        System.out.println("Start");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLen, maxVal);
            int[] arr2 = copyArray(arr);
            mergeSort1(arr);
            mergeSort2(arr2);
            if (!isEqual(arr,arr2)){
                System.out.println("Fucking Error");
                break;
            }
        }
        System.out.println("End");
    }
}
