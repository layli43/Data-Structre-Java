package org.example.assignment3.heap;

public interface Heap<E> {
    void insert(E element);
    E extractMin();
    E peek();
    int size();
    boolean isEmpty();
}
