package com.donkey.msb.class37;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * 327. 区间和的个数
 * <p>
 * 给定一个数组arr，和两个整数a和b（a<=b）
 * 求arr中有多少个子数组，累加和在[a,b]这个范围上
 * 返回达标的子数组数量
 * <p>
 * 思路：
 * （1）以j结尾的子数组的累加和有多少个在[a,b]，从左往右模型，从左往右遍历j
 * （2）[i,j]的累加和x，转换为前缀和关系 -> x= preSum[j] - preSum[i-1]  (如何这里到了边界条件j=0，怎么处理？)
 * （3）因 a<=x<=b，则 a<=preSum[j] - preSum[i-1]<=b
 * （4）故 prSum[j]-b <=preSum[i-1] <= prSum[j]-a
 * （5）这里的边界情况，当j=0时，可以理解为有个隐含的前缀和为0，即prSum[0]-b <=0 <= prSum[0]-a，符合则加一
 *
 * 解题思路可转换为：j前面有多少前缀和的个数满足prSum[j]-b <=preSum[i-1] <= prSum[j]-a
 */
public class Code01_CountOfRangeNum {

    public static int countSubarraysInRange(int[] nums, int lower, int upper) {
        int count = 0;
        long preSum = 0;
        TreeMap<Long, Integer> map = new TreeMap<>();
        map.put(0l, 1);
        for (int j = 0; j < nums.length; j++) {
            preSum += nums[j];
            long l = preSum - upper;
            long h = preSum - lower;
            Map<Long, Integer> contain = map.subMap(l, true, h, true);
            for (Integer value : contain.values()) {
                count += value;
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    /**
     * 暴力解法 - 用于验证结果
     * 时间复杂度: O(n^2)
     */
    public static int countSubarraysInRangeBruteForce(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int count = 0;
        int n = arr.length;

        // 枚举所有可能的子数组
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum >= a && sum <= b) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 测试用例1
        int[] arr1 = {1, 2, 3, 4};
        int a1 = 3, b1 = 6;
        System.out.println("测试用例1:");
        System.out.println("数组: " + Arrays.toString(arr1));
        System.out.println("范围: [" + a1 + ", " + b1 + "]");
        System.out.println("优化解法结果: " + countSubarraysInRange(arr1, a1, b1));
        System.out.println("暴力解法结果: " + countSubarraysInRangeBruteForce(arr1, a1, b1));
        System.out.println();

        // 测试用例2
        int[] arr2 = {2, -1, 2, 3, 1};
        int a2 = 0, b2 = 4;
        System.out.println("测试用例2:");
        System.out.println("数组: " + Arrays.toString(arr2));
        System.out.println("范围: [" + a2 + ", " + b2 + "]");
        System.out.println("优化解法结果: " + countSubarraysInRange(arr2, a2, b2));
        System.out.println("暴力解法结果: " + countSubarraysInRangeBruteForce(arr2, a2, b2));
        System.out.println();

        // 测试用例3 - 包含负数
        int[] arr3 = {-2, 5, -1};
        int a3 = -3, b3 = 1;
        System.out.println("测试用例3:");
        System.out.println("数组: " + Arrays.toString(arr3));
        System.out.println("范围: [" + a3 + ", " + b3 + "]");
        System.out.println("优化解法结果: " + countSubarraysInRange(arr3, a3, b3));
        System.out.println("暴力解法结果: " + countSubarraysInRangeBruteForce(arr3, a3, b3));
    }

}




