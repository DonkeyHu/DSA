package com.donkey.dsa_thu.stack;

import java.util.Stack;

/**
 * <p>
 *  括号匹配
 * </p>
 *
 * @author DonkeyHu
 * @date 2019-10-10
 */
public class ParenMatch {
    public static void main(String[] args) {
        char[] c = {'{','{','[','(',')',']','}','}','}'};
        char[] b = {'[','(',']',')'};
        match(b);
    }

    public static void match(char[] c){
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < c.length; i++) {
            if ('{' == c[i] || '[' == c[i] || '(' == c[i]){
                s.push(c[i]);
            }else if (!s.empty()){
                if(!parenEquals(s.peek(),c[i])){
                    System.err.println("ERROR,NOT MATCH!");
                    return;
                }
                s.pop();
            }else {
                System.err.println("left empty");
                return;
            }
        }
        if(s.empty()){
            System.out.println("Match Corrrect!");
        }else {
            System.err.println("left surplus!");
        }
    }

    public static boolean parenEquals(char a , char b){
        if(a=='(' && b==')'){
            return true;
        }else if(a=='[' && b==']'){
            return true;
        }else if(a=='{' && b=='}'){
            return true;
        }else {
            return false;
        }
    }

}
