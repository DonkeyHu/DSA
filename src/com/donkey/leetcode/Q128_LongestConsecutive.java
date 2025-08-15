package com.donkey.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 其实这题我是没想到什么思路去解答的，连通线，只维护左右两端点
 */
public class Q128_LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
                int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int postLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                int len = preLen + postLen + 1;
                // 关键是这一步，只需要维护左右两端点就行，中间的点不需要考虑
                map.put(num - preLen, len);
                map.put(num + postLen, len);
                ans = Math.max(ans, len);
            }
        }
        return ans;
    }

}
