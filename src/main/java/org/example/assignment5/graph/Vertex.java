package org.example.assignment5.graph;

public class Vertex<V> {

    private V element;

    public Vertex(V element) {
        this.element = element;
    }

    public V getElement() { return element; }

    @Override
    public String toString() { return element.toString(); }
}