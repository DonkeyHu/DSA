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
	 * JAVA 没有指针很恶心，不知怎么改变入参的值，只想到传入对象
	 */
	public static void max2(int a[],int lo,int hi,NumObj obj) {
		if(lo+2==hi) {
			if (a[lo] > a[lo+1]){
				obj.x1 = lo ;
				obj.x2 = lo + 1;
			}else {
				obj.x1 = lo + 1;
				obj.x2 = lo;
			}
			return;
		}
		if(lo+3==hi) {
			if (a[lo] > a[lo+1]){
				obj.x1 = lo ;
				obj.x2 = lo + 1;
			}else {
				obj.x1 = lo + 1;
				obj.x2 = lo;
			}
			if (a[obj.x1] > a[lo+2]){
				obj.x2 = a[obj.x2] > a[lo+2] ? obj.x2:lo+2;
			}else {
				obj.x2 = obj.x1;
				obj.x1 = lo + 2;
			}
			return;
		}
		int mi=(lo+hi)/2;
		NumObj left = new NumObj();
		max2(a,lo,mi,left);
		int x1L = left.x1,x2L = left.x2;
		NumObj right = new NumObj();
		max2(a,mi,hi,right);
		int x1R = right.x1,x2R = right.x2;
		if(a[x1L]>a[x1R]) {
			obj.x1=x1L;
			obj.x2=(a[x2L]>a[x1R])?x2L:x1R;
		}else {
			obj.x1=x1R;
			obj.x2=(a[x2R]>a[x1L])?x2R:x1L;
		}
		System.out.println("最大的数："+a[obj.x1]);
		System.out.println("次大的数："+a[obj.x2]);
	}
	
	public static void main(String[] args) {
		int a[]= {4,3,8,5,10,19,12,31,9,78};
		NumObj max = new NumObj();
		max2(a,0,a.length,max);
	}
}

class NumObj{
	int x1;
	int x2;

	public NumObj() {
	}

	public NumObj(int x1, int x2) {
		this.x1 = x1;
		this.x2 = x2;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}
}
