package com.donkey.msb.class29;


import java.util.Random;

/**
 * 普通抽样算法：给定样本数量N抽取M出来获奖，这里的样本数量N是给定的，写死的，提前就知道了。那直接Math.random就好了
 * 蓄水池算法：样本数量是不固定的，每个样本还能等概率获奖。样本数量就像streaming流一直有，中奖的放在一个pool里，保证流中每个样本都能等概率进入pool
 */
public class Code03_ReservoirSampling {
    private static final Random RANDOM = new Random();

    public static int random(int i) {
        return (int) (Math.random() * (i + 1));
    }

    public static void main(String[] args) {
        int streamNum = 120;
        int[] streamArr = new int[streamNum];
        int k = 10;

        for (int j = 0; j < 1000000; j++) {
            int[] pool = new int[k];
            for (int i = 0; i < streamArr.length; i++) {
                if (i < k) {
                    pool[i] = i;
                } else {
                    // [0, i]，这里是有小细节的，一定是 k/（i+1）的概率被选中，如果k=10，i=10时样本数是11
                    int band = RANDOM.nextInt(i + 1);
                    if (band < k) {
                        int index = (int) (Math.random() * k);
                        pool[index] = i;
                    }
                }
            }
            for (int i : pool) {
                streamArr[i]++;
            }
        }
        for (int i = 0; i < streamArr.length; i++) {
            System.out.println(streamArr[i]);
        }
    }

}
