package org.example.assignment5.algorithms;

import org.example.assignment5.graph.*;

import java.util.*;

public class DFS<V, E> {

    // Iterative DFS — returns vertices in visit order. O(V + E)
    public List<Vertex<V>> traverse(Graph<V, E> g, Vertex<V> start) {
        Set<Vertex<V>>   visited = new LinkedHashSet<>();
        List<Vertex<V>>  order   = new ArrayList<>();
        Deque<Vertex<V>> stack   = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Vertex<V> u = stack.pop();
            if (visited.contains(u)) continue;
            visited.add(u);
            order.add(u);
            for (Edge<V, E> e : g.incidentEdges(u)) {
                Vertex<V> v = e.getOpposite(u);
                if (!visited.contains(v))
                    stack.push(v);
            }
        }
        return order;
    }

    // Returns the DFS discovery-tree: vertex -> edge used to reach it
    public Map<Vertex<V>, Edge<V, E>> dfsTree(Graph<V, E> g, Vertex<V> start) {
        Map<Vertex<V>, Edge<V, E>> tree = new LinkedHashMap<>();
        Set<Vertex<V>>             visited = new LinkedHashSet<>();
        dfsRecursive(g, start, null, visited, tree);
        return tree;
    }

    private void dfsRecursive(Graph<V, E> g, Vertex<V> u, Edge<V, E> via,
                              Set<Vertex<V>> visited,
                              Map<Vertex<V>, Edge<V, E>> tree) {
        visited.add(u);
        if (via != null) tree.put(u, via);
        for (Edge<V, E> e : g.incidentEdges(u)) {
            Vertex<V> v = e.getOpposite(u);
            if (!visited.contains(v))
                dfsRecursive(g, v, e, visited, tree);
        }
    }
}