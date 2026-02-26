package org.example.assignment1.Queue;

public class LinkedQueue<E> implements QueueInterface<E>{
    private class Node {
        E element;
        Node next;
        Node(E e) { element = e; }
    }

    private Node front, rear;
    private int size = 0;

    public void enqueue(E e) {
        Node node = new Node(e);
        if (isEmpty()) front = node;
        else rear.next = node;
        rear = node;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) return null;
        E element = front.element;
        front = front.next;
        size--;
        if (isEmpty()) rear = null;
        return element;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size + "\t");
        Node curr = front;
        while (curr != null) {
            sb.append(curr.element+" ");
            curr = curr.next;
        }
        return sb.toString();
    }
}
