package com.donkey.msb.class04;

import java.util.Arrays;

public class PartitionAndQuickSort {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     *
     * @param arr
     * @param num
     * @return
     */
    public static int partition(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int border = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= num) {
                swap(arr, ++border, i);
            }
        }
        return border;
    }

    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        for (int i = L; i <= R; i++) {
            if (arr[i] <= arr[R]) {
                swap(arr, i, ++lessEqual);
            }
        }
        return lessEqual;
    }

    /**
     * 荷兰国旗问题
     * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int leftBorder = L - 1;
        int rightBorder = R;
        int index = L;
        while (index < rightBorder) {
            if (arr[index] < arr[R]) {
                swap(arr, ++leftBorder, index++);
            } else if (arr[index] == arr[R]) {
                index++;
            } else {
                swap(arr, --rightBorder, index);
            }
        }
        swap(arr, rightBorder, R);
        return new int[]{leftBorder + 1, rightBorder};
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] flag = netherlandsFlag(arr, L, R);
        process2(arr, L, flag[0] - 1);
        process2(arr, flag[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) Math.random() * (R - L + 1), R);
        int[] flag = netherlandsFlag(arr, L, R);
        process2(arr, L, flag[0] - 1);
        process2(arr, flag[1] + 1, R);
    }

    public static int[] generateRondomArray(int maxLen, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxLen + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
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

    // for test
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
        int maxLen = 10;
        int maxVal = 50;
        int times = 10000;
        boolean b = true;
        for (int i = 0; i < times; i++) {
            int[] arr1 = generateRondomArray(maxLen, maxVal);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                b = false;
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                System.out.println(Arrays.toString(arr3));
                break;
            }
        }
        System.out.println(b ? "good" : "bad");
    }

}
