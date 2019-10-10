package com.donkey.dsa_thu.stack;

import java.util.Stack;

/**
 * <p>
 *  表达式计算
 * </p>
 *
 * @author DonkeyHu
 * @date 2019-10-10
 */
public class ExpCalculate {
    public static void main(String[] args) {

    }

    public static void evaluate(char[] s){
        Stack<Integer> opnd = new Stack<>();
        Stack<Character> optr = new Stack<>();

        optr.push('$');
        while (!optr.empty()){
            for (int i = 0; i < s.length; i++) {
                if(isNumber(s[i])){
                    opnd.push(Integer.parseInt(String.valueOf(s[i])));
                }else{

                }
            }
        }
    }

    private static boolean isNumber(char a){
        Integer integer = Integer.valueOf(a);
        if(integer>=48 && integer <= 57){
            return true;
        }else{
            return false;
        }
    }

    private static void orderBetween(char top,char s){

    }
}
