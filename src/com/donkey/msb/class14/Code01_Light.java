package com.donkey.msb.class14;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code01_Light {

    // 贪心解法
    public static int minLight(String road) {
        char[] arr = road.toCharArray();
        int cur = 0;
        int light = 0;
        while (cur < arr.length) {
            if (arr[cur] == 'X') {
                cur++;
            } else {
                light++;
                if (cur + 1 == arr.length) {
                    break;
                } else {
                    if (arr[cur + 1] == 'X') {
                        cur = cur + 2;
                    } else {
                        cur = cur + 3;
                    }
                }
            }
        }
        return light;
    }

    // TODO 点灯问题暴力解搞不懂

}
