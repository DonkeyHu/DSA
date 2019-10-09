package com.donkey.dsa_thu.introduction;

public class Power2BF {
	public static void main(String[] args) {
		System.out.println(power2bf(29));
	}
	
	public static int power2bf(int n) {
		int m = 1;
		while(0<n--) {
			m <<=1;
		}
		return m;
	}
}
