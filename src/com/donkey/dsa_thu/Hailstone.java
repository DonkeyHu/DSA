package com.donkey.dsa_thu;
/**
 * 算法：有穷性
 * Hailstone Sequence 这是有穷的步骤吗？ 数学难题，没办法证明是正确的，也没找出是错误的
 * @author DonkeyHu
 *
 */
public class Hailstone {

	public static void main(String[] args) {
		System.out.println(hailstone(7));
	}
	
	public static int hailstone(int n) {
		int length=1;
		while(1<n) {
//			n%2==0 ? n=n/2:n=3*n+1;//不明白这个三目运算为什么会报错
			if(n%2==0) {
				n/=2;
				System.out.println("*:"+n);
			}else {
				n=3*n+1;
				System.out.println("**:"+n);
			}
			length++;
		}
		return length;
	}
}
