package com.donkey.msb.class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 1）Dijkstra算法必须指定一个源点
 * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
 * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 */
public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra1(Node node) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(node, 0);
        HashSet<Node> selectNode = new HashSet<>();
        Node minNode = getMinDistanceAdNotSelectNode(distanceMap, selectNode);
        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + edge.weight);
                } else {
                    distanceMap.put(to, Math.min(distanceMap.get(to), distance + edge.weight));
                }
            }
            selectNode.add(minNode);
            minNode = getMinDistanceAdNotSelectNode(distanceMap, selectNode);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAdNotSelectNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectNode) {
        Node minNode = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectNode.contains(node) && distance < minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        public Node[] nodes;
        public HashMap<Node, Integer> heapIndexMap;
        public HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            // update
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distance, distanceMap.get(node)));
                heapInsert(node, heapIndexMap.get(node));
            }
            // add
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                heapInsert(node, size++);
            }
            // ignore
        }

        public NodeRecord pop() {
            NodeRecord res = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            distanceMap.remove(nodes[size - 1]);
            heapIndexMap.put(nodes[size - 1], -1);
            nodes[size - 1] = null;
            heapify(0, --size);
            return res;
        }

        private void heapInsert(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }


        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int i, int j) {
            heapIndexMap.put(nodes[i], j);
            heapIndexMap.put(nodes[j], i);
            Node tmp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = tmp;
        }

    }

    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        HashMap<Node, Integer> result = new HashMap<>();
        NodeHeap heap = new NodeHeap(size);
        heap.addOrUpdateOrIgnore(head, 0);
        while (!heap.isEmpty()) {
            NodeRecord record = heap.pop();
            Node cur = record.node;
            Integer distance = record.distance;
            for (Edge edge : cur.edges) {
                heap.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
            }
            result.put(cur, distance);
        }
        return result;
    }


}
