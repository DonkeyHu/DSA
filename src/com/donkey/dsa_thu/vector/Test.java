package com.donkey.dsa_thu.vector;

import java.util.HashMap;
import java.util.Map;

public class Test {
    static final int MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args) {
//        System.out.println(tableSizeFor(10));
        Map<String,String> map = new HashMap<>();
        map.put("name","donkey");
        map.put("name1","donkey1");
        map.put("name2","donkey2");
        map.put("name3","donkey3");
        map.put("name4","donkey4");
        map.put("name5","donkey5");
        map.put("name6","donkey6");
        map.put("name7","donkey7");
        map.put("name8","donkey8");
        map.put("name9","donkey9");
        map.put("name10","donkey10");
        map.put("name11","donkey11");
        map.put("name12","donkey12");
        System.out.println(map.size());
    }


    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
