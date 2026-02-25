package org.example.assignment1.Queue;

public class ArrayQueue<E> implements QueueInterface<E>{
    private E[] queue;
    private int front;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayQueue() {
        queue = (E[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        size = 0;
    }
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        queue = (E[]) new Object[capacity];
        front = 0;
        size = 0;
    }

    @Override
    public void enqueue(E element) {
        if (size == queue.length) throw new IllegalStateException("Queue full");
        int avail = (front + size) % queue.length;
        queue[avail] = element;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;
        E element = queue[front];
        queue[front] = null; // Help Garbage Collection
        front = (front + 1) % queue.length;
        size--;
        return element;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size + "\t");
        for (int i = 0; i < size; i++) {
            sb.append(queue[(front + i) % queue.length]);
        }
        return sb.toString();
    }
}