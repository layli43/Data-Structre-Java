package org.example.assignment5;

import org.example.assignment5.algorithms.*;
import org.example.assignment5.experiment.Experiment;
import org.example.assignment5.graph.*;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Run performance experiment
        Experiment.run();

        // Graph Demo
        System.out.println("\nGraph Demo:");

        AdjacencyListGraph<String, String> g = new AdjacencyListGraph<>(false);

        Vertex<String> a = g.insertVertex("A");
        Vertex<String> b = g.insertVertex("B");
        Vertex<String> c = g.insertVertex("C");
        Vertex<String> d = g.insertVertex("D");
        Vertex<String> e = g.insertVertex("E");
        Vertex<String> f = g.insertVertex("F");

        g.insertEdge(a, b, "AB", 4);
        g.insertEdge(a, c, "AC", 2);
        g.insertEdge(b, c, "BC", 1);
        g.insertEdge(b, d, "BD", 5);
        g.insertEdge(c, d, "CD", 8);
        g.insertEdge(c, e, "CE", 10);
        g.insertEdge(d, e, "DE", 2);
        g.insertEdge(d, f, "DF", 6);
        g.insertEdge(e, f, "EF", 3);

        System.out.println("Vertices: " + g.numVertices() + "  Edges: " + g.numEdges());

        //DFS
        System.out.println("\nDFS from A:");
        DFS<String, String> dfs = new DFS<>();
        System.out.println("  Visit order: " + dfs.traverse(g, a));

        Map<Vertex<String>, Edge<String, String>> tree = dfs.dfsTree(g, a);
        System.out.println("  Discovery tree edges:");
        for (Map.Entry<Vertex<String>, Edge<String, String>> entry : tree.entrySet())
            System.out.println("    " + entry.getValue().getSource()
                    + " --> " + entry.getValue().getDestination()
                    + "  (weight " + entry.getValue().getWeight() + ")");
        //BFS
        System.out.println("\nBFS from A:");
        BFS<String, String> bfs = new BFS<>();
        System.out.println("  Visit order: " + bfs.traverse(g, a));

        Map<Vertex<String>, Integer> hopDist = bfs.distances(g, a);
        System.out.println("  Hop distances from A:");
        for (Map.Entry<Vertex<String>, Integer> entry : hopDist.entrySet())
            System.out.println("    " + entry.getKey() + " : " + entry.getValue());

        //Dijkstra
        System.out.println("\nDijkstra Shortest Paths from A:");
        Dijkstra<String, String> dijkstra = new Dijkstra<>();
        Dijkstra.ShortestPathResult<String, String> result = dijkstra.shortestPaths(g, a);

        for (Vertex<String> v : g.vertices())
            System.out.printf("  A -> %s : %.1f   path: %s%n",
                    v, result.distanceTo(v), result.pathTo(v));

        System.out.println("\nShortest path A --> F: " + result.pathTo(f));
        System.out.printf("Total distance: %.1f%n", result.distanceTo(f));
    }
}