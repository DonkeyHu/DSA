package com.donkey.msb.class40;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的
 * 返回其长度
 */
public class Code03_LongestLessSumSubArrLen {

    public static int fun(int[] arr, int k) {
        // [i,j] 以i起头的所有子数组中最小累加和子数组的值
        int[] minSum = new int[arr.length];
        // minSum结尾位置
        int[] minSumEnd = new int[arr.length];

        // 这里也有小技巧，左边依赖右边，右边依赖左边，推导方式不一样
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i == arr.length - 1) {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            } else {
                minSum[i] = minSum[i + 1] <= 0 ? minSum[i + 1] + arr[i] : arr[i];
                minSumEnd[i] = minSum[i + 1] <= 0 ? minSumEnd[i + 1] : i;
            }
        }

        int l = 0;
        int r = 0;
        int sum = 0;
        int ans = 0;
        while (l < arr.length) {
            while (r < arr.length && sum + minSum[r] <= k) {
                sum += minSum[r];
                r = minSumEnd[r] + 1;
            }
            ans = Math.max(ans, r - l);
            if (r > l) {
                sum -= arr[l];
            } else {
                r++;
            }
            l++;
        }

        return ans;
    }


    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            int a1 = fun(arr, k);
            int a2 = maxLength(arr, k);
            if (a1 != a2) {
                System.out.println("Oops!");
                System.out.println(Arrays.toString(arr));
                System.out.println("k:" + k);
                System.out.println("a1:" + a1);
                System.out.println("a2:" + a2);
                break;
            }
        }
        System.out.println("test finish");
//        int[] arr = {13, 4, -3, 6, 8, 2, 4, -6, 13, 12};
//        System.out.println(fun(arr, 1));
    }

}
