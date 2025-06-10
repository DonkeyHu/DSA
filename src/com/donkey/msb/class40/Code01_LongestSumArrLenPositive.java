package com.donkey.msb.class40;

/**
 * 给定一个正整数组成的无序数组arr，给定一个正整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 */
public class Code01_LongestSumArrLenPositive {

    // 以l开头的每个数组
    public static int fun(int[] arr, int k) {
        int l = 0;
        int r = 0;
        int len = arr.length;
        int sum = 0;
        int ans = 0;
        while (r < len) {
            sum += arr[r];

            while (sum > k && l <= r) {
                sum -= arr[l++];
            }

            if (sum == k) {
                ans = Math.max(ans, r - l + 1);
            }

            r++;
        }
        return ans;
    }


    public static int fun2(int[] arr, int k) {
        // [l, r)
        int l = 0;
        int r = 0;
        int len = arr.length;
        int sum = 0;
        int ans = 0;
        while (r < len) {
            if (sum < k) {
                sum += arr[r++];
            } else if (sum > k) {
                sum -= arr[l++];
            } else {
                ans = Math.max(ans, r - l);
                sum += arr[r++];
            }
        }
        return ans;
    }

    // for test
    public static int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generatePositiveArray(int size, int value) {
        int[] ans = new int[size];
        for (int i = 0; i != size; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len, value);
            int K = (int) (Math.random() * value) + 1;
            int ans1 = fun2(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }


    }

}
