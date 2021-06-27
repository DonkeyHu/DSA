package com.donkey.msb.class01;

/**
 * 在一个有序数组中，找<=某个数最右侧的位置
 */
public class BSNearRight {

    public static int nearestRight(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int min = L + ((R - L) >> 1);
            if (arr[min] <= num) {
                index = min;
                L = min + 1;
            } else {
                R = min - 1;
            }
        }
        return index;
    }

    public static int compare(int[] arr, int num) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= num) {
                return i;
            }
        }
        return -1;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) Math.random() * (maxSize + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxSize + 1)) - (int) (Math.random() * (maxSize + 1));
        }
        return arr;
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
        boolean b = true;
        int times = 10000;
        int maxSize = 50;
        int maxValue = 100;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int num = (int) (Math.random() * (maxSize + 1)) - (int) (Math.random() * (maxSize + 1));
            if (compare(arr,num) != nearestRight(arr,num)){
                b = false;
                printArray(arr);
                break;
            }
        }
        System.out.println(b ? "Nice!" : "Fucking fucked!");
    }
}
