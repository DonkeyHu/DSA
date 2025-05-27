package com.donkey.msb.class32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AC自动机
 */
public class Code03_AC {

    public static class Node {
        public String end;
        public boolean endUse;
        public Node fail;
        public Node[] nexts;

        public Node() {
            this.nexts = new Node[26];
        }
    }


    public static class ACAutomation {
        public Node root;

        public ACAutomation() {
            this.root = new Node();
        }

        // 构建前缀树
        public void buildTrieTree(String str) {
            char[] chars = str.toCharArray();
            // 注意这里得用cur代替，而不是直接用root
            Node cur = root;
            for (char aChar : chars) {
                int index = aChar - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = str;
        }

        /**
         * (1)root的所有直接子节点的fail指针都指向root
         * (2)对于其他节点，使用BFS逐层构建：
         * <p>
         * a.设当前节点为curr，它的父节点为parent，连接它们的字符为c
         * b.从parent的fail指针开始寻找，找到第一个有字符c的转移的节点
         * c.如果找到，curr的fail指针指向那个节点；否则指向root
         */
        public void buildFailPointer() {
            // 宽度优先遍历
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            Node cur;
            Node cfail;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.nexts[i] != null) {
                        cur.nexts[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            if (cfail.nexts[i] != null) {
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }

                }
            }
        }

        // 这里的content是怎么和上面很奇怪的tree联系起来的？
        public List<String> search(String content) {
            List<String> ans = new ArrayList<>();
            char[] chars = content.toCharArray();
            Node cur = root;
            Node follow;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }

                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.endUse) {
                        break;
                    }
                    if (follow.end != null) {
                        follow.endUse = true;
                        ans.add(follow.end);
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        ACAutomation automation = new ACAutomation();
        automation.buildTrieTree("dhe");
        automation.buildTrieTree("he");
        automation.buildTrieTree("abcdhekskk");
        // 设置fail指针
        automation.buildFailPointer();

        List<String> contains = automation.search("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}
