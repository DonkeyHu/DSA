package com.donkey.dsa_thu.vector;

import java.util.Arrays;

/**
 * 归并排序
 * @author DonkeyHu
 * @date 2019-07-10
 */
public class MergeSort {
	public static void main(String[] args) {
		int[] a = {12,11,10,9,8,7,6,5};
		mergeSort(a,0,8);
		System.out.println("结果为："+Arrays.toString(a));
	}
	
	public static void merge(int[] elem,int lo,int mid,int hi) {
		int init = lo;
		int lb = mid -lo;
		int[] temp = new int[lb];
		for(int i = 0;i < lb;temp[i++]=elem[lo++]);
		int lc = hi - mid;
		for(int i=init,j=0,k=0;(j<lb) || (k<lc);) {
			int p = mid + k;
			if((j<lb) && (lc<=k || temp[j]<=elem[p])) {
				elem[i++] = temp[j++];
			}
			if((k<lc) && (lb<=j || elem[p]<temp[j])) {
				elem[i++] = elem[p];
				k++;
			}
		}
	}
	
	public static void mergeSort(int[] elem,int lo,int hi) {
		if(hi - lo <2) return;
		int mi = hi + lo >>1;
		mergeSort(elem,lo,mi);
		mergeSort(elem,mi,hi);
		merge(elem,lo,mi,hi);
		System.out.println(Arrays.toString(elem));
	}
	
	


}
