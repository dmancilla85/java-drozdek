package org.drozdek.graphs.unlam;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.MinimumHeap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class Algorithms {
private Algorithms() {  }

    /// Prim-Jarník minimum spanning tree algorithm.
    ///
    /// Builds a priority queue of all edges sorted by weight. Repeatedly
    /// extracts the cheapest edge that connects a visited vertex to an
    /// unvisited one, adding its unvisited endpoint to the spanning tree.
    ///
    /// @param g       an undirected weighted graph
    /// @param inicial starting vertex
    /// @return minimum spanning tree
    public static WeightedGraph primJarnikAlgorithm(WeightedGraph g, int inicial) {

        Calendar ini = Calendar.getInstance();

        WeightedGraph newGraph = new WeightedGraph(g.cardinality());
        MinimumHeap<Edge> edges = new MinimumHeap<>(g.countEdges());
        ArrayList<Vertex> visitedVertices = new ArrayList<>();

        // All edges sorted by weight
        for (Edge element : g.edges)
            edges.insert(element);

        // Add the starting vertex
        visitedVertices.add(g.vertices.get(inicial));

        for (int i = 0; i < g.cardinality(); i++)
            // While not all vertices have been visited
            for (int j = 0; j < edges.size() && visitedVertices.size() < g.cardinality(); j++) {
                Edge aux = edges.extractMin();
                // Pick the edge if exactly one of its endpoints is unvisited
                // and adding it does not exceed N-1 edges (no cycle)
                if ((!visitedVertices.contains(aux.destination) ||
                        !visitedVertices.contains(aux.origin)) && newGraph.countEdges() < g.cardinality() - 1) {
                    // Add the unvisited endpoint
                    if (!visitedVertices.contains(aux.destination))
                        visitedVertices.add(aux.destination);
                    else
                        visitedVertices.add(aux.origin);

                    newGraph.createEdge(aux.origin.key, aux.destination.key, aux.weight);
                }
            }

        Calendar end = Calendar.getInstance();
        LoggerService.logInfo("Tiempo algoritmo de Prim para N = " + g.cardinality() + ": "  //$NON-NLS-1$//$NON-NLS-2$
                + (end.getTimeInMillis() - ini.getTimeInMillis()));

        return newGraph;
    }

    public static WeightedGraph kruskalAlgorithm(WeightedGraph g) {

        WeightedGraph newGraph = new WeightedGraph(g.cardinality());

        MinimumHeap<Edge> queue = new MinimumHeap<>(g.countEdges());
        ArrayList<Vertex> tree = new ArrayList<>();

        // All edges sorted by weight
        for (Edge element : g.edges) {
            queue.insert(element);
        }

        for (int i = 1; i <= g.countEdges() && tree.size() < g.cardinality() - 1 && !queue.isEmpty(); i++) {
            Edge e = queue.extractMin();

            if ((!tree.contains(e.destination) || !tree.contains(e.origin)) &&
                    newGraph.countEdges() < g.cardinality() - 1) {

                // Add the unvisited endpoint
                if (!tree.contains(e.destination))
                    tree.add(e.destination);
                else
                    tree.add(e.origin);

                newGraph.createEdge(e.origin.key, e.destination.key, e.weight);
            }

        }

        return newGraph;
    }

    public static Integer minimum(Integer[] v, List<Vertex> unvisited, WeightedDigraph g) {

        int minimum = Integer.MAX_VALUE;
        int index = 0;

        try {
            for (int i = 0; i < g.cardinality(); i++)
                if ((i == 0 || minimum > v[i]) && unvisited.contains(g.v.get(i))) {
                    minimum = v[i];
                    index = i;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return index;
    }


    /// Dijkstra's shortest path algorithm.
    ///
    /// @param g       weighted directed graph
    /// @param inicial source vertex
    /// @return array of shortest distances from the source to every vertex
    public static Integer[] dijkstraAlgorithm(WeightedDigraph g, int inicial) {

        Integer[] d;
        ArrayList<Vertex> unvisited = new ArrayList<>();
        int v0;

        // Create distance vector D: distance from start node to all others
        d = new Integer[g.cardinality()];

        for (int i = 0; i < g.cardinality(); i++) {
            // Initialize all distances to INF, except the start node (= 0)
            if (i != inicial)
                d[i] = Integer.MAX_VALUE;
            else
                d[i] = 0;

            // Mark all vertices as unvisited
            unvisited.add(g.v.get(i));
        }

        // Process until all vertices are visited
        while (!unvisited.isEmpty()) {

            // Pick unvisited vertex with smallest distance
            v0 = minimum(d, unvisited, g);

            // Mark it as visited
            unvisited.remove(g.v.get(v0));

            // Relax all adjacent (unvisited) edges
            for (int u = 0; u < g.cardinality(); u++) {

                // If a shorter path to u is found through v0, update it
                if (d[u] > d[v0] + g.weightTable[v0][u] && v0 != u
                        && unvisited.contains(g.v.get(u)) && g.weightTable[v0][u] != 0) {
                    d[u] = d[v0] + g.weightTable[v0][u];
                }

            }
        }

        return d;
    }

    /// Floyd-Warshall all-pairs shortest path algorithm.
    ///
    /// Compares all possible paths through the graph for every pair of vertices.
    /// Improves the shortest-path estimate iteratively until optimal.
    ///
    /// @param g weighted graph
    /// @return matrix of shortest distances between every pair of vertices
    public static int[][] floydMarshallAlgorithm(WeightedGraph g) {

        // shortestPath[i][j] is initialized to the direct edge weight (i, j)
        int[][] shortestPath = g.weightTable.clone();

        // Consider each vertex k as an intermediate point
        for (int i = 0; i < g.cardinality(); i++) {
            for (int j = 0; j < g.cardinality(); j++) {
                for (int k = 0; k < g.cardinality(); k++)
                    // If path j -> i -> k is shorter than the current j -> k path, update it
                    if (g.weightTable[j][k] > g.weightTable[j][i] + g.weightTable[i][k])
                        shortestPath[j][k] = g.weightTable[j][i] + g.weightTable[i][k];
            }
        }

        return shortestPath;
    }


    public static boolean cycleDetector() {

        return false;
    }
}
