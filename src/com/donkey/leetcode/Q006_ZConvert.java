package com.donkey.leetcode;

/**
 * 强行找规律，思路就是强行找出每一行的规律：
 * （1）第一行和最后一行规律一致的
 * （2）中间行是另外的规律
 */
public class Q006_ZConvert {

    public static String convert(String s, int numRows) {
        int len = s.length();
        if (numRows == 1 || len <= numRows) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int index = i;
            boolean flag = true;
            while (index < len) {
                sb.append(s.charAt(index));
                if (i == 0 || i == numRows - 1) {
                    index += 2 * (numRows - 1);
                } else {
                    if (flag) {
                        index += 2 * (numRows - i - 1);
                    } else {
                        index += 2 * i;
                    }
                    flag = !flag;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("AB", 1));
    }
}
