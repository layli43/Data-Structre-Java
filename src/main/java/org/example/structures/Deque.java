package org.example.structures;

public interface Deque<E> {
    public int size();
    public boolean isEmpty();
    
    public E first();
    public E last();
    public void insertFirst(E element);
    public void insertLast(E element);
    public E removeFirst();
    public E removeLast();
}
