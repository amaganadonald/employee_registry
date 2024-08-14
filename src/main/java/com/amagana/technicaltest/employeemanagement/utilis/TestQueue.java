package com.amagana.technicaltest.employeemanagement.utilis;

import java.util.LinkedList;
import java.util.Queue;

public class TestQueue<T> {
    final Queue<T> q;
    int capacity;
    public  TestQueue(int capacity) {
        this.capacity = capacity;
        q = new LinkedList<>();
    }

    public boolean enQueue(T value) {
        synchronized (q) {
            while(q.size() == capacity) {
                try{
                    q.wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e.getMessage());
                }
            }
            q.add(value);
            q.notifyAll();
            return true;
        }
    }
    public T deQueue() {
        synchronized (q) {
            while(q.isEmpty()) {
                try{
                    q.wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e.getMessage());
                }
            }
            T result =  q.poll();
            q.notifyAll();
            return result;
        }
    }
}
