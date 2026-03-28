package org.example.structures;

import java.util.ArrayList;

public class SortedPriorityQueue<K, V> implements PriorityQueue<K, V> {

    private final ArrayList<Entry<K, V>> list = new ArrayList<>();
    private final Comparator<K> comp;

    public SortedPriorityQueue(Comparator<K> comp) {
        this.comp = comp;
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);

        int i = 0;
        while (i < list.size() && comp.compare(list.get(i).getKey(), key) < 0)
            i++;

        list.add(i, entry);
        return entry;
    }

    @Override
    public Entry<K, V> removeMin() {
        return list.isEmpty() ? null : list.remove(0);
    }

    @Override
    public Entry<K, V> min() {
        return list.isEmpty() ? null : list.get(0);
    }

    @Override public int size() { return list.size(); }
    @Override public boolean isEmpty() { return list.isEmpty(); }
}