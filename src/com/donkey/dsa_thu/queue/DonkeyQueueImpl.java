package com.donkey.dsa_thu.queue;

/**
 * @author DonkeyHu
 * @date 2019-10-10
 */
public class DonkeyQueueImpl implements DonkeyQueue {
    private final static int CAPACITY = 1000;
    private int capacity;
    private int f = 0;
    private int r = 0;
    private Object[] obj;

    public DonkeyQueueImpl() {
        this(CAPACITY);
    }

    public DonkeyQueueImpl(int capacity) {
        this.capacity = capacity;
        obj = new Object[capacity];
    }

    @Override
    public void enqueue(Object o) throws Exception {
        if(getSize() == capacity-1){
            throw new Exception("the queue is full..");
        }
        this.obj[r] = o;
        r = (r+1)%capacity;
    }

    @Override
    public Object dequeue() throws Exception {
        if(empty()){
            throw new Exception("the queue is empty...");
        }
        Object elem = this.obj[f];
        this.obj[f] = null;
        f = (f+1)%capacity;
        return elem;
    }

    @Override
    public int getSize() {
        return (r-f+capacity) % capacity;
    }

    @Override
    public boolean empty() {
        return (r-f)>0?false:true;
    }

    @Override
    public Object front() {
        Object elem = this.obj[f];
        return elem;
    }

    @Override
    public void traversal() {
        for (int i = f; i < r; i++) {
            System.out.println(obj[i] + " ");
        }
    }

}
