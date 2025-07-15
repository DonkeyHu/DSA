package com.donkey.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Traveloka面试题
 * <p>
 * 有N个不同高度的盒子，每一步挪出最高堆那个盒子，使其等于次高堆，问最少多少步骤把堆抹平
 * 例子：如
 * [5,2,1] -> [2,2,1]
 * [2,2,1] -> [2,1,1]
 * [2,1,1] -> [1,1,1]
 * 一共三步
 *
 * 哎排序后滑动指针就可以做，当时怎么就想不到，太想依赖AI工具了，不行的
 */
public class Code02_BoxProblem {

    public static int minStep(List<Integer> box) {
        int len = box.size();
        box.sort((a, b) -> b - a);
        int[] arr = new int[len];
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = box.get(i);
        }
        int l = 0;
        int r = 0;
        int step = 0;
        while (r < len) {
            if (arr[l] == arr[r]) {
                r++;
            } else {
                step += r;
                l = r;
                r++;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>();
        test.add(4);
        test.add(5);
        test.add(5);
        test.add(4);
        test.add(2);
        System.out.println(minStep(test));
    }

}
