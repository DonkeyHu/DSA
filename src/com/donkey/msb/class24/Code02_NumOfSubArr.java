package com.donkey.msb.class24;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class Code02_NumOfSubArr {
    // num = 5
    //[8,2,1,1,3]
    public static int right(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        int count = 0;
        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int min = arr[L];
                int max = arr[L];
                // 这里 i是 <=
                for (int i = L; i <= R; i++) {
                    min = Math.min(min, arr[i]);
                    max = Math.max(max, arr[i]);
                }
                if (max - min <= num) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     *
     *     // num = 5
     *     //[8,2,1,1,3,8]
     * 两个结论：
     * （1）[L,R] 达标，则[L,R]内所有的子数组也达标  -> 所以R不用回退
     * （2）[L,R] 不达标，则[L,R右侧]都不达标，[L左侧, R]都不达标
     *
     *  如果子数组 [i,j] 达标，那么 [i,i], [i,i+1], ..., [i,j] 都达标
     *  如果子数组 [i,j] 不达标，那么 [i,j+1], [i,j+2], ... 都不达标
     *
     *  首先子数组问题，还是转换以i开头的所有子数组，或者是以j结尾的所有子数组。这题就是遍历以i开头所有子数组是否满足题意。即L指针
     */
    public static int window(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }

        int N = arr.length;
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int R = 0;
        int count = 0;
        for (int L = 0; L < N; L++) {
            // [L,R）初次不达标！
            while (R < N) {
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);

                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                } else {
                    R++;
                }
            }
            count += R - L;

            // L马上要++了，所以要排除队列中过期的值，正因为排除了过期的值才有后序R++
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
        }
        return count;
    }


    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = window(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }

}
