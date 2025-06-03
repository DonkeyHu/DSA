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
            SkipListNode<K, V> next =  node.nextNodes.get(0);
            return next != null && next.isKeyEqual(k);
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

        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.v : null;
        }

        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).k : null;
        }

        public K lastKey() {
            SkipListNode<K,V> cur = head;
            int level = maxLevel;
            while (level >= 0) {
                SkipListNode<K,V> next = cur.nextNodes.get(level);
                // 记住链表的这种手法，next指针和当前指针
                while (next != null) {
                    cur = next;
                    next = next.nextNodes.get(level);
                }
                level--;
            }
            return cur.k;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.k : null;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.k : less.k;
        }

        public int size() {
            return size;
        }

    }

    // for test
    public static void printAll(SkipListMap<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.k + " , " + next.v + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipListMap<String, String> test = new SkipListMap<>();
        printAll(test);
        System.out.println("======================");
        test.put("A", "10");
        printAll(test);
        System.out.println("======================");
        test.remove("A");
        printAll(test);
        System.out.println("======================");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.containKey("B"));
        System.out.println(test.containKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.remove("D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));


    }

}


