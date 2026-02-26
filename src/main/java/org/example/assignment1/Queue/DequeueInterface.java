package org.example.assignment1.Queue;

public interface DequeueInterface<T> {
    void insertFirst(T item);
    void insertLast(T item);
    T removeFirst() throws Exception;
    T removeLast() throws Exception;
    T font() throws Exception;
    T rear() throws Exception;
    boolean isEmpty();
    int size();
}
