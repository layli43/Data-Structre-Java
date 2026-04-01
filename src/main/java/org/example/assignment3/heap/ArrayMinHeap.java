package org.example.assignment3.heap;

import java.util.ArrayList;

public class ArrayMinHeap<E extends Comparable<E>> implements Heap<E> {

    private final ArrayList<E> heap = new ArrayList<>();

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    @Override
    public void insert(E element) {
        heap.add(element);
        upHeap(heap.size() - 1);
    }

    private void upHeap(int i) {
        while (i > 0 && heap.get(i).compareTo(heap.get(parent(i))) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    @Override
    public E extractMin() {
        if (heap.isEmpty()) return null;

        E min = heap.get(0);
        E last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            downHeap(0);
        }

        return min;
    }

    private void downHeap(int i) {
        int smallest = i;
        int left = left(i);
        int right = right(i);

        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0)
            smallest = left;

        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0)
            smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            downHeap(smallest);
        }
    }

    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override public E peek() { return heap.isEmpty() ? null : heap.get(0); }
    @Override public int size() { return heap.size(); }
    @Override public boolean isEmpty() { return heap.isEmpty(); }
}
