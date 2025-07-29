package com.donkey.leetcode;

import java.util.PriorityQueue;

/**
 * 首先可能会想到用堆来解答
 */
public class Q088_MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            heap.add(nums1[i]);
        }

        for (int i = 0; i < n; i++) {
            heap.add(nums2[i]);
        }

        for (int i = 0; i < m + n; i++) {
            nums1[i] = heap.poll();
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[--index] = nums1[--m];
            } else {
                nums1[--index] = nums2[--n];
            }
        }
        while (m > 0) {
            nums1[--index] = nums1[--m];
        }
        while (n > 0) {
            nums1[--index] = nums2[--n];
        }
    }

}
