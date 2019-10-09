package com.donkey.dsa_thu.introduction;
/**
 * 查找一个数组中最大的两个数，要求比较次数尽量小
 * @author DonkeyHu
 *
 */
public class Max2 {
	/*
	 * 迭代的方式:先选出最大的A[x1]，再从两旁选出次大的A[x2]
	 * 扫描比较次数为：2n-3级别
	 */
	public static void max(int A[],int lo,int hi) {
		int x1=0,x2=0;
		for(int i=0;i<hi;i++) {
			if(A[x1]<A[i]) {
				x1=i;
			}
		}
		for(int j=0;j<x1;j++) {
			if(A[x2]<A[j]) {
				x2=j;
			}
		}
		for(int k=x1+1;k<hi;k++) {
			if(A[x2]<A[k]) {
				x2=k;
			}
		}
		System.out.println("最大的数："+A[x1]);
		System.out.println("次大的数："+A[x2]);
	}
	/*
	 * 迭代方式2：最好情况：1+(n-2)=n-1
	 * 			最坏情况：1+(n-2)*2=2n-3
	 */
	public static void max1(int A[],int lo,int hi) {
		int x1=0,x2=0;
		if(A[x1=lo]<A[x2=lo+1]) {
			int temp=x1;
			x1=x2;
			x2=temp;
		}
		for(int i=lo+2;i<hi;i++) {
			if(A[i]>A[x2]) {
				if(A[x1]<A[x2=i]) {
					int temp=x1;
					x1=x2;
					x2=temp;
				}
			}
		}
		System.out.println("最大的数："+A[x1]);
		System.out.println("次大的数："+A[x2]);
	}
	/*
	 * 递归+分治：
	 */
	public static void max2(int a[],int lo,int hi,int x1,int x2) {
		if(lo+2==hi) {
			System.out.println("最大的数："+a[x1]);
			System.out.println("次大的数："+a[x2]);
			return;
		}
		if(lo+3==hi) {
			System.out.println("最大的数："+a[x1]);
			System.out.println("次大的数："+a[x2]);
			return;
		}
		int mi=(lo+hi)/2;
		int x1L = 0,x2L = 0;
		max2(a,lo,mi,x1L,x2L);
		int x1R = 0,x2R = 0;
		max2(a,mi,hi,x1R,x2R);
		if(a[x1L]>a[x1R]) {
			x1=x1L;
			x2=(a[x2L]>a[x1R])?x2L:x1R;
		}else {
			x1=x1R;
			x2=(a[x2R]>a[x1L])?x2R:x1L;
		}
		
	}
	
	public static void main(String[] args) {
		int a[]= {4,3,8,5,10,19,12,31,9,78};
		max2(a,0,a.length,0,0);
	}
}
