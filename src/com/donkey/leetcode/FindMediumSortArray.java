package com.donkey.leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 */
public class FindMediumSortArray {
    public static void main(String[] args) {
        int[] a1 = new int[]{1};
        int[] a2 = new int[]{2,3,4,5,6};
        System.out.println(findMediumSortedArray(a1,a2));

    }

    public static double findMediumSortedArray(int[] a, int[] b){
        int lenA = a.length;
        int lenB = b.length;
        if ((lenA+lenB) % 2 == 0){
            int m1 = (lenA+lenB+1)/2;
            int m2 = (lenA+lenB+2)/2;
            return ((double)findKth(a,0,b,0,m1)+(double)findKth(a,0,b,0,m2))/2;
        }else {
            int m = (lenA+lenB+1)/2;
            return (double)findKth(a,0,b,0,m);
        }
    }

    /**
     * 找两个有序数组中第K个数
     * @param a
     * @param pa
     * @param b
     * @param pb
     * @param k
     * @return
     */
    public static int findKth(int[] a,int pa,int[] b,int pb,int k){
        if (pa >= a.length) return b[pb + k-1];
        if (pb >= b.length) return a[pa + k-1];
        if (k == 1) return Math.min(a[pa],b[pb]);
        // 数组越界判定
        int valueA = pa+k/2-1 >= a.length ? Integer.MAX_VALUE:a[pa+k/2-1];
        int valueB = pb+k/2-1 >= b.length ? Integer.MAX_VALUE:b[pb+k/2-1];
        if (valueA > valueB){
           return findKth(a,pa,b,pb+k/2,k-k/2);
        }else{
           return findKth(a,pa+k/2,b,pb,k-k/2);
        }
    }
}
