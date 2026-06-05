package org.drozdek.graphs;

import org.drozdek.graphs.unlam.WeightedDigraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedDigraphTest {
    WeightedDigraph graph;

    @BeforeEach
    void setUp() {
        graph = new WeightedDigraph(5);
    }

    @Test
    @DisplayName("Cardinality matches constructor")
    void cardinality() {
        assertEquals(5, graph.cardinality());
    }

    @Test
    @DisplayName("Create arc returns true")
    void createArc() {
        assertTrue(graph.createArc(0, 1, 10));
    }

    @Test
    @DisplayName("Create arc to self returns false")
    void createArcSelf() {
        assertFalse(graph.createArc(0, 0, 10));
    }

    @Test
    @DisplayName("Create duplicate arc returns false")
    void createArcDuplicate() {
        assertTrue(graph.createArc(0, 1, 10));
        assertFalse(graph.createArc(0, 1, 20));
    }

    @Test
    @DisplayName("Create arc via char helper")
    void createArcChar() {
        assertTrue(graph.createArc('a', 'b', 5));
    }

    @Test
    @DisplayName("Remove arc")
    void removeArc() {
        graph.createArc(0, 1, 10);
        Integer removed = graph.removeArc(0, 1);
        assertNull(removed);
    }

    @Test
    @DisplayName("Remove arc from empty digraph returns null")
    void removeArcEmpty() {
        assertNull(graph.removeArc(0, 1));
    }

    @Test
    @DisplayName("Is adjacent")
    void isAdjacent() {
        graph.createArc(0, 1, 10);
        assertTrue(graph.isAdjacent(0, 1));
        assertTrue(graph.isAdjacent(1, 0));
    }

    @Test
    @DisplayName("Is not adjacent")
    void isNotAdjacent() {
        assertFalse(graph.isAdjacent(0, 1));
    }

    @Test
    @DisplayName("DFS traversal")
    void depthFirstSearch() {
        graph.createArc('a', 'b', 1);
        graph.createArc('b', 'c', 1);
        graph.createArc('c', 'd', 1);
        WeightedDigraph dfs = graph.depthFirstSearch();
        assertNotNull(dfs);
    }

    @Test
    @DisplayName("Print adjacency table")
    void printAdjacencyTable() {
        assertDoesNotThrow(() -> graph.printAdjacencyTable());
    }

    @Test
    @DisplayName("Print weight table")
    void printArcWeightTable() {
        assertDoesNotThrow(() -> graph.printArcWeightTable());
    }

    @Test
    @DisplayName("DFS from specific vertex")
    void dfs() {
        graph.createArc(0, 1, 1);
        graph.createArc(1, 2, 1);
        WeightedDigraph result = new WeightedDigraph(5);
        java.util.ArrayList<Integer> visited = new java.util.ArrayList<>();
        visited.add(0);
        graph.dfs(0, visited, result);
    }

    @Test
    @DisplayName("CreateArc exception returns false")
    void createArcException() {
        assertFalse(graph.createArc(99, 5, 10));
    }

    @Test
    @DisplayName("RemoveArc with exception")
    void removeArcException() {
        graph.createArc(0, 1, 10);
        assertDoesNotThrow(() -> graph.removeArc(99, 5));
    }

    @Test
    @DisplayName("DFS disconnected vertices")
    void depthFirstSearchDisconnected() {
        graph.createArc(0, 1, 1);
        graph.createArc(3, 4, 1);
        WeightedDigraph result = graph.depthFirstSearch();
        assertNotNull(result);
    }

    @Test
    @DisplayName("DFS empty graph")
    void depthFirstSearchEmpty() {
        WeightedDigraph result = graph.depthFirstSearch();
        assertNotNull(result);
    }

    @Test
    @DisplayName("Main method runs without exception")
    void runMain() throws Exception {
        java.lang.reflect.Method m = WeightedDigraph.class.getDeclaredMethod("main", String[].class);
        m.setAccessible(true);
        m.invoke(null, (Object) new String[0]);
    }
}
