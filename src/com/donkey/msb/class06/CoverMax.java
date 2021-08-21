package com.donkey.msb.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题（用堆的实现):
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class CoverMax {

    /**
     * 暴力解
     */
    public static int coverMaxA(int[][] line) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < line.length; i++) {
            min = line[i][0] < min ? line[i][0] : min;
            max = line[i][1] > max ? line[i][1] : max;
        }
        int res = 0;
        for (double i = min + 0.5; i < max; i++) {
            int standby = 0;
            for (int j = 0; j < line.length; j++) {
                standby = line[j][0] < i && i < line[j][1] ? ++standby : standby; // 注意这里是++standby 而不是standby++
            }
            res = Math.max(standby, res);
        }
        return res;
    }

    public static class Line {
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 堆
     *
     * @return
     */
    public static int coverMaxB(int[][] arr) {
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        // 为什么要排序增加复杂度？
        Arrays.sort(lines, (a, b) -> a.start - b.start);
        int res = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (Line line : lines) {
            heap.add(line.end);
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            res = Math.max(res, heap.size());
        }
        return res;
    }

    public static int[][] generateRandomLine(int N, int L, int R) {
        int[][] arr = new int[(int) (Math.random() * N) + 1][2];
        for (int[] a : arr) {
            int l = L + (int) (Math.random() * (R - L));
            int r = L + (int) (Math.random() * (R - L));
            if (l == r) {
                r++;
            }
            a[0] = Math.min(l, r);
            a[1] = Math.max(l, r);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 10;
        int L = 0;
        int R = 200;
        System.out.println("Start");
        for (int i = 0; i < 10000; i++) {
            int[][] randomLine = generateRandomLine(N, L, R);
            if (coverMaxA(randomLine) != coverMaxB(randomLine)) {
                System.out.println(Arrays.deepToString(randomLine));
                System.out.println("BUG");
                break;
            }
        }
        System.out.println("End");
    }
}
