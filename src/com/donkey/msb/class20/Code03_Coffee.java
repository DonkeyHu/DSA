package com.donkey.msb.class20;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台洗咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有人咖啡杯变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 */
public class Code03_Coffee {

    public static class Machine {
        private int timePoint;
        private int workTime;

        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }

    public static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    public static int way1(int[] arr, int N, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drink = new int[N];
        for (int i = 0; i < drink.length; i++) {
            Machine m = heap.poll();
            m.timePoint += m.timePoint + m.workTime;
            drink[i] = m.timePoint;
            heap.add(m);
        }
        return bestTime(drink, a, b, 0, 0);
    }

    // free代表机器空闲的时刻，这个变量我怎么也想不出来，怎么去构造
    // 这个返回值我也没想到该怎么返回
    public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }

        // 决定用机器洗
        int t1 = Math.max(drinks[index], free) + wash;
        int p1 = bestTime(drinks, wash, air, index + 1, t1);
        int r1 = Math.max(t1, p1);

        // 决定挥发
        int t2 = drinks[index] + air;
        int p2 = bestTime(drinks, wash, air, index + 1, free);
        int r2 = Math.max(t2, p2);

        return Math.min(r1, r2);
    }


    public static int dp(int[] arr, int wash, int air) {
        int maxFree = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            maxFree = Math.max(arr[i], maxFree) + wash;
        }
        // free的填充我还有些疑惑
        int[][] dp = new int[N + 1][maxFree + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int free = 0; free <= maxFree; free++) {
                int t1 = Math.max(arr[i], free) + wash;
                if (t1 > maxFree) {
                    break;
                }
                int p1 = dp[i + 1][t1];
                int r1 = Math.max(t1, p1);

                int t2 = arr[i] + air;
                int p2 = dp[i + 1][free];
                int r2 = Math.max(t2, p2);

                dp[i][free] = Math.min(r1, r2);
            }
        }

        return dp[0][0];
    }

    public static int way2(int[] arr, int N, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drink = new int[N];
        for (int i = 0; i < drink.length; i++) {
            Machine m = heap.poll();
            m.timePoint += m.timePoint + m.workTime;
            drink[i] = m.timePoint;
            heap.add(m);
        }
        return dp(drink, a, b);
    }


    public static int[] randomArr(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.random() * max + 1;
        }
        return arr;
    }


    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int times = 10;
        for (int i = 0; i < times; i++) {

            int[] arr = randomArr(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;

            int ans1 = way1(arr, n, a, b);
            int ans2 = way2(arr, n, a, b);
            if (ans1 != ans2) {
                System.out.println(arr.toString());
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("success!");
    }

}
