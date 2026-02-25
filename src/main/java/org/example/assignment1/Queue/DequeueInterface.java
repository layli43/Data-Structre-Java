package org.example.assignment1.Queue;

public interface DequeueInterface<T> {
    void addFirst(T item);
    void addLast(T item);
    T removeFirst() throws Exception;
    T removeLast() throws Exception;
    T peekFirst() throws Exception;
    T peekLast() throws Exception;
    boolean isEmpty();
    int size();
}
