package com.donkey.leetcode;

/**
 * 14. 最长公共前缀
 */
public class Q014_LongCommonPrefix {

    public static class Node {
        public int count;
        public Node[] nexts;

        public Node() {
            this.nexts = new Node[26];
        }
    }

    public static String longestCommonPrefix(String[] strs) {
        Node head = new Node();
        for (int i = 0; i < strs.length; i++) {
            Node cur = head;
            String str = strs[i];
            char[] chars = str.toCharArray();
            for (char c : chars) {
                int x = c - 'a';
                if (cur.nexts[x] == null) {
                    cur.nexts[x] = new Node();
                    cur.nexts[x].count++;
                } else {
                    cur.nexts[x].count++;
                }
                cur = cur.nexts[x];
            }
        }

        StringBuffer sb = new StringBuffer();
        while (head != null) {
            Node[] nexts = head.nexts;
            int index = 0;
            while (index < 26) {
                if (nexts[index] != null && nexts[index].count == strs.length) {
                    char x = (char) (index + 'a');
                    sb.append(x);
                    break;
                }
                index++;
            }
            if (index == 26) {
                break;
            }
            head = nexts[index];
        }

        return sb.isEmpty() ? "" : sb.toString();
    }

    public static String longestCommonPrefix2(String[] strs) {
        String str = strs[0];
        int minIndex = Integer.MAX_VALUE;
        for (int i = 1; i < strs.length; i++) {
            String compare = strs[i];
            int index = 0;
            while (index < str.length() && index < compare.length()) {
                if (str.charAt(index) != compare.charAt(index)) {
                    break;
                }
                index++;
            }
            minIndex = Math.min(minIndex, index);
            if (index == 0) {
                return "";
            }
        }
        return minIndex == Integer.MAX_VALUE ? str : str.substring(0, minIndex);
    }

    public static void main(String[] args) {
        String[] strs = {"","b"};
        System.out.println(longestCommonPrefix2(strs));
    }

}
