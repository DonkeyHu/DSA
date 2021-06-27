package com.donkey.msb.class01;

/**
 * 在一个有序数组中，找>=某个数最左侧的位置
 */
public class BSNearLeft {

    public static int nearestLeft(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int index = -1;
        int L = 0;
        int R = arr.length - 1;
        // 至少一个数的时候
        while (L <= R) {
            int min = L + ((R - L) >> 1);
            if (arr[min] >= value) {
                index = min;
                R = min - 1;
            } else {
                L = min + 1;
            }
        }
        return index;
    }

    public static int compare(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
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
        int times = 100;
        int maxSize = 50;
        int maxValue = 100;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int num = (int) (Math.random() * (maxSize + 1)) - (int) (Math.random() * (maxSize + 1));
            if (compare(arr,num) != nearestLeft(arr,num)){
                b = false;
                printArray(arr);
                break;
            }
        }
        System.out.println(b ? "Nice!" : "Fucking fucked!");
    }
}
