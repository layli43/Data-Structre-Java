package org.example.assignment5.graph;

import java.util.List;

public interface Graph<V, E> {
    int numVertices();
    int numEdges();
    List<Vertex<V>> vertices();
    List<Edge<V, E>> edges();
    List<Edge<V, E>> incidentEdges(Vertex<V> v);
    Vertex<V> insertVertex(V element);
    Edge<V, E> insertEdge(Vertex<V> u, Vertex<V> v, E element, double weight);
    boolean isDirected();
}