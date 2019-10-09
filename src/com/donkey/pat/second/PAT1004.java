package com.donkey.pat.second;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PAT1004 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(sc.nextLine());
		}

		int max = 0,min = 101;
		int maxIndex = 0,minIndex = 0;
		for (int j = 0; j < n; j++) {
			String[] sArr = list.get(j).split(" ");
			int temp = Integer.parseInt(sArr[2]);
			if(min > temp) {
				min = temp;
				minIndex = j;
			}
			if(temp > max) {
				max =temp;
				maxIndex = j;
			}
		}
		
		String mins = "";
		String maxs = "";
		String[] minArr = list.get(minIndex).split(" ");
		String[] maxArr = list.get(maxIndex).split(" ");
		
		System.out.println(maxs+maxArr[0]+" "+maxArr[1]);
		System.out.println(mins+minArr[0]+" "+minArr[1]);
	}

}
