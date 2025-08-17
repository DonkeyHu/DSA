package com.donkey.leetcode;

import java.util.*;

/**
 * 勉强能过，没看懂leetcode的最优解怎么解决，思路是：
 * （1）滑动固定窗口，用一个容器去装pLen长度的数据，当容器里的数据长度等于pLen的时候，遍历数据跟目标数据对比
 */
public class Q438_FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        char[] pChars = p.toCharArray();
        char[] sChars = s.toCharArray();
        if (pLen > sLen) {
            return new ArrayList<>();
        }
        Set<Character> set = new HashSet<>();
        for (char c : pChars) {
            set.add(c);
        }
        String target = strSort(p);
        LinkedList<Integer> queue = new LinkedList<>();
        int index = 0;
        while (index < sLen) {
            if (queue.size() == pLen) {
                StringBuilder sb = new StringBuilder();
                for (Integer i : queue) {
                    sb.append(sChars[i]);
                }
                String str = strSort(sb.toString());
                Integer i = queue.pollFirst();
                if (str.equals(target)) {
                    ans.add(i);
                }
            }
            char x = sChars[index];
            if (!set.contains(x)) {
                index++;
                queue.clear();
                continue;
            }
            queue.add(index);
            index++;
        }

        if (queue.size() == pLen) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : queue) {
                sb.append(sChars[i]);
            }
            String str = strSort(sb.toString());
            Integer i = queue.pollFirst();
            if (str.equals(target)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public String strSort(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

}
