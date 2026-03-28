package org.example.structures;

public class ChainMap<K,V> implements Map<K,V> {

    public static final int DEFAULT_SIZE = 101;

    private int size;
    private List[] A;

    // ✅ FIX: extend Entry instead of implementing it
    private class ChainMapEntry extends Entry<K,V> {

        public ChainMapEntry(K key, V value) {
            super(key, value);
        }

        @Override
        public String toString() {
            return "{" + getKey() + "," + getValue() + "}";
        }
    }

    public ChainMap() {
        this(DEFAULT_SIZE);
    }

    public ChainMap(int N) {
        A = new List[N];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected int hashFunction(K k) {
        return Math.abs(k.hashCode()) % A.length;
    }

    // ✅ FIX: use getKey()
    private Position find(List list, K k) {
        Position p = list.first();
        while (p != list.last()) {
            if (((Entry<K,V>) p.element()).getKey().equals(k)) return p;
            p = list.next(p);
        }
        if (((Entry<K,V>) p.element()).getKey().equals(k)) return p;
        return null;
    }

    public V put(K k, V v) {
        int h = hashFunction(k);
        V temp = null;

        if (A[h] == null) {
            A[h] = new LinkedList();
            A[h].insertLast(new ChainMapEntry(k, v));
        } else {
            Position P = find(A[h], k);
            if (P == null) {
                A[h].insertLast(new ChainMapEntry(k, v));
            } else {
                try {
                    Entry<K,V> entry =
                            (Entry<K,V>) A[h].replace(P, new ChainMapEntry(k, v));
                    temp = entry.getValue();   // ✅ FIX
                } catch (Exception e) {}
            }
        }
        size++;
        return temp;
    }

    public V get(K k) {
        int h = hashFunction(k);
        if (A[h] == null) return null;

        Position P = find(A[h], k);
        if (P == null) return null;

        return ((Entry<K,V>) P.element()).getValue();  // ✅ FIX
    }

    public V remove(K k) {
        int h = hashFunction(k);
        V temp = null;

        if (A[h] != null) {
            Position P = find(A[h], k);
            if (P != null) {
                try {
                    Entry<K,V> entry = (Entry<K,V>) A[h].remove(P);
                    size--;
                    temp = entry.getValue();  // ✅ FIX
                } catch (Exception e) {}
            }
        }
        return temp;
    }

    public Iterator<Entry<K,V>> entries() {
        List<Entry<K,V>> list = new LinkedList<>();

        for (int i=0; i < A.length; i++) {
            List temp = (List) A[i];
            if (temp != null) {
                Iterator it = new ListIterator(temp);
                while (it.hasNext()) {
                    list.insertLast((Entry<K,V>) it.next());
                }
            }
        }
        return new ListIterator<>(list);
    }

    public Iterator<K> keys() {
        List<K> temp = new LinkedList<>();
        Iterator iterator = entries();

        while (iterator.hasNext()) {
            temp.insertLast(((Entry<K,V>) iterator.next()).getKey()); // ✅ FIX
        }
        return new ListIterator<>(temp);
    }

    public Iterator<V> values() {
        List<V> temp = new LinkedList<>();
        Iterator iterator = entries();

        while (iterator.hasNext()) {
            temp.insertLast(((Entry<K,V>) iterator.next()).getValue()); // ✅ FIX
        }
        return new ListIterator<>(temp);
    }

    @Override
    public String toString() {
        String output = "";

        for (int i=0; i<A.length; i++) {
            output += i + ": ";
            if (A[i] != null) {
                Iterator it = new ListIterator(A[i]);
                while (it.hasNext()) {
                    Entry<K,V> entry = (Entry<K,V>) it.next();
                    output += entry + " ";
                }
            }
            output += "\n";
        }
        return output;
    }
}