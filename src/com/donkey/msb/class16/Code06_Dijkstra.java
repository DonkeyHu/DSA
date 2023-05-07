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

}
