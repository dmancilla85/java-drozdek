package org.drozdek.graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {
    DirectedGraph graph;

    @BeforeEach
    void setUp() {
        graph = new DirectedGraph(5);
    }

    @Test
    @DisplayName("Graph with N vertices has cardinality N")
    void cardinality() {
        assertEquals(5, graph.cardinality());
    }

    @Test
    @DisplayName("New graph has zero arcs")
    void countArcs() {
        assertEquals(0, graph.countArcs());
    }

    @Test
    @DisplayName("Add arc between two vertices")
    void createArc() {
        assertTrue(graph.createArc(0, 1));
        assertEquals(1, graph.countArcs());
    }

    @Test
    @DisplayName("Add arc to self returns false")
    void createArcSelf() {
        assertFalse(graph.createArc(0, 0));
    }

    @Test
    @DisplayName("Add duplicate arc returns false")
    void createArcDuplicate() {
        assertTrue(graph.createArc(0, 1));
        assertFalse(graph.createArc(0, 1));
    }

    @Test
    @DisplayName("Add reverse arc succeeds (directed)")
    void createArcReverse() {
        assertTrue(graph.createArc(0, 1));
        assertTrue(graph.createArc(1, 0));
        assertEquals(2, graph.countArcs());
    }

    @Test
    @DisplayName("Remove arc")
    void removeArc() {
        graph.createArc(0, 1);
        graph.removeArc(0, 1);
        assertEquals(0, graph.countArcs());
    }

    @Test
    @DisplayName("Get adjacent vertices")
    void getAdjacentVertices() {
        graph.createArc(0, 1);
        graph.createArc(0, 2);
        assertEquals(2, graph.getAdjacentVertices(0).size());
        assertEquals(0, graph.getAdjacentVertices(1).size());
    }

    @Test
    @DisplayName("createArc with chars")
    void createArcChar() {
        assertTrue(graph.createArc('a', 'b'));
        assertTrue(graph.createArc('b', 'c'));
        assertEquals(2, graph.countArcs());
    }

    @Test
    @DisplayName("DFS traversal")
    void depthFirstSearch() {
        graph.createArc(0, 1);
        graph.createArc(1, 2);
        graph.createArc(2, 3);
        DirectedGraph result = graph.depthFirstSearch();
        assertNotNull(result);
        assertEquals(5, result.cardinality());
    }
}
