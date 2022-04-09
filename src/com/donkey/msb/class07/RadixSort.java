package com.donkey.msb.class07;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

    // 范围限制 ：非负数，十进制的数
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    public static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int v : arr) {
            max = Math.max(max, v);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    public static void radixSort(int[] arr, int L, int R, int digit) {
        int radix = 10;
        int[] help = new int[R - L + 1];
        // 这里是没有桶的优化版本的基数排序
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            for (int v : arr) {
                count[getDigit(v, d)]++;
            }
            for (int c = 1; c < count.length; c++) {
                count[c] = count[c] + count[c - 1];
            }
            for (int i = R; i >= L; i--) {
                int v = getDigit(arr[i], d);
                help[count[v] - 1] = arr[i];
                count[v]--;
            }
            for (int i = 0; i < help.length; i++) {
                arr[L + i] = help[i];
            }
        }
    }

    /**
     * 获取一个数位数的值
     *
     * @param v value
     * @param d 个位/百位/千位...
     * @return
     */
    public static int getDigit(int v, int d) {
        return ((v / (int) Math.pow(10, (d - 1))) % 10);
    }

    public static int[] generateRandomArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static int[] copyArr(int[] arr) {
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
        int maxValue = 100;
        int maxSize = 20;
        boolean b = true;
        for (int i = 0; i < 50000; i++) {
            int[] arr1 = generateRandomArr(maxSize, maxValue);
            int[] arr2 = copyArr(arr1);
            radixSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                b = false;
                break;
            }
        }
        System.out.println(b ? "Nice" : "Fucking error");
    }

}
