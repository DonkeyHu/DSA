package com.donkey.pat.second;
/**
 * 这一版犯了数组越界的问题,没想过如果T在A前面怎么办
 */
import java.util.Scanner;

public class PAT1003_second {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0;i<n;i++) {
			System.out.println(yesOrNo(sc.next()));
		}
		sc.close();
	}

	public static String yesOrNo(String s) {
		char[] charArray = s.toCharArray();
		
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] != 'P' && charArray[i] != 'A' && charArray[i] != 'T') {
				return "NO";
			}
		}
		
		for (int i = 0; i < charArray.length; i++) {
			// 这样写很容易数组越界的.eg：ATAPA 
			if (charArray[i] == 'P' && charArray[i+1] == 'A' && charArray[i+2] == 'T') {
				if(i==0 && charArray.length == i+2+1) {
					return "YES";
				}
				if(i>0) {
					for(int j=0;j<i;j++) {
						if(charArray[j]!='A') {
							return "NO";
						}
					}
				}
				if(charArray.length-i-2-1>0) {
					for(int k=i+2+1;k<charArray.length;k++) {
						if(charArray[k]!='A') {
							return "NO";
						}
					}
				}
				if(i==charArray.length-i-2-1) {
					return "YES";
				}
			}
		}
		
		
		int n=0;
		int m=0;
		int q=0;
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == 'P') {
				n=i;
			}
		}
		for (int t = 0; t < charArray.length; t++) {
			if (charArray[t] == 'T') {
				m=t-n-1;
				q=charArray.length-t-1;
			}
		}
		if(n==0 && m==0 && q==0) {
			return "NO";
		}
		if(n*m == q) {
			return "YES";
		}else {
			return "NO";
		}
		
	}
}