package com.donkey.msb.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数
 */
public class Code04_IPO {

    public static class Program {
        public int c;
        public int p;

        public Program(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    public static int process(int[] costs, int[] profits, int K, int M) {
        PriorityQueue<Program> minCost = new PriorityQueue<>(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.c - o2.c;
            }
        });

        for (int i = 0; i < profits.length; i++) {
            minCost.add(new Program(costs[i], profits[i]));
        }

        PriorityQueue<Program> maxProfit = new PriorityQueue<>(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o2.p - o1.p;
            }
        });

        for (int i = 0; i < K; i++) {
            while (!minCost.isEmpty() && minCost.peek().c <= M) {
                maxProfit.add(minCost.poll());
            }
            if (maxProfit.isEmpty()) {
                return M;
            }
            M += maxProfit.poll().p;
        }
        return M;
    }

}
