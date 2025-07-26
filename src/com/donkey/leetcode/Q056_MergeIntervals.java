package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 首先想到了栈，但这个区间里面的值是不是有序的呢?  -> 转换为有序再计算
 */
public class Q056_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> {
            return a[0] -b[0];
        });
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> tmp = new ArrayList<>();
        for(int i = 0; i < intervals.length; i ++ ) {
            List<Integer> l = new ArrayList<>();
            l.add(intervals[i][0]);
            l.add(intervals[i][1]);
            tmp.add(l);
        }
        tmp.sort((a, b) -> {
            return a.get(0) - b.get(0);
        });

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tmp.size(); i++) {
            List<Integer> list = tmp.get(i);
            if (stack.isEmpty()) {
                stack.add(list.get(0));
                stack.add(list.get(1));
            }else {
                // [[1,3],[2,6],[8,10],[15,18]]
                if (list.get(0) <= stack.peek()) {
                    Integer pop = stack.pop();
                    int x = pop >= list.get(1) ? pop : list.get(1);
                    stack.add(x);
                }else {
                    Integer end = stack.pop();
                    Integer start = stack.pop();
                    List<Integer> x = new ArrayList<>();
                    x.add(start);
                    x.add(end);
                    res.add(x);
                    stack.add(list.get(0));
                    stack.add(list.get(1));
                }
            }
        }

        if (!stack.isEmpty()) {
            Integer end = stack.pop();
            Integer start = stack.pop();
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(end);
            res.add(list);
        }

        int[][] ans = new int[res.size()][2];
        for(int i = 0; i < res.size(); i ++) {
            List<Integer> list = res.get(i);
            int[] x = {list.get(0), list.get(1)};
            ans[i] = x;
        }
        return ans;
    }

}
