package com.donkey.msb.class14;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 * <p>
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * <p>
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价
 * <p>
 * 哈夫曼树！
 */
public class Code02_LessMoneySplitGold {

    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        int sum = 0;
        while (pq.size() > 1) {
            int cur = pq.poll() + pq.poll();
            sum += cur;
            pq.add(cur);
        }
        return sum;
    }


    public static int[] copyMergeTwo(int[] arr, int i, int j) {
        int[] newArr = new int[arr.length - 1];
        int cur = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                newArr[cur++] = arr[k];
            }
        }
        newArr[cur] = arr[i] + arr[j];
        return newArr;
    }


    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(process(copyMergeTwo(arr, i, j), pre + arr[i] + arr[j]), ans);
            }

        }
        return ans;
    }

    public static int lessMoney2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    public static int[] generateRandomArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 6;
        int maxValue = 500;
        int times = 100000;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArr(maxSize, maxValue);
            if (lessMoney(arr) != lessMoney2(arr)) {
                System.out.println("Oops");
                return;
            }
        }
        System.out.println("Finish!");
    }
}
