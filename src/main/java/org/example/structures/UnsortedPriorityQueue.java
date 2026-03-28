package org.example.structures;

import java.util.ArrayList;

public class UnsortedPriorityQueue<K, V> implements PriorityQueue<K, V> {

    private ArrayList<Entry<K, V>> list = new ArrayList<>();
    private Comparator<K> comp;

    public UnsortedPriorityQueue(Comparator<K> comp) {
        this.comp = comp;
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        list.add(entry);
        return entry;
    }

    @Override
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) return null;

        int min = 0;
        for (int i = 1; i < list.size(); i++) {
            if (comp.compare(list.get(i).getKey(), list.get(min).getKey()) < 0) {
                min = i;
            }
        }

        return list.remove(min);
    }

    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) return null;

        int min = 0;
        for (int i = 1; i < list.size(); i++) {
            if (comp.compare(list.get(i).getKey(), list.get(min).getKey()) < 0) {
                min = i;
            }
        }

        return list.get(min);
    }

    @Override public int size() { return list.size(); }
    @Override public boolean isEmpty() { return list.isEmpty(); }
}