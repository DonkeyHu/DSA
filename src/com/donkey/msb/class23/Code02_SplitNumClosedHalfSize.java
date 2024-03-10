package com.donkey.msb.class23;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class Code02_SplitNumClosedHalfSize {

    public static int way1(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        int N = arr.length;
        if (N % 2 == 0) {
            return process(0, sum, N / 2, arr);
        } else {
            return Math.max(process(0, sum, N / 2, arr), process(0, sum, (N + 1) / 2, arr));
        }
    }


    public static int process(int index, int rest, int count, int[] arr) {
        if (index == arr.length) {
            return count == 0 ? 0 : -1;
        }
        int A = process(index + 1, rest, count, arr);
        int B = -1;
        if (arr[index] <= rest) {
            int p = process(index + 1, rest - arr[index], count - 1, arr);
            if (p != -1) {
                B = arr[index] + p;
            }
        }
        return Math.max(A, B);
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            return process1(arr, 0, arr.length / 2, sum / 2);
        } else {
            return Math.max(process1(arr, 0, arr.length / 2, sum / 2), process1(arr, 0, arr.length / 2 + 1, sum / 2));
        }
    }

    // arr[i....]自由选择，挑选的个数一定要是picks个，累加和<=rest, 离rest最近的返回
    public static int process1(int[] arr, int i, int picks, int rest) {
        if (i == arr.length) {
            return picks == 0 ? 0 : -1;
        } else {
            int p1 = process1(arr, i + 1, picks, rest);
            // 就是要使用arr[i]这个数
            int p2 = -1;
            int next = -1;
            if (arr[i] <= rest) {
                next = process1(arr, i + 1, picks - 1, rest - arr[i]);
            }
            if (next != -1) {
                p2 = arr[i] + next;
            }
            return Math.max(p1, p2);
        }
    }


            // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = way1(arr);
//            int ans3 = dp2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
//                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");

    }
}
