package com.donkey.pat.second;

/**
 * 3n+1 卡尔兹猜想
 * 
 * @author DonkeyHu
 * @date 2018-10-28
 */
public class PAT1001 {

	public static void main(String[] args) {
		
		System.out.println("------>"+calculate(7));
	}

	public static int calculate(int n) {
		int i = 0;
		while (n != 1) {
			if (n % 2 == 0) {
				n = n / 2;
				i++;
			} else {
				n = 3 * n + 1;
			}
		}
		return i;
	}
}
