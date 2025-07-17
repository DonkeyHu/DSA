package com.donkey.interview;

import java.util.Stack;

/**
 * 美团一面  括号匹配
 */
public class Code03_Match {
    public static boolean process(String s) {
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '{' || str[i] == '[' || str[i] == '(') {
                stack.push(str[i]);
            }
            if (str[i] == '}') {
                if (stack.isEmpty()) {
                    return false;
                }else {
                    char x = stack.pop();
                    if (x != '{') {
                        return false;
                    }
                }
            }
            if (str[i] == ']') {
                if (stack.isEmpty()) {
                    return false;
                }else {
                    char x = stack.pop();
                    if (x != '[') {
                        return false;
                    }
                }
            }
            if (str[i] == ')') {
                if (stack.isEmpty()) {
                    return false;
                }else {
                    char x = stack.pop();
                    if (x != '(') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
