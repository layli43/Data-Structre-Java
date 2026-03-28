package org.example.assignment3.priorityqueue;

import java.util.ArrayList;

public class UnsortedPriorityQueue<E extends Comparable<E>> {
    private ArrayList<E> list = new ArrayList<>();

    public void insert(E e) { list.add(e); }

    public E removeMin() {
        int min = 0;
        for (int i = 1; i < list.size(); i++)
            if (list.get(i).compareTo(list.get(min)) < 0)
                min = i;

        return list.remove(min);
    }
}