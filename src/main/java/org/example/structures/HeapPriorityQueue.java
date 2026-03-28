package org.example.structures;

public class HeapPriorityQueue<K, V> implements PriorityQueue<K, V> {

    private final ArrayHeap<K, V> heap;

    public HeapPriorityQueue(Comparator<K> comp) {
        heap = new ArrayHeap<>(comp);
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        heap.insert(entry);
        return entry;
    }

    @Override
    public Entry<K, V> removeMin() {
        return heap.extractMin();
    }

    @Override
    public Entry<K, V> min() {
        return heap.peek();
    }

    @Override public int size() { return heap.size(); }
    @Override public boolean isEmpty() { return heap.isEmpty(); }
}