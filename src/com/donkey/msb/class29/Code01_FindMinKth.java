package com.donkey.msb.class29;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 在无序数组中找到第k小的数，要求时间复杂度是o(n)
 */
public class Code01_FindMinKth {


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 荷兰国旗partition
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                // 这里左边界有边界还是有点模糊的，脑海得有个数组的画面，左边界less恰好是小于pivot的数
                swap(arr, cur++, ++less);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static int process(int[] arr, int L, int R, int index) {
        // base case，index是一定在L和R之间的，如果L==R，则index应该等于L和R
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, L, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, R, index);
        }
    }

    // 快排，时间复杂度O(N)
    public static int minKth(int[] array, int k) {
        int[] arr = Arrays.copyOf(array, array.length);
        return process(arr, 0, arr.length - 1, k - 1);
    }


    // ============================================================

    // 利用bfprt算法，时间复杂度O(N)
    public static int minKth2(int[] array, int k) {
        int[] arr = Arrays.copyOf(array, array.length);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }


    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = medianOfMedian(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    // 拆分成数组大小为5的无数个子数组
    public static int medianOfMedian(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int startIndex = L + i * 5;
            mArr[i] = getMedian(arr, startIndex, Math.min(R, startIndex + 4));
        }
        // 这里为啥还要调用bfprt，直接再调用getMedian行不行？
        return bfprt(mArr, 0, mArr.length - 1, (mArr.length - 1) / 2);
    }

    public static int getMedian(int[] arr, int L, int R) {
        insertSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    public static void insertSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, +j + 1);
            }
        }
    }

    // =========================大根堆=================================

    public static int minKth3(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            heap.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < heap.peek()) {
                heap.poll();
                heap.offer(arr[i]);
            }
        }
        return heap.peek();
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
