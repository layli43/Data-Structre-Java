package org.example.assignment4.implementation;

import org.example.assignment4.structures.AbstractHashMap;
import org.example.assignment4.structures.Entry;

import java.util.ArrayList;

public class HashMap<K,V> extends AbstractHashMap<K,V> {
    private MapEntry<K,V>[] table;
    private final MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);

    public long probeCount = 0;     // total slot inspections
    public long opCount    = 0;     // total ops issued
    public int  resizeCount = 0;    // number of rehashes

    public HashMap(int cap, int p) { super(cap, p); }
    public HashMap(int cap)        { super(cap); }
    public HashMap()               { super(); }

    public int    capacity()    { return capacity; }
    public double loadFactor()  { return (double) n / capacity; }
    public double avgProbes()   { return opCount == 0 ? 0 : (double) probeCount / opCount; }
    public void   resetCounters() { probeCount = 0; opCount = 0; resizeCount = 0; }

    // Use MapEntry array to implement the hashmap
    @Override
    @SuppressWarnings("unchecked")
    protected void createTable() {
        if (table != null) resizeCount++;          // only counts re-creations
        table = (MapEntry<K,V>[]) new MapEntry[capacity];
    }

    private boolean isAvailable(int j) {
        return table[j] == null || table[j] == DEFUNCT;
    }

    private int findSlot(int h, K k) {
        int avail = -1;
        int j = h;
        do {
            probeCount++;                            // every inspection is a probe
            if (isAvailable(j)) {
                if (avail == -1) avail = j;
                if (table[j] == null) break;
            } else if (table[j].getKey().equals(k))
                return j;
            j = (j + 1) % capacity;
        } while (j != h);
        return -(avail + 1);
    }

    @Override
    protected V bucketGet(int h, K k) {
        opCount++;
        int j = findSlot(h, k);
        if (j < 0) return null;
        return table[j].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        opCount++;
        int j = findSlot(h, k);
        if (j >= 0) return table[j].setValue(v);
        table[-(j + 1)] = new MapEntry<>(k, v);
        n++;
        return null;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        opCount++;
        int j = findSlot(h, k);
        if (j < 0) return null;
        V answer = table[j].getValue();
        table[j] = DEFUNCT;
        n--;
        return answer;
    }

    @Override
    public Iterable<Entry<K,V>> entrySet() {
        ArrayList<Entry<K,V>> buf = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            if (!isAvailable(h)) buf.add(table[h]);
        return buf;
    }
}

