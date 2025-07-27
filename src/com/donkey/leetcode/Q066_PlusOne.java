package com.donkey.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q066_PlusOne {
    public static int[] plusOne(int[] digits) {
        List<Integer> res = new ArrayList<>();
        int step = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + step;
            if (sum >= 10) {
                res.add(sum % 10);
                step = 1;
            } else {
                res.add(sum);
                step = 0;
            }
        }
        if (step > 0) {
            res.add(step);
        }
        int[] ans = new int[res.size()];
        int index = 0;
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[index++] = res.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};
        System.out.println(Arrays.toString(plusOne(arr)));
    }
}
