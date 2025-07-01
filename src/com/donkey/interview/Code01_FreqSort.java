package com.donkey.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 汇丰的一道面试题
 *
 * 根据输入数组的词频排序，若词频相同，则维持原来的顺序
 * 输入：1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 8, 9, 0
 * 输出：5, 5, 5, 5, 3, 3, 3, 6, 6, 6, 2, 2, 4, 4, 1, 7, 8, 9, 0
 */
public class Code01_FreqSort {
    public static int[] freqSort(int[] listEle) {
        Map<Integer, Integer> freMap = new HashMap<>();
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < listEle.length; i++) {
            int num = listEle[i];
            freMap.put(num, freMap.getOrDefault(num, 0) + 1);
            if (!indexMap.containsKey(num)) {
                indexMap.put(num, i);
            }
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(
                (a, b) -> {
                    if (a.getValue() != b.getValue()) {
                        return b.getValue() - a.getValue();
                    } else {
                        return indexMap.get(a.getKey()) - indexMap.get(b.getKey());
                    }
                }
        );

        int[] ans = new int[listEle.length];
        queue.addAll(freMap.entrySet());
        int index = 0;
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            Integer key = entry.getKey();
            Integer fre = entry.getValue();
            for (int i = 0; i < fre; i++) {
                ans[index++] = key;
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 8, 9, 0};
        System.out.println(Arrays.toString(freqSort(arr)));
    }
}
