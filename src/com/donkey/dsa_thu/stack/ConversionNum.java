package com.donkey.dsa_thu.stack;

import java.util.Stack;

/**
 * <p>
 *  进制转换
 * </p>
 *
 * @author DonkeyHu
 * @date 2019-10-10
 */
public class ConversionNum {
    public static void main(String[] args) {
        convert(30,16);
    }

    public static void convert(int num, int scale){
        Stack<Character> stack = new Stack();
        char[] c = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(num>0){
            char x = c[num%scale];
            stack.push(x);
            num /= scale;
        }
        while(!stack.empty()){
            System.out.println(stack.pop());
        }
    }
}
