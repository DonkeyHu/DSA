package com.donkey.dsa_thu.introduction;
/**
 * 数组倒置
 * 用递归or迭代的方法都ok，注意一下递归基的情况就行了
 * @author DonkeyHu
 *
 */
public class Reverse {
	public static int[] reverse(int[] a,int lo,int hi) {
		int temp=0;
		if(hi>lo) {
			temp=a[hi];
			a[hi]=a[lo];
			a[lo]=temp;
		}else {
			return a;
		}
		reverse(a,lo+1,hi-1);
		return a;
	}
	
	public static void main(String[] args) {
		int[] b= {1,2,3,4,5,6,7,8};
		int[] a=reverse(b,0,b.length-1);
		for (int i : a) {
			System.out.print(i+" ");
		}
	}
}
