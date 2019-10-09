package com.donkey.pat.second;

import java.util.ArrayList;
import java.util.List;

public class Test{
	public static void main(String[] args) {
		String s ="aaa bbb 98";
		System.out.println(s.split(" ")[2]);
		List<String[]> list = new ArrayList<>();
		int[] b = new int [5];
		Integer.parseInt(list.get(1)[3]);
		
		int[] a = {7,9,3,12,6,1,8,5};
		a[2] = a[3];
		int temp;
		for(int i=0;i<a.length-1;i++) {
			for(int j=0;j<a.length-1-i;j++) {
				if(a[j]>a[j+1]) {
					temp = a[j+1];
					a[j+1] = a[j];
					a[j] = temp;
				}
			}
		}
		for (int i : a) {
			System.out.println("sort:"+i);
		}
	}
}