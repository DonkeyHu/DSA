package com.donkey.leetcode;

import java.util.LinkedList;

/**
 * 134. 加油站
 */
public class Q134_GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        boolean[] good = good(gas,cost);
        for (int i = 0; i < len; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] good(int[] g, int[] c) {
        int len = g.length;
        int[] rest = new int[len];
        for (int i = 0; i < len; i++) {
            rest[i] = g[i] - c[i];
        }

        int[] preSum = new int[2 * len];
        preSum[0] = rest[0];
        for (int i = 1; i < preSum.length; i++) {
            if (i < len) {
                preSum[i] = preSum[i - 1] + rest[i];
            } else {
                preSum[i] = preSum[i - 1] + rest[i - len];
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] ans = new boolean[len];
        int index = 0;
        for (int i = 0; i < preSum.length; i++) {
            while (!queue.isEmpty() && preSum[queue.peekLast()] >= preSum[i]) {
                queue.pollLast();
            }
            queue.addLast(i);

            if (i - queue.peekFirst() == len) {
                queue.pollFirst();
            }

            if (i >= len - 1 && index < len) {
                int x = i - len < 0 ? 0 : preSum[i - len];
                ans[index++] = preSum[queue.peekFirst()] - x >= 0 ? true : false;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] g = {2,3,4};
        int[] c = {3,4,3};
        System.out.println(canCompleteCircuit(g,c));
    }
}
