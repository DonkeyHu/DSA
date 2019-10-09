package com.donkey.pat.second;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 逻辑是对的，但运行超时
 * @author DonkeyHu
 * @date 2019-06-07
 */
public class PAT1007 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		System.out.println(findPrimePair(num));
	}
	
	public static int findPrimePair(int num) {
		List<Integer> prime = new ArrayList<>();
		// 这一块逻辑值得研究
		lable: for (int i = 2; i <= num; i++) {
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					continue lable;
				}
			}
			prime.add(i);
		}
		
		System.out.println(prime.toString());
		if(prime.size() == 1) {
			return 0;
		}
		int result = 0;
		for(int k=1;k<prime.size();k++) {
			if(prime.get(k)-prime.get(k-1)==2) {
				result ++;
			}
		}
		return result;
	}
}
