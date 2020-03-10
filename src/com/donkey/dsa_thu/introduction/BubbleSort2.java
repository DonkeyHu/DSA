package com.donkey.dsa_thu.introduction;

import java.util.Arrays;

/**
 * 冒泡排序的第二种解法！
 * @author DonkeyHu
 * @date 2019-03-06
 */
public class BubbleSort2 {
	public static void main(String[] args) {
		int[] a = {8,15,6,22,4,18,2,16,55,57};
		bubble(a);
	}
	
	public static void bubble(int[] a) {
		boolean b = true;
		for (int i=0;i<a.length-1;i++){
			for (int j=0;j<a.length-1-i;j++){
				if (a[j]>a[j+1]){
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
					b = false;
				}
			}
			if (b){
				break;
			}
		}
		System.out.println(Arrays.toString(a));
	}
}
