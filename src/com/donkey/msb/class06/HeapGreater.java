package com.donkey.msb.class06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆(T一定要是非基础类型，有基础类型需求包一层, 因为受hashmap key的唯一值影响)
 */
public class HeapGreater<T> {
    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;

    public HeapGreater(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contain(T v) {
        return indexMap.containsKey(v);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T v) {
        heap.add(v);
        indexMap.put(v, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T res = heap.get(0);
        swap(0, --heapSize);
        heap.remove(heapSize);
        heapify(0);
        indexMap.remove(res);
        return res;
    }

    public void remove(T v) {
        Integer index = indexMap.get(v);
        T replace = heap.get(--heapSize);
        indexMap.remove(v);
        heap.remove(heapSize);
        if (comparator.compare(v, replace) != 0) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public void resign(T v) {
        heapInsert(indexMap.get(v));
        heapify(indexMap.get(v));
    }

    public List<T> getAllElement() {
        List<T> ans = new ArrayList<>();
        for (T t : heap) {
            ans.add(t);
        }
        return ans;
    }

    public void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int index) {
        int leftChild = index * 2 + 1;
        while (leftChild < heapSize) {
            int best = leftChild + 1 < heapSize && comparator.compare(heap.get(leftChild + 1), heap.get(leftChild)) < 0 ? leftChild + 1 : leftChild;
            if (comparator.compare(heap.get(index), heap.get(best)) < 0) {
                break;
            }
            swap(index, best);
            index = best;
            leftChild = index * 2 + 1;
        }
    }


    public void swap(int i, int j) {
        T iValue = heap.get(i);
        T jValue = heap.get(j);
        heap.set(i, jValue);
        heap.set(j, iValue);
        indexMap.put(iValue, j);
        indexMap.put(jValue, i);
    }

}
