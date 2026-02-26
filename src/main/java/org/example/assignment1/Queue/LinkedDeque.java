package org.example.assignment1.Queue;

public class LinkedDeque<E> implements DequeueInterface<E>{
    private class Node {
        E element;
        Node next, prev;
        Node(E e) { element = e; }
    }

    private Node front, rear;
    private int size = 0;

    public void insertFirst(E e) {
        Node node = new Node(e);
        if (isEmpty()) front = rear = node;
        else {
            node.next = front;
            front.prev = node;
            front = node;
        }
        size++;
    }

    public void insertLast(E e) {
        Node node = new Node(e);
        if (isEmpty()) front = rear = node;
        else {
            node.prev = rear;
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        E element = front.element;
        front = front.next;
        size--;
        if (isEmpty()) rear = null;
        else front.prev = null;
        return element;
    }

    public E removeLast() {
        if (isEmpty()) return null;
        E element = rear.element;
        rear = rear.prev;
        size--;
        if (isEmpty()) front = null;
        else rear.next = null;
        return element;
    }

    @Override
    public E font() throws Exception {
        return front.element;
    }

    @Override
    public E rear() throws Exception {
        return rear.element;
    }

    public boolean isEmpty() { return size == 0; }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size + "\t");
        Node curr = front;
        while (curr != null) {
            sb.append(curr.element);
            curr = curr.next;
        }
        return sb.toString();
    }
}
