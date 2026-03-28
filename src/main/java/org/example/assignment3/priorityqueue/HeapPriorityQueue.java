package org.example.assignment3.priorityqueue;

import org.example.assignment3.heap.ArrayMinHeap;

public class HeapPriorityQueue<E extends Comparable<E>> {
    private final ArrayMinHeap<E> heap = new ArrayMinHeap<>();

    public void insert(E e) { heap.insert(e); }
    public E removeMin() { return heap.extractMin(); }
}