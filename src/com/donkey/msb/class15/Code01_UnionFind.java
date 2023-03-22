package com.donkey.msb.class15;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * (1)有若干个样本a、b、c、d…类型假设是V
 * (2)在并查集中一开始认为每个样本都在单独的集合里
 * (3)用户可以在任何时候调用如下两个方法：
 * boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
 * void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
 * (4)isSameSet和union方法的代价越低越好
 */
public class Code01_UnionFind {

    public static class Node<V> {
        public V value;

        public Node(V v) {
            this.value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 给你一个节点，请你往上到不能再往上，把代表返回
        public Node<V> findFather(Node<V> value) {
            Stack<Node<V>> path = new Stack<>();
            while (value != parents.get(value)) {
                path.push(value);
                value = parents.get(value);
            }
            // 链扁平化
            while (!path.isEmpty()) {
                parents.put(path.pop(), value);
            }
            return value;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> headA = findFather(nodes.get(a));
            Node<V> headB = findFather(nodes.get(b));
            if (headA != headB) {
                Integer sizeA = sizeMap.get(headA);
                Integer sizeB = sizeMap.get(headB);
                Node<V> big = sizeA > sizeB ? headA : headB;
                Node<V> small = big == headA ? headB : headA;
                parents.put(small, big);
                sizeMap.put(big, sizeA + sizeB);
                sizeMap.remove(sizeB);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }



}
