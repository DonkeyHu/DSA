package com.donkey.msb.class01;

public class EvenTimesOldTimes {

    /**
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
     *
     * @param arr
     * @return
     */
    public static int findOddTimesNum(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor = eor ^ arr[i];
        }
        System.out.println(eor);
        return eor;
    }

    /**
     * 计算一个数中含有bit 1的个数
     *
     * @param a
     * @return
     */
    public static int bit1Counts(int a) {
        int count = 0;
        while (a != 0) {
            // 1011 1000
            // 0100 0111
            // 0100 1000
            // 0000 1000
            // 等同于写法 a & -a
            int rightOne = a & ((~a) + 1); //怎么把一个int类型的数，提取出最右侧的1来
            count++;
            // 1011 0000
            a ^= rightOne;
        }
        return count;
    }

    /**
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
     *
     * @param arr
     */
    public static void findOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (-eor);

        int eorx = 0;

        for (int i = 0; i < arr.length; i++) {
            if ((rightOne & arr[i]) != 0) {
                eorx ^= arr[i];
            }
        }
        System.out.println(eorx + " "+(eorx^eor));
    }

    public static void main(String[] args) {
        int[] a = {3,3,3,5,5,5,66,66,88,88,2,2,2,2,6,6,6,6};
        findOddTimesNum2(a);

        int[] b = {3,3,3,3,5,5,5,66,66,88,88,2,2,2,2,6,6,6,6};
        findOddTimesNum(b);

        System.out.println(bit1Counts(11));
    }
}
