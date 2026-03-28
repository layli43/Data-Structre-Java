package org.example.assignment3.priorityqueue;

import java.util.ArrayList;

public class SortedPriorityQueue<E extends Comparable<E>> {
    private final ArrayList<E> list = new ArrayList<>();

    public void insert(E e) {
        int i = 0;
        while (i < list.size() && list.get(i).compareTo(e) < 0) i++;
        list.add(i, e);
    }

    public E removeMin() {
        return list.remove(0);
    }
}