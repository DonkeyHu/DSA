package com.donkey.msb.class14;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次
 */
public class Code03_BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static int arrange(Program[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });
        int ans = 0;
        int timeEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            if (timeEnd <= arr[i].start) {
                ans++;
                timeEnd = arr[i].end;
            }
        }
        return ans;
    }

    // 暴力解
    public static int arrange2(Program[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, 0);
    }

    // 还剩下的会议都放在programs里
    // done之前已经安排了多少会议的数量
    // timeLine目前来到的时间点是什么

    // 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
    // 返回能安排的最多会议数量
    public static int process(Program[] arr, int done, int timeLine) {
        if (arr.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].start >= timeLine) {
                max = Math.max(process(copyButExcept(arr, i), done + 1, arr[i].end), max);
            }
        }
        return max;
    }

    public static Program[] copyButExcept(Program[] arr, int x) {
        Program[] newArr = new Program[arr.length - 1];
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != x) {
                newArr[cur++] = arr[i];
            }
        }
        return newArr;
    }

    public static Program[] generateRandom(int maxSize, int maxTime) {
        Program[] arr = new Program[(int) (Math.random() * maxSize)];
        for (int i = 0; i < arr.length; i++) {
            int x = (int) (Math.random() * maxTime);
            int y = (int) (Math.random() * maxTime);
            if (x == y) {
                arr[i] = new Program(x, y + 1);
            } else {
                arr[i] = new Program(Math.min(x, y), Math.max(x, y));
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 4;
        int maxTime = 20;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            Program[] arr = generateRandom(maxSize, maxTime);
            if (arrange(arr) != arrange2(arr)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(arrange(arr));
                System.out.println(arrange2(arr));
                System.out.println("BUG");
                return;
            }
        }
        System.out.println("Finish");
    }

}
