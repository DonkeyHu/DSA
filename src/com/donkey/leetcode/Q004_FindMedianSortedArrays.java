package com.donkey.leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 *
 * getUpMedian这个方法我都要画格子才能分析清楚
 */
public class Q004_FindMedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int aLen = nums1.length;
        int bLen = nums2.length;
        if (aLen == 0) {
            return (bLen & 1) == 1 ? (double) nums2[bLen / 2] : (double) (nums2[(bLen / 2) - 1] + nums2[bLen / 2]) / 2;
        }
        if (bLen == 0) {
            return (aLen & 1) == 1 ? (double) nums1[aLen / 2] : (double) (nums1[(aLen / 2) - 1] + nums1[aLen / 2]) / 2;
        }

        if (((aLen + bLen) & 1) == 1) {
            int kth = ((aLen + bLen) / 2) + 1;
            return (double) findKthNum(nums1, nums2, kth);
        } else {
            return (double) (findKthNum(nums1, nums2, (aLen + bLen) / 2) + findKthNum(nums1, nums2, (aLen + bLen) / 2 + 1)) / 2;
        }
    }


    // A B有序数组，求第k小的数
    public static int findKthNum(int[] A, int[] B, int kth) {
        int[] sArr = A.length >= B.length ? B : A;
        int[] lArr = A.length >= B.length ? A : B;

        int sLen = sArr.length;
        int lLen = lArr.length;

        if (kth <= sLen) {
            return getUpMedian(sArr, 0, kth - 1, lArr, 0, kth - 1);
        } else if (kth <= lLen) {
            int index = kth - sLen - 1;
            // 剪支一种边界条件
            if (lArr[index] >= sArr[sLen - 1]) {
                return lArr[index];
            } else {
                return getUpMedian(sArr, 0, sLen - 1, lArr, index + 1, kth - 1);
            }
        } else {
            int lIndex = kth - sLen - 1;
            int sIndex = kth - lLen - 1;
            // 剪支一种边界条件，不然差一位
            if (sArr[sIndex] >= lArr[lLen - 1]) {
                return sArr[sIndex];
            }
            if (lArr[lIndex] >= sArr[sLen - 1]) {
                return lArr[lIndex];
            }
            return getUpMedian(sArr, sIndex + 1, sLen - 1, lArr, lIndex + 1, lLen - 1);
        }
    }


    // A[s1...e1]
    // B[s2...e2]
    // 这两段一定等长且都有序
    // 求这两段整体的上中位数，上中位数值返回
    // 举例子，然后分情况去讨论很容易理解
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        if (s1 == e1) {
            return Math.min(A[s1], B[s2]);
        }

        int m1 = (s1 + e1) / 2;
        int m2 = (s2 + e2) / 2;

        if (A[m1] == B[m2]) {
            return A[m1];
        } else {
            // 奇数
            if (((e1 - s1 + 1) & 1) == 1) {
                if (A[m1] > B[m2]) {
                    // 剪支一种边界条件
                    if (B[m2] >= A[m1 - 1]) {
                        return B[m2];
                    } else {
                        return getUpMedian(A, s1, m1 - 1, B, m2 + 1, e2);
                    }
                } else {
                    if (A[m1] >= B[m2 - 1]) {
                        return A[m1];
                    } else {
                        return getUpMedian(A, m1 + 1, e1, B, s2, m2 - 1);
                    }
                }
            } else {
                // 偶数
                if (A[m1] > B[m2]) {
                    return getUpMedian(A, s1, m1, B, m2 + 1, e2);
                } else {
                    return getUpMedian(A, m1 + 1, e1, B, s2, m2);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] A = {1, 2};
        int[] B = {3,4};
        System.out.println(findKthNum(A, B,2));
        System.out.println(findMedianSortedArrays(A, B));
    }

}
