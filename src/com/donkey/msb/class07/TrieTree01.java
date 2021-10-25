package com.donkey.msb.class07;

/**
 * 前缀树
 */
public class TrieTree01 {

    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            Node1 node = root;
            node.pass++;
            for (int i = 0; i < str.length; i++) {
                int x = str[i] - 'a';
                if (node.nexts[x] == null) {
                    node.nexts[x] = new Node1();
                }
                node = node.nexts[x];
                node.pass++;
            }
            node.end++;
        }
    }

}
