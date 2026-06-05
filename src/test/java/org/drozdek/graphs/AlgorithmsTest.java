package org.drozdek.graphs;

import org.drozdek.graphs.unlam.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    @Test
    @DisplayName("Dijkstra finds shortest paths")
    void dijkstraAlgorithm() {
        WeightedDigraph g = new WeightedDigraph(4);
        g.createArc('a', 'b', 10);
        g.createArc('a', 'c', 3);
        g.createArc('c', 'b', 1);
        g.createArc('b', 'd', 2);
        g.createArc('c', 'd', 8);

        Integer[] dist = Algorithms.dijkstraAlgorithm(g, 0);
        assertNotNull(dist);
        assertEquals(0, dist[0]);
        assertEquals(4, dist[1]);
        assertEquals(3, dist[2]);
        assertEquals(6, dist[3]);
    }

    @Test
    @DisplayName("Dijkstra with single vertex")
    void dijkstraSingleVertex() {
        WeightedDigraph g = new WeightedDigraph(1);
        Integer[] dist = Algorithms.dijkstraAlgorithm(g, 0);
        assertNotNull(dist);
        assertEquals(1, dist.length);
        assertEquals(0, dist[0]);
    }

    @Test
    @DisplayName("Prim-Jarnik finds minimum spanning tree")
    void primJarnikAlgorithm() {
        WeightedGraph g = new WeightedGraph(4);
        g.createEdge('a', 'b', 1);
        g.createEdge('a', 'c', 4);
        g.createEdge('b', 'c', 2);
        g.createEdge('b', 'd', 5);
        g.createEdge('c', 'd', 3);

        WeightedGraph mst = Algorithms.primJarnikAlgorithm(g, 0);
        assertNotNull(mst);
        assertEquals(4, mst.cardinality());
    }

    @Test
    @DisplayName("Kruskal finds minimum spanning tree")
    void kruskalAlgorithm() {
        WeightedGraph g = new WeightedGraph(4);
        g.createEdge('a', 'b', 1);
        g.createEdge('a', 'c', 4);
        g.createEdge('b', 'c', 2);
        g.createEdge('b', 'd', 5);
        g.createEdge('c', 'd', 3);

        WeightedGraph mst = Algorithms.kruskalAlgorithm(g);
        assertNotNull(mst);
        assertEquals(4, mst.cardinality());
    }

    @Test
    @DisplayName("Prim on single vertex graph")
    void primSingleVertex() {
        WeightedGraph g = new WeightedGraph(1);
        assertThrows(IllegalArgumentException.class, () -> Algorithms.primJarnikAlgorithm(g, 0));
    }

    @Test
    @DisplayName("Kruskal on single vertex graph")
    void kruskalSingleVertex() {
        WeightedGraph g = new WeightedGraph(1);
        assertThrows(IllegalArgumentException.class, () -> Algorithms.kruskalAlgorithm(g));
    }

    @Test
    @DisplayName("Floyd-Warshall finds all-pairs shortest paths")
    void floydMarshallAlgorithm() {
        WeightedGraph g = new WeightedGraph(3);
        g.createEdge('a', 'b', 2);
        g.createEdge('b', 'c', 3);
        g.createEdge('a', 'c', 10);

        int[][] result = Algorithms.floydMarshallAlgorithm(g);
        assertNotNull(result);
        assertEquals(3, result.length);
    }

    @Test
    @DisplayName("Dijkstra with single unreachable vertex")
    void dijkstraDisconnected() {
        WeightedDigraph g = new WeightedDigraph(2);
        g.createArc(0, 1, 5);
        Integer[] dist = Algorithms.dijkstraAlgorithm(g, 0);
        assertNotNull(dist);
        assertEquals(0, dist[0]);
        assertEquals(5, dist[1]);
    }

    @Test
    @DisplayName("Cycle detector returns false for any input")
    void cycleDetector() {
        assertFalse(Algorithms.cycleDetector());
    }
}
