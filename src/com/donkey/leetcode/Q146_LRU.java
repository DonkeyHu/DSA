package com.donkey.leetcode;


import java.util.HashMap;

/**
 * 这题我开始想到了队列之类的数据结构，因为有先进先出的语义（隐含时间语义），方便淘汰数据
 * <p>
 * 这题的思路是hash表+双向链表
 */
public class Q146_LRU {

    MyCache<Integer, Integer> cache;

    public Q146_LRU(int capacity) {
        cache = new MyCache<>(capacity);
    }

    public int get(int key) {
        return cache.get(key);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> last;
        Node<K, V> next;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class DoubleLinkedNode<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public DoubleLinkedNode() {
            this.head = null;
            this.tail = null;
        }

        public void addNode(Node<K, V> node) {
            if (node == null) {
                return;
            }
            if (head == null) {
                head = node;
                tail = node;
            }else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public void removeNodeToTail(Node<K, V> node) {
            if (tail == node) {
                return;
            }

            if (head == node) {
                head = head.next;
                head.last = null;
            }else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.next = null;
            node.last = tail;
            tail.next = node;
            tail = node;
        }

        public void removeHeadNode() {
            if (head == null) {
                return;
            }
            Node<K, V> res = head;
            if (head == tail) {
                head = null;
                tail = null;
            }else {
                head = head.next;
                head.last = null;
                // 这里还有个指向的
                res.next = null;
            }
        }
    }

    public static class MyCache<K, V> {
        HashMap<K, Node<K, V>> map;
        DoubleLinkedNode<K, V> link;
        final int capacity;

        public MyCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.link = new DoubleLinkedNode<>();
        }

        public V get(K k) {
            if (map.containsKey(k)) {
                Node<K, V> node = map.get(k);
                link.removeNodeToTail(node);
                return node.value;
            }
            return null;
        }

        public void put(K k , V v) {
            if (map.containsKey(k)) {
                Node<K, V> node = map.get(k);
                node.value = v;
                link.removeNodeToTail(node);
            }else {
                if (map.size() == capacity) {
                    Node<K, V> node = link.head;
                    link.removeHeadNode();
                    map.remove(node.key);
                }
                Node<K, V> node = new Node<>(k, v);
                map.put(k, node);
                link.addNode(node);
            }
        }
    }

}
