package org.example.structures;

public interface Heap<K, V> {

    void insert(Entry<K, V> entry);

    Entry<K, V> extractMin();

    Entry<K, V> peek();

    int size();

    boolean isEmpty();
}