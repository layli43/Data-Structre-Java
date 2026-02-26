package org.example.assignment1.Stack;

public class LinkedStack<T> implements StackInterface<T> {
    private class Node {
        T element;
        Node next;

        public Node(T element) {
            this.element = element; // [cite: 172]
        }

        public String toString() {
            return element.toString(); // [cite: 175]
        }
    }

    private Node top;
    private int size;

    public LinkedStack() {
        top = null; // [cite: 181]
        size = 0;   // [cite: 182]
    }

    public void push(T o) {
        Node node = new Node(o); // [cite: 185]
        node.next = top;         // [cite: 186]
        top = node;              // [cite: 187]
        size++;                  // [cite: 188]
    }

    public T pop() {
        if (isEmpty()) return null;
        T element = top.element;
        top = top.next;
        size--;
        return element;
    }

    public T top() {
        return (isEmpty()) ? null : top.element;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    @Override
    public String toString() {
        String output = "";
        Node node = top;
        while (node != null) {
            output = node.element.toString() + " " + output; // [cite: 202]
            node = node.next;
        }
        return size + "\t" + output; // [cite: 208]
    }
}
