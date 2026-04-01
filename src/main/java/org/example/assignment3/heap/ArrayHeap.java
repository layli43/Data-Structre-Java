package org.example.assignment3.heap;

import org.example.structures.Comparator;
import org.example.structures.Entry;
import org.example.structures.Heap;

import java.util.ArrayList;

public class ArrayHeap<K, V> implements Heap<K, V> {

    private final ArrayList<Entry<K, V>> heap;
    private final Comparator<K> comp;

    public ArrayHeap(Comparator<K> comp) {
        this.comp = comp;
        this.heap = new ArrayList<>();
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    @Override
    public void insert(Entry<K, V> entry) {
        heap.add(entry);
        upHeap(heap.size() - 1);
    }

    private void upHeap(int i) {
        while (i > 0 && compare(heap.get(i), heap.get(parent(i))) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    @Override
    public Entry<K, V> extractMin() {
        if (heap.isEmpty()) return null;

        Entry<K, V> min = heap.get(0);
        Entry<K, V> last = heap.remove(heap.size() - 1);

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

        if (left < heap.size() && compare(heap.get(left), heap.get(smallest)) < 0)
            smallest = left;

        if (right < heap.size() && compare(heap.get(right), heap.get(smallest)) < 0)
            smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            downHeap(smallest);
        }
    }

    private void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public Entry<K, V> peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    @Override public int size() { return heap.size(); }
    @Override public boolean isEmpty() { return heap.isEmpty(); }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(Entry<K, V> node : heap) {
            res.append(node.getKey()+" ");
        }
        return res.toString();
    }


}