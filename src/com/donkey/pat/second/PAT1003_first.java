package com.donkey.pat.second;

import java.util.Scanner;

/**
 * 第一次做时的思路，遇到瓶颈了(PAAT)，没把字符串转为数组，不好做
 * @author DonkeyHu
 * @date 2018-10-30
 */
public class PAT1003_first {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0;i<n;i++) {
			System.out.println(yesOrNo(sc.next()));
		}
		sc.close();
	}

	public static String yesOrNo(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != 'P' && s.charAt(i) != 'A' && s.charAt(i) != 'T') {
				return "NO";
			}
		}

		if (s.contains("PAT")) {
			String[] array = s.split("PAT");
			if(array.length == 0) {
				return "YES";
			}
			if(array.length == 1) {
				return "NO";
			}
			if (array[0].equals(array[1])) {
				if (array[0].equals("")) {
					return "YES";
				}
				for (int j = 0; j < array[0].length(); j++) {
					if (array[0].charAt(j) != 'A') {
						return "NO";
					}
				}
				return "YES";
			} else {
				return "NO";
			}
		}

		String[] arr = s.split("P");
		if(arr.length == 1 || arr.length == 0) {
			return "NO";
		}
		String[] brr = arr[1].split("T");
		if(brr.length == 1 || brr.length == 0) {
			return "NO";
		}
		for (int k = 0; k < arr[0].length(); k++) {
			if (arr[0].charAt(k) != 'A') {
				return "NO";
			}
		}
		for (int p = 0; p < brr[0].length(); p++) {
			if (brr[0].charAt(p) != 'A') {
				return "NO";
			}
		}
		for (int q = 0; q < brr[1].length(); q++) {
			if (brr[1].charAt(q) != 'A') {
				return "NO";
			}
		}
		int n1 = arr[0].length();
		int n2 = brr[0].length();
		int n3 = brr[1].length();
		if (n1 * n2 == n3 && n2 != 0) {
			return "YES";
		} else {
			return "NO";
		}

	}

}
