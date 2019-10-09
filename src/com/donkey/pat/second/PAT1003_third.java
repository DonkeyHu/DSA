package com.donkey.pat.second;
import java.util.Scanner;

public class PAT1003_third {
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
		
		int pCount = 0,aCount = 0, tCount = 0;
		for(int i=0;i<charArray.length;i++) {
			if(charArray[i] == 'P') {
				pCount++;
			}else if(charArray[i] == 'A') {
				aCount++;
			}else if(charArray[i] == 'T') {
				tCount++;
			}else {
				return "NO";
			}
		}
		
		if(pCount == 1 && tCount ==1 && aCount >=1) {
			int pIndex = s.indexOf('P');
			int tIndex = s.indexOf('T');
			if(tIndex > pIndex) {
				int a = pIndex;
				int b = tIndex-pIndex-1;
				int c =s.length()-tIndex-1;
				if(a*b == c) {
					return "YES";
				}
			}
		}
		return "NO";
	}
}