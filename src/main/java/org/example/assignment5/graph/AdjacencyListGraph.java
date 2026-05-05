package org.example.assignment5.graph;

import java.util.*;

public class AdjacencyListGraph<V, E> implements Graph<V, E> {

    private final boolean directed;
    private final Map<Vertex<V>, List<Edge<V, E>>> adjacency = new LinkedHashMap<>();

    public AdjacencyListGraph(boolean directed) {
        this.directed = directed;
    }

    @Override
    public boolean isDirected() { return directed; }

    @Override
    public int numVertices() { return adjacency.size(); }

    @Override
    public int numEdges() {
        int total = adjacency.values().stream().mapToInt(List::size).sum();
        return directed ? total : total / 2;
    }

    @Override
    public List<Vertex<V>> vertices() { return new ArrayList<>(adjacency.keySet()); }

    @Override
    public List<Edge<V, E>> edges() {
        Set<Edge<V, E>> seen = new LinkedHashSet<>();
        adjacency.values().forEach(seen::addAll);
        return new ArrayList<>(seen);
    }

    @Override
    public List<Edge<V, E>> incidentEdges(Vertex<V> v) {
        return adjacency.getOrDefault(v, Collections.emptyList());
    }

    @Override
    public Vertex<V> insertVertex(V element) {
        Vertex<V> v = new Vertex<>(element);
        adjacency.put(v, new ArrayList<>());
        return v;
    }

    @Override
    public Edge<V, E> insertEdge(Vertex<V> u, Vertex<V> v, E element, double weight) {
        Edge<V, E> e = new Edge<>(u, v, element, weight);
        adjacency.get(u).add(e);
        if (!directed) adjacency.get(v).add(e);
        return e;
    }
}