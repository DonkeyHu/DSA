package com.donkey.msb.class04;

import java.util.Arrays;
import java.util.Stack;

public class QuickSortUnrecursive {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int index = L;
        int lBorder = L - 1;
        int rBorder = R;
        while (index < rBorder) {
            if (arr[index] < arr[R]) {
                swap(arr, ++lBorder, index++);
            } else if (arr[index] == arr[R]) {
                index++;
            } else {
                swap(arr, --rBorder, index);
            }
        }
        swap(arr, rBorder, R);
        return new int[]{lBorder + 1, rBorder};
    }

    public static class OP {
        private int l;
        private int r;

        public OP(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }


    public static void unrecursiveQuickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int L = 0;
        int R = arr.length - 1;
        swap(arr, (int) Math.random() * (R + 1), R);
        int[] flag = netherlandsFlag(arr, L, R);
        Stack<OP> stack = new Stack<>();
        stack.push(new OP(L, flag[0] - 1));
        stack.push(new OP(flag[1] + 1, R));
        while (!stack.isEmpty()) {
            OP op = stack.pop();
            if (op.r > op.l) {
                swap(arr, op.l + (int) Math.random() * (op.r - op.l + 1), op.r);
                int[] netherlandsFlag = netherlandsFlag(arr, op.l, op.r);
                stack.push(new OP(op.l, netherlandsFlag[0] - 1));
                stack.push(new OP(netherlandsFlag[1] + 1, op.r));
            }
        }
    }

    // 生成随机数组（用于测试）
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // 拷贝数组（用于测试）
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

    // 对比两个数组（用于测试）
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

    // 打印数组（用于测试）
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
        int maxLen = 10;
        int maxVal = 50;
        int times = 100;
        boolean b = true;
        System.out.println("start");
        for (int i = 0; i < times; i++) {
            int[] arr1 = generateRandomArray(maxLen, maxVal);
            int[] arr2 = copyArray(arr1);
            unrecursiveQuickSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                b = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(b ? "Nice" : "Fucking");
    }
}
