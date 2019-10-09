package com.donkey.pat.second;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PAT1005 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(sc.nextInt());
		}

		for (int j = 0; j < list.size(); j++) {
			int x = list.get(j);
			while (x != 1) {
				if (x % 2 == 0) {
					x = x / 2;
					for (int m = 0; m < list.size(); m++) {
						if (list.get(m) == list.get(j)) {
							continue;
						}
						if (x == list.get(m)) {
							list.remove(m);
							break;
						}
					}
				}else {
					x = x*3+1;
				}
			}
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int q=list.size()-1;q>=0;q--) {
			if(q!=0) {
				sb.append(list.get(q)).append(" ");
			}else {
				sb.append(list.get(q));
			}
		}
		System.out.println(sb.toString());
	}
}
