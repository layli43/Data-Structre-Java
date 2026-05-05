package org.example.assignment5;

import org.example.assignment5.algorithms.*;
import org.example.assignment5.graph.*;

import java.util.List;
import java.util.Map;

public class GraphTests {

    public static void main(String[] args) {

        System.out.println("Test 1: Vertex and Edge Count");
        testVertexEdgeCount();

        System.out.println("\nTest 2: DFS Traversal");
        testDFS();

        System.out.println("\nTest 3: BFS Traversal");
        testBFS();

        System.out.println("\nTest 4: BFS Hop Distances");
        testBFSDistances();

        System.out.println("\nTest 5: Dijkstra Shortest Distance");
        testDijkstraDistance();

        System.out.println("\nTest 6: Dijkstra Path Reconstruction");
        testDijkstraPath();

        System.out.println("\nTest 7: Directed Graph");
        testDirectedGraph();

        System.out.println("\nTest 8: Disconnected Graph");
        testDisconnectedGraph();

        System.out.println("\nTest 9: Single Node Graph");
        testSingleNode();
    }

    // 1. Vertex and Edge Count
    public static void testVertexEdgeCount() {
        AdjacencyListGraph<String, String> g = buildSample();
        System.out.println("numVertices: " + g.numVertices());
        System.out.println("numEdges: "    + g.numEdges());
    }

    // 2. DFS traversal visits all nodes
    public static void testDFS() {
        AdjacencyListGraph<String, String> g = buildSample();
        DFS<String, String> dfs = new DFS<>();
        List<Vertex<String>> visited = dfs.traverse(g, g.vertices().get(0));
        System.out.println("DFS visited: " + visited);
        System.out.println("All visited: " + (visited.size() == g.numVertices()));
    }

    // 3. BFS traversal visits all nodes
    public static void testBFS() {
        AdjacencyListGraph<String, String> g = buildSample();
        BFS<String, String> bfs = new BFS<>();
        List<Vertex<String>> visited = bfs.traverse(g, g.vertices().get(0));
        System.out.println("BFS visited: " + visited);
        System.out.println("All visited: " + (visited.size() == g.numVertices()));
    }

    // 4. BFS hop distances
    public static void testBFSDistances() {
        AdjacencyListGraph<String, String> g = buildSample();
        BFS<String, String> bfs = new BFS<>();
        Map<Vertex<String>, Integer> dist = bfs.distances(g, g.vertices().get(0));
        System.out.println("Hop distances from A: " + dist);
    }

    // 5. Dijkstra correct shortest distance
    public static void testDijkstraDistance() {
        AdjacencyListGraph<String, String> g = buildSample();
        Vertex<String> a = g.vertices().get(0);   // A
        Vertex<String> d = g.vertices().get(3);   // D

        Dijkstra<String, String> dijk = new Dijkstra<>();
        Dijkstra.ShortestPathResult<String, String> r = dijk.shortestPaths(g, a);

        System.out.println("Distance A->D: " + r.distanceTo(d));
        System.out.println("Expected:      8.0");
    }

    // 6. Path reconstruction
    public static void testDijkstraPath() {
        AdjacencyListGraph<String, String> g = buildSample();
        Vertex<String> a = g.vertices().get(0);   // A
        Vertex<String> f = g.vertices().get(5);   // F

        Dijkstra<String, String> dijk = new Dijkstra<>();
        Dijkstra.ShortestPathResult<String, String> r = dijk.shortestPaths(g, a);

        System.out.println("Path A->F:    " + r.pathTo(f));
        System.out.println("Distance A->F: " + r.distanceTo(f));
    }

    // 7. Directed graph — incident edges are one-way
    public static void testDirectedGraph() {
        AdjacencyListGraph<String, String> g = new AdjacencyListGraph<>(true);
        Vertex<String> x = g.insertVertex("X");
        Vertex<String> y = g.insertVertex("Y");
        g.insertEdge(x, y, "XY", 5);

        System.out.println("Incident edges of X: " + g.incidentEdges(x).size());
        System.out.println("Incident edges of Y: " + g.incidentEdges(y).size());
    }

    // 8. Disconnected graph — unreachable vertex has MAX distance
    public static void testDisconnectedGraph() {
        AdjacencyListGraph<String, String> g = new AdjacencyListGraph<>(false);
        Vertex<String> a = g.insertVertex("A");
        Vertex<String> b = g.insertVertex("B");
        Vertex<String> c = g.insertVertex("C");   // isolated
        g.insertEdge(a, b, "AB", 1);

        Dijkstra<String, String> dijk = new Dijkstra<>();
        Dijkstra.ShortestPathResult<String, String> r = dijk.shortestPaths(g, a);

        System.out.println("Distance A->C (disconnected): " + r.distanceTo(c));
        System.out.println("Is unreachable: " + (r.distanceTo(c) == Double.MAX_VALUE));
    }

    // 9. Single node
    public static void testSingleNode() {
        AdjacencyListGraph<String, String> g = new AdjacencyListGraph<>(false);
        Vertex<String> only = g.insertVertex("Z");

        DFS<String, String> dfs = new DFS<>();
        List<Vertex<String>> visited = dfs.traverse(g, only);

        System.out.println("Single node DFS: " + visited);
        System.out.println("Size: " + visited.size());
    }

    private static AdjacencyListGraph<String, String> buildSample() {
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
        return g;
    }
}