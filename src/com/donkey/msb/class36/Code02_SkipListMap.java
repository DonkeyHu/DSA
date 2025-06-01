package com.donkey.msb.class36;

import java.util.ArrayList;

/**
 * 跳表
 */
public class Code02_SkipListMap {

    public static class SkipListNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        // 多层级
        public ArrayList<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K k, V v) {
            this.k = k;
            this.v = v;
            this.nextNodes = new ArrayList<>();
        }

        // 判断当前k是否小于otherKey
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (k == null || k.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (k == null && otherKey == null) || (k != null && otherKey != null && k.compareTo(otherKey) == 0);
        }
    }


    public static class SkipListMap<K extends Comparable<K>, V> {
        public final static double P = 0.5;
        public SkipListNode<K, V> head;
        public int size;
        public int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }


        public SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                // 这里返回的cur，语义：返回当前level小于key的最右节点
                cur = next;
                next = next.nextNodes.get(level);
            }
            return cur;
        }


        public SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            SkipListNode<K, V> cur = head;
            int level = maxLevel;
            while (level >= 0) {
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }

            SkipListNode<K, V> node = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = node.nextNodes.get(0);
            if (find != null && find.isKeyEqual(key)) {
                find.v = value;
            } else {
                size++;
                int newNodeLevel = 0;
                while (P <= Math.random()) {
                    newNodeLevel++;
                }

                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }

                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                for (int i = 0; i <= newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }

                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                // 这里精髓的地方在于一定要从maxLevel往下走，才能加速，才能快！
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    if (newNodeLevel >= level) {
                        SkipListNode<K, V> preNext = pre.nextNodes.get(level);
                        pre.nextNodes.set(level, newNode);
                        newNode.nextNodes.set(level, preNext);
                    }
                    level--;
                }
            }
        }

        public boolean containKey(K k) {
            if (k == null) {
                return false;
            }

            SkipListNode<K, V> node = mostRightLessNodeInTree(k);
            return node != null && node.nextNodes.get(0).isKeyEqual(k);
        }

        public void remove(K k) {
            if (containKey(k)) {
                size--;
                SkipListNode<K, V> pre = head;
                int level = maxLevel;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(k, pre, level);

                    SkipListNode<K, V> node = pre.nextNodes.get(level);
                    if (node != null && node.isKeyEqual(k)) {
                        pre.nextNodes.set(level, node.nextNodes.get(level));
                    }

                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }


    }

}


