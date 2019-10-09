package com.donkey.dsa_thu.introduction;
/**
 * 查询二进制中1的总数
 * @author DonkeyHu
 * @date 2019-04-17
 */
public class CountOnes {
	public static void main(String[] args) {
		System.out.println(countOnes(441));
	}
	
	public static int countOnes(int n) {
		int m = 0;
		while(n>0) {
			m +=(n&1);
			n>>=1;
		}
		return m;
	}
}
