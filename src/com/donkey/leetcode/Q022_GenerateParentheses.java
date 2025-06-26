package com.donkey.leetcode;

import java.util.*;

public class Q022_GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        process("",2*n, ans);
        return ans;
    }

    public static void process(String path, int len, List<String> ans) {
        if (path.length() == len) {
            if (isValid(path)) {
                ans.add(path);
            }
            return;
        }
        process(path + "(", len, ans);
        process(path + ")", len, ans);
    }


    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');

        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty() || map.get(stack.pop()) != c) {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(1).toString());
    }
}
