package org.example.assignment4.implementation;

import org.example.assignment4.structures.AbstractHashMap;
import org.example.assignment4.structures.Entry;

import java.util.ArrayList;

/** ChainHashMap variant that counts comparisons inside buckets. */
public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {
    private UnsortedTableMap<K,V>[] table;

    public long probeCount  = 0;
    public long opCount     = 0;
    public int  resizeCount = 0;

    public ChainHashMap(int cap, int p) { super(cap, p); }
    public ChainHashMap(int cap)        { super(cap); }
    public ChainHashMap()               { super(); }

    public int    capacity()    { return capacity; }
    public double loadFactor()  { return (double) n / capacity; }
    public double avgProbes()   { return opCount == 0 ? 0 : (double) probeCount / opCount; }
    public void   resetCounters() { probeCount = 0; opCount = 0; resizeCount = 0; }

    /** Worst-case bucket length: shows how uneven the distribution is. */
    public int maxChainLength() {
        int max = 0;
        for (int i = 0; i < capacity; i++)
            if (table[i] != null && table[i].size() > max) max = table[i].size();
        return max;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void createTable() {
        if (table != null) resizeCount++;
        table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
    }

    @Override
    protected V bucketGet(int h, K k) {
        opCount++;
        UnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null) { probeCount++; return null; }
        probeCount += bucket.size();               // linear scan inside bucket
        return bucket.get(k);
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        opCount++;
        UnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null) bucket = table[h] = new UnsortedTableMap<>();
        probeCount += bucket.size() + 1;
        int oldSize = bucket.size();
        V answer = bucket.put(k, v);
        n += (bucket.size() - oldSize);
        return answer;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        opCount++;
        UnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null) { probeCount++; return null; }
        probeCount += bucket.size();
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        n -= (oldSize - bucket.size());
        return answer;
    }

    @Override
    public Iterable<Entry<K,V>> entrySet() {
        ArrayList<Entry<K,V>> buf = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            if (table[h] != null)
                for (Entry<K,V> e : table[h].entrySet()) buf.add(e);
        return buf;
    }
}
