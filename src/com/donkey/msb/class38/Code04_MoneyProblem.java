package com.donkey.msb.class38;

/**
 * int[] d，d[i]：i号怪兽的能力
 * int[] p，p[i]：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
 * 返回通过所有的怪兽，需要花的最小钱数。
 */
public class Code04_MoneyProblem {

    public static long process1(int[] d, int[] p, int index, int ability) {
        if (index == d.length) {
            return 0;
        }

        if (ability >= d[index]) {
            return Math.min(p[index] + process1(d, p, index + 1, ability + d[index]),
                    process1(d, p, index + 1, ability));
        } else {
            return p[index] + process1(d, p, index + 1, ability + d[index]);
        }
    }

    public static long fun1(int[] d, int[] p) {
        return process1(d, p, 0, 0);
    }




}
