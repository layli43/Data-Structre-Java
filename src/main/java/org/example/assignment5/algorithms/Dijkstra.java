package org.example.assignment5.algorithms;

import org.example.assignment5.graph.*;

import java.util.*;

public class Dijkstra<V, E> {

    // Shortest weighted distances from source. O((V + E) log V)
    public Map<Vertex<V>, Double> shortestDistances(Graph<V, E> g, Vertex<V> source) {
        List<Vertex<V>>         vertices = g.vertices();
        Map<Vertex<V>, Double>  dist     = new HashMap<>();
        Map<Vertex<V>, Integer> idx      = new IdentityHashMap<>();

        for (int i = 0; i < vertices.size(); i++) {
            dist.put(vertices.get(i), Double.MAX_VALUE);
            idx.put(vertices.get(i), i);
        }
        dist.put(source, 0.0);

        // min-heap: [distance, vertexIndex]
        PriorityQueue<double[]> heap =
                new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));
        heap.offer(new double[]{0.0, idx.get(source)});

        while (!heap.isEmpty()) {
            double[]  entry = heap.poll();
            Vertex<V> u     = vertices.get((int) entry[1]);
            if (entry[0] > dist.get(u)) continue;

            for (Edge<V, E> e : g.incidentEdges(u)) {
                Vertex<V> v   = e.getOpposite(u);
                double    alt = dist.get(u) + e.getWeight();
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    heap.offer(new double[]{alt, idx.get(v)});
                }
            }
        }
        return dist;
    }

    // Shortest paths + predecessor tree for full path reconstruction
    public ShortestPathResult<V, E> shortestPaths(Graph<V, E> g, Vertex<V> source) {
        List<Vertex<V>>          vertices = g.vertices();
        Map<Vertex<V>, Double>   dist     = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> prev    = new HashMap<>();
        Map<Vertex<V>, Integer>  idx      = new IdentityHashMap<>();

        for (int i = 0; i < vertices.size(); i++) {
            dist.put(vertices.get(i), Double.MAX_VALUE);
            idx.put(vertices.get(i), i);
        }
        dist.put(source, 0.0);

        PriorityQueue<double[]> heap =
                new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));
        heap.offer(new double[]{0.0, idx.get(source)});

        while (!heap.isEmpty()) {
            double[]  entry = heap.poll();
            Vertex<V> u     = vertices.get((int) entry[1]);
            if (entry[0] > dist.get(u)) continue;

            for (Edge<V, E> e : g.incidentEdges(u)) {
                Vertex<V> v   = e.getOpposite(u);
                double    alt = dist.get(u) + e.getWeight();
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    heap.offer(new double[]{alt, idx.get(v)});
                }
            }
        }
        return new ShortestPathResult<>(dist, prev, source);
    }

    public static class ShortestPathResult<V, E> {
        private final Map<Vertex<V>, Double>    distances;
        private final Map<Vertex<V>, Vertex<V>> predecessors;
        private final Vertex<V>                 source;

        public ShortestPathResult(Map<Vertex<V>, Double> distances,
                                  Map<Vertex<V>, Vertex<V>> predecessors,
                                  Vertex<V> source) {
            this.distances    = distances;
            this.predecessors = predecessors;
            this.source       = source;
        }

        public double distanceTo(Vertex<V> v) { return distances.get(v); }

        public List<Vertex<V>> pathTo(Vertex<V> target) {
            List<Vertex<V>> path = new ArrayList<>();
            for (Vertex<V> cur = target; cur != null; cur = predecessors.get(cur))
                path.add(0, cur);
            if (path.isEmpty() || path.get(0) != source) return Collections.emptyList();
            return path;
        }
    }
}