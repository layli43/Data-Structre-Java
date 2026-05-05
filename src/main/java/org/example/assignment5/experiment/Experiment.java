package org.example.assignment5.experiment;

import org.example.assignment5.algorithms.*;
import org.example.assignment5.graph.*;

import java.util.*;

public class Experiment {

    public static void run() {
        int[] sizes = {100, 500, 1000};

        System.out.println("DFS Performance:");
        for (int n : sizes) testDFS(n);

        System.out.println("\nBFS Performance:");
        for (int n : sizes) testBFS(n);

        System.out.println("\nDijkstra Performance:");
        for (int n : sizes) testDijkstra(n);
    }

    private static void testDFS(int n) {
        AdjacencyListGraph<Integer, String> g = buildRandom(n, 0.05);
        Vertex<Integer> start = g.vertices().get(0);
        DFS<Integer, String> dfs = new DFS<>();

        long begin = System.nanoTime();
        dfs.traverse(g, start);
        long end = System.nanoTime();

        System.out.println("  n=" + n + "  E=" + g.numEdges()
                + "  time=" + (end - begin) + " ns");
    }

    private static void testBFS(int n) {
        AdjacencyListGraph<Integer, String> g = buildRandom(n, 0.05);
        Vertex<Integer> start = g.vertices().get(0);
        BFS<Integer, String> bfs = new BFS<>();

        long begin = System.nanoTime();
        bfs.traverse(g, start);
        long end = System.nanoTime();

        System.out.println("  n=" + n + "  E=" + g.numEdges()
                + "  time=" + (end - begin) + " ns");
    }

    private static void testDijkstra(int n) {
        AdjacencyListGraph<Integer, String> g = buildRandom(n, 0.05);
        Vertex<Integer> start = g.vertices().get(0);
        Dijkstra<Integer, String> dijk = new Dijkstra<>();

        long begin = System.nanoTime();
        dijk.shortestDistances(g, start);
        long end = System.nanoTime();

        System.out.println("  n=" + n + "  E=" + g.numEdges()
                + "  time=" + (end - begin) + " ns");
    }

    private static AdjacencyListGraph<Integer, String> buildRandom(int n, double p) {
        AdjacencyListGraph<Integer, String> g = new AdjacencyListGraph<>(false);
        List<Vertex<Integer>> verts = new ArrayList<>();
        for (int i = 0; i < n; i++) verts.add(g.insertVertex(i));

        Random rnd = new Random(42);
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (rnd.nextDouble() < p)
                    g.insertEdge(verts.get(i), verts.get(j), "e",
                            1 + rnd.nextDouble() * 9);
        return g;
    }
}