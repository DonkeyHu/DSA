package com.donkey.dsa_thu.introduction;

/**
 * 数组求和：二分递归
 * @author DonkeyHu
 *
 */
public class SumByRecruit {
	public static void main(String[] args) {
		int a[]= {1,2,3,4,5,6,7,8};
		System.out.println(sum(a,0,a.length-1));
	}
	public static int sum(int[] a,int lo,int hi) {
		//递归基
		if(lo==hi) {
			return a[lo];
		}
		int mid=(lo+hi)>>1;
		return sum(a,lo,mid)+sum(a,mid+1,hi);
	}
}
