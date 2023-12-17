package com.donkey.msb.class19;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code02_ConvertToLetterString {

    public static int way1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process1(str.toCharArray(), 0);
    }

    public static int process1(char[] arr, int index) {
        if (index >= arr.length) {
            return 1;
        }
        int p1 = 0;
        int p2 = 0;
        if (arr[index] != '0') {
            p1 = process1(arr, index + 1);
        }
        // 写的勇气
        // 0起头都是无效没有考虑到
        //字符转数字，数字转字符
        if (index + 1 < arr.length && arr[index] != '0' && (arr[index] - '0') * 10 + arr[index + 1] - '0' < 27) {
            p2 = process1(arr, index + 2);
        }
        return p1 + p2;
    }

    public static int dp(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] arr = str.toCharArray();
        int len = arr.length;
        int[] res = new int[len + 1];
        res[len] = 1;

        for (int i = len - 1; i >= 0; i--) {
            int p1 = 0;
            int p2 = 0;
            if (arr[i] != '0') {
                p1 = res[i + 1];
            }
            if (i + 1 < len && arr[i] != '0' && (arr[i] - '0') * 10 + arr[i + 1] - '0' < 27) {
                p2 = res[i + 2];
            }
            res[i] = p1 + p2;
        }
        return res[0];
    }

    public static String generateRandomStr(int len) {
        char[] arr = new char[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return arr.toString();
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int len = (int) (Math.random() * 10);
            String s = generateRandomStr(len);
            if (way1(s) != dp(s)) {
                System.out.println(s);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("Success!");
    }
}
