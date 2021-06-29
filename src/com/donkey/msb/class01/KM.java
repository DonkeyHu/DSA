package com.donkey.msb.class01;

import java.util.*;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次，
 * M > 1,  K < M
 * 找到，出现了K次的数，
 * 要求，额外空间复杂度O(1)，时间复杂度O(N)
 */
public class KM {

    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static int[] generateRandom(int maxKinds, int range, int k, int m) {
        int kTimesNum = randomNum(range);
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        int[] arr = new int[k + (numKinds - 1) * m];
        int index = 0;
        for (; index < k; index++) {
            arr[index] = kTimesNum;
        }
        numKinds--;
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(kTimesNum);
        for (int i = numKinds; i > 0; i--) {
            int x = 0;
            do {
                x = randomNum(range);
            } while (hashSet.contains(x));
            for (int j = 0; j < m; j++) {
                arr[index++] = x;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    public static int compare(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.keySet().contains(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        for (int x : map.keySet()) {
            if (map.get(x) == k) {
                return x;
            }
        }
        return -1;
    }

    public static int randomNum(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    public static void main(String[] args) {
        int times = 100;
        int range = 100;
        int maxKinds = 10;
        boolean b = true;
        for (int i = 0; i < times; i++) {
            int a = (int) (Math.random() * 10) + 1;
            int q = (int) (Math.random() * 10) + 1;
            int k = Math.min(a, q);
            int m = Math.max(a, q);
            if (k == m) {
                m++;
            }
            int[] arr = generateRandom(maxKinds, range, k, m);
            int ans1 = onlyKTimes(arr, k, m);
            int ans2 = compare(arr, k, m);
            if (ans1 != ans2) {
                System.out.println(k);
                System.out.println(m);
                System.out.println(Arrays.toString(arr));
                Arrays.sort(arr);
                System.out.println(Arrays.toString(arr));
                System.out.println(ans1);
                System.out.println(ans2);
                b = false;
                break;
            }
        }
        System.out.println(b ? "nice" : "fucking");
    }
}
