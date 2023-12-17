package com.donkey.msb.class19;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 */
public class Code03_StickersToSpellWord {

    public static int way1(String s, String[] arr) {
        return process1(s.toCharArray(), arr);
    }

    // 什么时候代表用了一张贴纸了？
    public static int process1(char[] target, String[] arr) {
        if (target.length == 0) {
            return 0;
        }

        int p = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            char[] res = minis(target, arr[i].toCharArray());
            if (res.length != target.length) {
                process1(res, arr);
            }
        }

        return p == 0 ? 0 : p + 1;
    }

    public static char[] minis(char[] str, char[] sticker) {
        int[] tmp = new int[26];
        for (int i = 0; i < str.length; i++) {
            int index = str[i] - '0' - 49;
            tmp[index] += 1;
        }
        for (int i = 0; i < sticker.length; i++) {
            int index = sticker[i] - '0' - 49;
            if (tmp[index] > 0) {
                tmp[index] -= 1;
            }
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
            while (tmp[i] > 0) {
                char x = (char) (i + '0' + 49);
                s.append(x);
                tmp[i] -= 1;
            }
        }
        return s.toString().toCharArray();
    }


    public static void main(String[] args) {
        String str = "babac";
        String[] arr = {"ba", "c", "abcd"};
        System.out.println(way1(str, arr));
    }
}
