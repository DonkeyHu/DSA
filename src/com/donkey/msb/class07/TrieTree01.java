package com.donkey.msb.class07;

import java.util.HashMap;
import java.util.Set;

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

        // 这个单词出现过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] str = word.toCharArray();
            Node1 node = this.root;
            for (int i = 0; i < str.length; i++) {
                int x = str[i] - 'a';
                if (node.nexts[x] == null) {
                    return 0;
                }
                node = node.nexts[x];
            }
            return node.end;
        }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            char[] str = word.toCharArray();
            Node1 node = this.root;
            node.pass--;
            for (int i = 0; i < str.length; i++) {
                int x = str[i] - 'a';
                // 这里这个判断是为什么呀？
                if (--node.nexts[x].pass == 0) {
                    node.nexts[x] = null;
                    return;
                }
                node = node.nexts[x];
            }
            node.end--;
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] str = pre.toCharArray();
            Node1 node = this.root;
            for (int i = 0; i < str.length; i++) {
                int x = str[i] - 'a';
                if (node.nexts[x] == null) {
                    return 0;
                }
                node = node.nexts[x];
            }
            return node.pass;
        }
    }


    public static class Node2 {
        private int pass;
        private int end;
        private HashMap<Integer, Node2> next;

        public Node2() {
            this.pass = 0;
            this.end = 0;
            this.next = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node2 node = root;
            node.pass++;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int x = (int) str[i];
                if (!node.next.containsKey(x)) {
                    node.next.put(x, new Node2());
                }
                node = node.next.get(x);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (word == null) {
                return;
            }
            Node2 node = root;
            node.pass--;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int x = (int) str[i];
                if (--node.next.get(x).pass == 0) {
                    node.next.remove(x);
                    return;
                }
                node = node.next.get(x);
            }
            node.end--;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            Node2 node = root;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int x = (int) str[i];
                if (!node.next.containsKey(x)) {
                    return 0;
                }
                node = node.next.get(x);
            }
            return node.end;
        }

        public int prefixNum(String word) {
            if (word == null) {
                return 0;
            }
            Node2 node = root;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int x = (int) str[i];
                if (!node.next.containsKey(x)) {
                    return 0;
                }
                node = node.next.get(x);
            }
            return node.pass;
        }
    }

    public static class Right {
        private HashMap<String, Integer> box;

        public Right() {
            this.box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (box.containsKey(word)) {
                return box.get(word);
            } else {
                return 0;
            }
        }

        public int prefixNum(String word) {
            int count = 0;
            for (String str : box.keySet()) {
                if (str.startsWith(word)) {
                    count++;
                }
            }
            return count;
        }
    }



}
