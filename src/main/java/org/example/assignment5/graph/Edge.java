package org.example.assignment5.graph;

public class Edge<V, E> {

    private Vertex<V> source;
    private Vertex<V> destination;
    private E element;
    private double weight;

    public Edge(Vertex<V> source, Vertex<V> destination, E element, double weight) {
        this.source      = source;
        this.destination = destination;
        this.element     = element;
        this.weight      = weight;
    }

    public Vertex<V> getSource()      { return source; }
    public Vertex<V> getDestination() { return destination; }
    public E getElement()             { return element; }
    public double getWeight()         { return weight; }

    public Vertex<V> getOpposite(Vertex<V> v) {
        return v == source ? destination : source;
    }

    @Override
    public String toString() {
        return "(" + source + " --[" + weight + "]--> " + destination + ")";
    }
}