package com.donkey.msb.class28;

/**
 * 理解Manacher算法的重要步骤
 * 1，暴力方法如何寻找最长回文子串
 * 2，Manacher扩展串，可以方便的寻找奇长度、偶长度的回文，扩展字符可以随意设置，不会影响计算
 * 3，回文半径和真实回文长度的对应，真实长度 = p[i] - 1
 * 4，扩展回文串结尾下标和真实回文串终止位置的对应，真实回文串终止位置 = 扩展回文串结尾下标 / 2
 * 5，理解回文半径数组p、理解回文覆盖最右边界r、理解回文中心c，课上会图解
 * 6，Manacher算法的加速过程，当来到的中心点i，如何利用p、r、c来进行回文扩展，课上详细图解
 * a，i没有被r包住，那么以i为中心直接扩展
 * b，i被r包住，对称点 2*c-i 的回文半径，在大回文区域以内，直接确定p[i] = p[2*c-i]
 * c，i被r包住，对称点 2*c-i 的回文半径，在大回文区域以外，直接确定p[i] = r - i
 * d，i被r包住，对称点 2*c-i 的回文半径，撞线大回文区域的边界，从r之外的位置进行扩展
 * 7，Manacher算法的时间复杂度分析，时间复杂度O(n)
 * 8，Manacher算法代码讲解，理解巧妙的while是如何兼顾四种情况的，代码中无需四种情况的判断
 */
public class Code01_Manacher {

    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherStr(s);
        // 回文半径
        int[] pArr = new int[str.length];
        int C = -1;
        // R代表最右的扩成功的位置的下一个位置
        int R = -1;
        int max = -1;
        for (int i = 0; i < str.length; i++) {
            // 这里比较精妙，Math.min是包含 bc两种情况的，且如果是情况b，那么最小值就是b的的值，如果是情况c，那么最小值就是c的结果
            // 也就是这里是算法加速的关键，我们的目的是为了求回文半径，这里回文半径len直接就可以等于一个值了，而不用暴力向两边从头开始扩张了
            int len = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + len < str.length && i - len >= 0) {
                if (str[i + len] == str[i - len]) {
                    len++;
                } else {
                    break;
                }
            }
            if (i + len > R) {
                R = i + len;
                C = i;
            }
            max = Math.max(max, len);
            pArr[i] = len;
        }
        return max - 1;
    }

    public static char[] manacherStr(String s) {
        char[] str = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : str[index++];
        }
        return res;
    }


}
