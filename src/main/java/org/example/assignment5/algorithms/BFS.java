package org.example.assignment5.algorithms;

import org.example.assignment5.graph.*;

import java.util.*;

public class BFS<V, E> {

    // BFS from start — returns vertices in visit order. O(V + E)
    public List<Vertex<V>> traverse(Graph<V, E> g, Vertex<V> start) {
        Set<Vertex<V>>  visited = new LinkedHashSet<>();
        List<Vertex<V>> order   = new ArrayList<>();
        Queue<Vertex<V>> queue  = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> u = queue.poll();
            order.add(u);
            for (Edge<V, E> e : g.incidentEdges(u)) {
                Vertex<V> v = e.getOpposite(u);
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        return order;
    }

    // BFS hop-count distances from start
    public Map<Vertex<V>, Integer> distances(Graph<V, E> g, Vertex<V> start) {
        Map<Vertex<V>, Integer> dist = new LinkedHashMap<>();
        Queue<Vertex<V>> queue       = new LinkedList<>();
        dist.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> u = queue.poll();
            for (Edge<V, E> e : g.incidentEdges(u)) {
                Vertex<V> v = e.getOpposite(u);
                if (!dist.containsKey(v)) {
                    dist.put(v, dist.get(u) + 1);
                    queue.add(v);
                }
            }
        }
        return dist;
    }
}