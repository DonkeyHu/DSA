package com.donkey.msb.class02;

/**
 * 递归查找数组最大值
 */
public class GetMax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R-L) >> 1);
        int lMax = process(arr,L,mid);
        int rMax = process(arr,mid+1,R);
        return Math.max(lMax,rMax);
    }

    public static void main(String[] args) {
        int[] a = {2,4,5,6,1,9,18,7,8};
        System.out.println(getMax(a));
    }
}
