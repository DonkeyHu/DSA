package com.donkey.msb.class36;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

/**
 * TreeMap详细使用指南
 * TreeMap是基于红黑树实现的NavigableMap，提供有序的键值对存储
 */
public class Code03_RedBlackTree {

    public static void main(String[] args) {
        System.out.println("=== Java TreeMap 使用指南 ===\n");

        // 基本操作演示
        basicOperations();

        // 有序性操作演示
        orderOperations();

        // 范围操作演示
        rangeOperations();

        // 自定义排序演示
        customComparatorDemo();

        // 性能测试
        //performanceComparison();

        // 实际应用场景
        //practicalExamples();
    }

    /**
     * 1. 基本操作演示
     */
    public static void basicOperations() {
        System.out.println("=== 1. 基本操作演示 ===");

        // 创建TreeMap
        TreeMap<Integer, String> treeMap = new TreeMap<>();


        // 插入元素
        treeMap.put(3, "Three");
        treeMap.put(1, "One");
        treeMap.put(4, "Four");
        treeMap.put(2, "Two");
        treeMap.put(5, "Five");

        System.out.println("插入后的TreeMap: " + treeMap);
        System.out.println("注意：键自动按升序排列！");

        System.out.println(treeMap.floorEntry(3));
        System.out.println(treeMap.lowerEntry(3));
        System.out.println("插入后的TreeMap: " + treeMap);

        // 获取元素
        System.out.println("获取键2对应的值: " + treeMap.get(2));
        System.out.println("获取不存在键6的值: " + treeMap.get(6));

        // 检查是否包含
        System.out.println("是否包含键3: " + treeMap.containsKey(3));
        System.out.println("是否包含值\"Five\": " + treeMap.containsValue("Five"));

        // 删除元素
        String removed = treeMap.remove(4);
        System.out.println("删除键4，返回值: " + removed);
        System.out.println("删除后: " + treeMap);

        // 大小和空检查
        System.out.println("TreeMap大小: " + treeMap.size());
        System.out.println("是否为空: " + treeMap.isEmpty());

        System.out.println();
    }

    /**
     * 2. 有序性操作演示
     */
    public static void orderOperations() {
        System.out.println("=== 2. 有序性操作演示 ===");

        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(15, "Fifteen");
        map.put(10, "Ten");
        map.put(25, "Twenty-Five");
        map.put(6, "Six");
        map.put(14, "Fourteen");
        map.put(20, "Twenty");
        map.put(27, "Twenty-Seven");

        System.out.println("TreeMap内容: " + map);

        // 获取第一个和最后一个
        System.out.println("第一个键值对: " + map.firstEntry());
        System.out.println("最后一个键值对: " + map.lastEntry());
        System.out.println("第一个键: " + map.firstKey());
        System.out.println("最后一个键: " + map.lastKey());

        // 获取相邻元素
        System.out.println("键15的下一个键: " + map.higherKey(15));  // >15的最小键
        System.out.println("键15的上一个键: " + map.lowerKey(15));   // <15的最大键
        System.out.println(">=15的最小键: " + map.ceilingKey(15));   // >=15的最小键
        System.out.println("<=15的最大键: " + map.floorKey(15));     // <=15的最大键

        // 对应的Entry版本
        System.out.println("键15的下一个Entry: " + map.higherEntry(15));
        System.out.println("键15的上一个Entry: " + map.lowerEntry(15));
        System.out.println(">=15的最小Entry: " + map.ceilingEntry(15));
        System.out.println("<=15的最大Entry: " + map.floorEntry(15));

        // 弹出首尾元素
        TreeMap<Integer, String> copyMap = new TreeMap<>(map);
        System.out.println("弹出第一个元素: " + copyMap.pollFirstEntry());
        System.out.println("弹出最后一个元素: " + copyMap.pollLastEntry());
        System.out.println("弹出后的map: " + copyMap);

        System.out.println();
    }

    /**
     * 3. 范围操作演示
     */
    public static void rangeOperations() {
        System.out.println("=== 3. 范围操作演示 ===");

        TreeMap<Integer, String> map = new TreeMap<>();
        for (int i = 1; i <= 20; i++) {
            map.put(i, "Value" + i);
        }

        System.out.println("原始map大小: " + map.size());

        // 子映射 - 指定范围
        SortedMap<Integer, String> subMap1 = map.subMap(5, 15);  // [5, 15)
        System.out.println("subMap(5, 15): " + subMap1);

        NavigableMap<Integer, String> subMap2 = map.subMap(5, true, 15, true);  // [5, 15]
        System.out.println("subMap(5, true, 15, true): " + subMap2);

        NavigableMap<Integer, String> subMap3 = map.subMap(5, false, 15, false);  // (5, 15)
        System.out.println("subMap(5, false, 15, false): " + subMap3);

        // 头映射和尾映射
        SortedMap<Integer, String> headMap = map.headMap(10);  // < 10
        System.out.println("headMap(10): " + headMap);

        NavigableMap<Integer, String> headMap2 = map.headMap(10, true);  // <= 10
        System.out.println("headMap(10, true): " + headMap2);

        SortedMap<Integer, String> tailMap = map.tailMap(15);  // >= 15
        System.out.println("tailMap(15): " + tailMap);

        NavigableMap<Integer, String> tailMap2 = map.tailMap(15, false);  // > 15
        System.out.println("tailMap(15, false): " + tailMap2);

        // 重要：子映射是原映射的视图，修改会相互影响
        System.out.println("\n--- 子映射视图特性演示 ---");
        subMap1.put(8, "Modified8");
        System.out.println("修改subMap后，原map中键8的值: " + map.get(8));

        System.out.println();
    }

    /**
     * 4. 自定义排序演示
     */
    public static void customComparatorDemo() {
        System.out.println("=== 4. 自定义排序演示 ===");

        // 1. 使用Comparator.reverseOrder()实现降序
        TreeMap<Integer, String> descendingMap = new TreeMap<>(Comparator.reverseOrder());
        descendingMap.put(3, "Three");
        descendingMap.put(1, "One");
        descendingMap.put(4, "Four");
        descendingMap.put(2, "Two");
        System.out.println("降序TreeMap: " + descendingMap);

        // 2. 字符串长度排序
        TreeMap<String, Integer> lengthMap = new TreeMap<>(
                Comparator.comparing(String::length).thenComparing(String::compareTo)
        );
        lengthMap.put("apple", 1);
        lengthMap.put("pie", 2);
        lengthMap.put("banana", 3);
        lengthMap.put("cat", 4);
        lengthMap.put("elephant", 5);
        System.out.println("按字符串长度排序: " + lengthMap);

        // 3. 自定义对象排序
        TreeMap<Student, String> studentMap = new TreeMap<>();
        studentMap.put(new Student("Alice", 85), "CS");
        studentMap.put(new Student("Bob", 92), "Math");
        studentMap.put(new Student("Charlie", 78), "Physics");
        studentMap.put(new Student("David", 92), "CS");  // 同分数按姓名排序

        System.out.println("学生按成绩排序:");
        studentMap.forEach((student, major) ->
                System.out.println("  " + student + " -> " + major));

        // 4. descendingMap() - 获取逆序视图
        NavigableMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");

        NavigableMap<Integer, String> reversedView = originalMap.descendingMap();
        System.out.println("原始map: " + originalMap);
        System.out.println("逆序视图: " + reversedView);

        // 修改逆序视图会影响原map
        reversedView.put(4, "Four");
        System.out.println("修改逆序视图后，原map: " + originalMap);

        System.out.println();
    }

    /**
     * 学生类，用于演示自定义对象排序
     */
    static class Student implements Comparable<Student> {
        String name;
        int score;

        Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Student other) {
            // 先按分数降序，分数相同按姓名升序
            int scoreCompare = Integer.compare(other.score, this.score);
            return scoreCompare != 0 ? scoreCompare : this.name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return name + "(" + score + ")";
        }
    }

    /**
     * 5. 性能比较
     */
    public static void performanceComparison() {
        System.out.println("=== 5. 性能比较 ===");

        int size = 100000;

        // HashMap vs TreeMap 插入性能
        long start = System.currentTimeMillis();
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            hashMap.put(i, "Value" + i);
        }
        long hashMapInsertTime = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            treeMap.put(i, "Value" + i);
        }
        long treeMapInsertTime = System.currentTimeMillis() - start;

        System.out.println("插入" + size + "个元素:");
        System.out.println("  HashMap耗时: " + hashMapInsertTime + "ms");
        System.out.println("  TreeMap耗时: " + treeMapInsertTime + "ms");

        // 查找性能比较
        Random random = new Random();
        int[] searchKeys = new int[10000];
        for (int i = 0; i < searchKeys.length; i++) {
            searchKeys[i] = random.nextInt(size);
        }

        start = System.currentTimeMillis();
        for (int key : searchKeys) {
            hashMap.get(key);
        }
        long hashMapSearchTime = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        for (int key : searchKeys) {
            treeMap.get(key);
        }
        long treeMapSearchTime = System.currentTimeMillis() - start;

        System.out.println("查找" + searchKeys.length + "次:");
        System.out.println("  HashMap耗时: " + hashMapSearchTime + "ms");
        System.out.println("  TreeMap耗时: " + treeMapSearchTime + "ms");

        System.out.println();
    }

    /**
     * 6. 实际应用场景演示
     */
    public static void practicalExamples() {
        System.out.println("=== 6. 实际应用场景演示 ===");

        // 场景1: 成绩统计系统
        gradeStatisticsExample();

        // 场景2: 时间序列数据
        timeSeriesExample();

        // 场景3: 范围查询
        rangeQueryExample();

        // 场景4: LRU缓存的一种实现方式
        lruCacheExample();
    }

    /**
     * 场景1: 成绩统计系统
     */
    public static void gradeStatisticsExample() {
        System.out.println("--- 场景1: 成绩统计系统 ---");

        TreeMap<Integer, List<String>> gradeMap = new TreeMap<>(Comparator.reverseOrder());

        // 添加学生成绩
        addStudent(gradeMap, "Alice", 95);
        addStudent(gradeMap, "Bob", 87);
        addStudent(gradeMap, "Charlie", 95);
        addStudent(gradeMap, "David", 78);
        addStudent(gradeMap, "Eve", 87);
        addStudent(gradeMap, "Frank", 92);

        System.out.println("成绩排行榜 (按分数降序):");
        gradeMap.forEach((score, students) ->
                System.out.println("  " + score + "分: " + students));

        // 查找90分以上的学生
        System.out.println("90分以上的学生:");
        gradeMap.tailMap(90).forEach((score, students) ->
                students.forEach(student -> System.out.println("  " + student + ": " + score)));

        System.out.println();
    }

    private static void addStudent(TreeMap<Integer, List<String>> gradeMap, String name, int score) {
        gradeMap.computeIfAbsent(score, k -> new ArrayList<>()).add(name);
    }

    /**
     * 场景2: 时间序列数据
     */
    public static void timeSeriesExample() {
        System.out.println("--- 场景2: 时间序列数据 ---");

        TreeMap<Long, Double> temperatureData = new TreeMap<>();

        // 模拟24小时温度数据（时间戳 -> 温度）
        long baseTime = System.currentTimeMillis();
        Random random = new Random();

        for (int i = 0; i < 24; i++) {
            long timestamp = baseTime + i * 3600000L; // 每小时一个数据点
            double temperature = 20 + random.nextGaussian() * 5; // 平均20度，标准差5度
            temperatureData.put(timestamp, temperature);
        }

        System.out.println("24小时温度数据 (前5个):");
        temperatureData.entrySet().stream().limit(5).forEach(entry -> {
            Date date = new Date(entry.getKey());
            System.out.printf("  %tH:%tM -> %.1f°C%n", date, date, entry.getValue());
        });

        // 查找某个时间段的数据
        long startTime = baseTime + 8 * 3600000L;  // 8点
        long endTime = baseTime + 18 * 3600000L;   // 18点

        NavigableMap<Long, Double> workingHours = temperatureData.subMap(startTime, true, endTime, true);
        double avgTemp = workingHours.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);
        System.out.printf("工作时间(8-18点)平均温度: %.1f°C%n", avgTemp);

        System.out.println();
    }

    /**
     * 场景3: 范围查询 - 商品价格区间搜索
     */
    public static void rangeQueryExample() {
        System.out.println("--- 场景3: 商品价格区间搜索 ---");

        TreeMap<Double, List<String>> priceMap = new TreeMap<>();

        // 添加商品数据
        addProduct(priceMap, "iPhone", 999.99);
        addProduct(priceMap, "iPad", 599.99);
        addProduct(priceMap, "MacBook", 1299.99);
        addProduct(priceMap, "AirPods", 179.99);
        addProduct(priceMap, "Apple Watch", 399.99);
        addProduct(priceMap, "Magic Mouse", 79.99);
        addProduct(priceMap, "Keyboard", 129.99);

        // 查找价格在200-800美元之间的商品
        System.out.println("价格在$200-$800之间的商品:");
        NavigableMap<Double, List<String>> priceRange = priceMap.subMap(200.0, true, 800.0, true);
        priceRange.forEach((price, products) ->
                products.forEach(product -> System.out.printf("  %s: $%.2f%n", product, price)));

        // 查找最便宜和最贵的商品
        System.out.printf("最便宜商品: %s ($%.2f)%n",
                priceMap.firstEntry().getValue().get(0), priceMap.firstKey());
        System.out.printf("最贵商品: %s ($%.2f)%n",
                priceMap.lastEntry().getValue().get(0), priceMap.lastKey());

        System.out.println();
    }

    private static void addProduct(TreeMap<Double, List<String>> priceMap, String product, double price) {
        priceMap.computeIfAbsent(price, k -> new ArrayList<>()).add(product);
    }

    /**
     * 场景4: 基于TreeMap的简单LRU缓存实现
     */
    public static void lruCacheExample() {
        System.out.println("--- 场景4: 基于访问时间的缓存管理 ---");

        // 使用TreeMap按访问时间排序
        TreeMap<Long, String> cache = new TreeMap<>();
        int maxSize = 3;

        // 模拟缓存操作
        putCache(cache, "data1", maxSize);
        sleep(1);
        putCache(cache, "data2", maxSize);
        sleep(1);
        putCache(cache, "data3", maxSize);
        sleep(1);

        System.out.println("缓存状态: " + cache.values());

        // 添加第4个元素，应该淘汰最老的
        putCache(cache, "data4", maxSize);
        System.out.println("添加data4后: " + cache.values());

        // 访问data2（更新其时间戳）
        updateCache(cache, "data2");
        System.out.println("访问data2后: " + cache.values());

        // 再添加一个元素
        putCache(cache, "data5", maxSize);
        System.out.println("添加data5后: " + cache.values());

        System.out.println();
    }

    private static void putCache(TreeMap<Long, String> cache, String data, int maxSize) {
        // 如果缓存满了，移除最老的元素
        if (cache.size() >= maxSize) {
            cache.pollFirstEntry();
        }
        cache.put(System.nanoTime(), data);
    }

    private static void updateCache(TreeMap<Long, String> cache, String data) {
        // 找到并移除旧的条目
        cache.entrySet().removeIf(entry -> entry.getValue().equals(data));
        // 添加新的时间戳
        cache.put(System.nanoTime(), data);
    }

    private static void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}