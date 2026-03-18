package org.example.structres;

public class UnsortedListPriorityQueue<K,V> implements PriorityQueue<K,V> {
    private List<Entry<K,V>> list;
    private Comparator comp;

    private static class DefaultComparator implements Comparator {
        public int compare(Object a, Object b) {
            try {
                return ((Comparable) a).compareTo(b);
            } catch (ClassCastException cce) {
                throw new UncomparableException();
            }
        }
    }
    
    private class SimpleEntry implements Entry<K,V> {
        K key;
        V value;
        
        public SimpleEntry(K k, V v) {
            key = k;
            value = v;
        }
        
        public K key() {
            return key;
        }
        
        public V value() {
            return value;
        }
    }

    public UnsortedListPriorityQueue() {
        this(new DefaultComparator());
    }

    public UnsortedListPriorityQueue(Comparator c) {
        comp = c;
        list = new LinkedList<Entry<K,V>>();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    private Position<Entry<K,V>> findMin() throws EmptyPriorityQueueException {
        if (list.isEmpty()) {
            throw new EmptyPriorityQueueException();        // Use a linear search to find the smallest node
        }
        
        Position<Entry<K,V>> minPosn = list.first();
        if (list.size() > 1) {
            K minKey = minPosn.element().key();
            Position<Entry<K,V>> cur = list.first();
            do {
                cur = list.next(cur);
                K temp = cur.element().key();
                if (comp.compare(minKey, temp) > 0) {
                    minKey = temp;
                    minPosn = cur;
                }
            } while (cur != list.last());
        }
        return minPosn;
    }

    public Entry<K,V> insert(K key, V value) {
        Entry<K,V> entry = new SimpleEntry(key, value);
        list.insertLast(entry);
        return entry;
    }

    public Entry<K,V> min() throws EmptyPriorityQueueException {
        return findMin().element();
    }

    public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
        Position<Entry<K,V>> posn = findMin();
        list.remove(posn);
        return posn.element();
    }
}
