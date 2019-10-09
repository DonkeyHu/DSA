package com.donkey.dsa_thu.introduction;

import java.util.Arrays;

/**
 * 冒泡排序的第二种解法！
 * @author DonkeyHu
 * @date 2019-03-06
 */
public class BubbleSort2 {
	public static void main(String[] args) {
		int[] a = {8,15,6,22,4,18,2,16};
		bubble(a,8);
	}
	
	public static void bubble(int[] a,int n) {
		boolean b = false;
		while(!b) {
			b=true;
			for(int i=1;i<n;i++) {
				if(a[i-1]>a[i]) {
					int temp = a[i];
					a[i] = a[i-1];
					a[i-1] = temp;
					b = false;
					
				}
			}
			n--;
		}
		System.out.println(Arrays.toString(a));
	}
}
