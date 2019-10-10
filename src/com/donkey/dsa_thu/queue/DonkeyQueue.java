package com.donkey.dsa_thu.queue;

/**
 * @author DonkeyHu
 * @date 2019-10-10
 */
public interface DonkeyQueue {
    void enqueue(Object obj) throws Exception;
    Object dequeue() throws Exception;
    int getSize();
    boolean empty();
    Object front();
    void traversal();
}
