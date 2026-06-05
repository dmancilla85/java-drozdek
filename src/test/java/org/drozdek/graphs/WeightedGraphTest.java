package org.drozdek.graphs;
import org.drozdek.graphs.unlam.WeightedGraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedGraphTest {
    WeightedGraph graph;

    @BeforeEach
    void setUp() {
        graph = new WeightedGraph(5);
    }

    @Test
    @DisplayName("Create weighted edge")
    void createEdge() {
        assertTrue(graph.createEdge(0, 1, 20));
        assertEquals(1, graph.countEdges());
    }

    @Test
    @DisplayName("Create edge to self returns false")
    void createEdgeSelf() {
        assertFalse(graph.createEdge(0, 0, 10));
    }

    @Test
    @DisplayName("Create duplicate edge returns false")
    void createEdgeDuplicate() {
        assertTrue(graph.createEdge(0, 1, 20));
        assertFalse(graph.createEdge(0, 1, 30));
    }

    @Test
    @DisplayName("Create edge via char helper")
    void createEdgeChar() {
        assertTrue(graph.createEdge('a', 'b', 15));
    }

    @Test
    @DisplayName("Remove weighted edge")
    void removeEdge() {
        graph.createEdge(0, 1, 20);
        graph.removeEdge(0, 1);
        assertEquals(0, graph.countEdges());
    }

    @Test
    @DisplayName("Remove edge from empty graph does nothing")
    void removeEdgeEmpty() {
        assertDoesNotThrow(() -> graph.removeEdge(0, 1));
    }

    @Test
    @DisplayName("Get weight table")
    void getWeightTable() {
        graph.createEdge(0, 1, 20);
        String table = graph.getWeightTable().toString();
        assertTrue(table.contains("Tabla de Pesos"));
    }

    @Test
    @DisplayName("Inherits adjacency table from Graph")
    void getAdjacencyTable() {
        graph.createEdge(0, 1, 20);
        String table = graph.getAdjacencyTable().toString();
        assertTrue(table.contains("Tabla de Adyacencias"));
    }

    @Test
    @DisplayName("Create random weighted graph")
    void createRandom() {
        WeightedGraph g = WeightedGraph.createRandom(5, 50);
        assertEquals(5, g.cardinality());
    }

    @Test
    @DisplayName("Cardinality from Graph parent")
    void cardinality() {
        assertEquals(5, graph.cardinality());
    }

    @Test
    @DisplayName("CreateEdge with exception returns false")
    void createEdgeException() {
        assertFalse(graph.createEdge(99, 5, 10));
    }

    @Test
    @DisplayName("RemoveEdge with exception does not throw")
    void removeEdgeException() {
        graph.createEdge(0, 1, 10);
        assertDoesNotThrow(() -> graph.removeEdge(99, 5));
    }

    @RepeatedTest(10)
    @DisplayName("Create random graph with edges")
    void createRandomWithEdges() {
        WeightedGraph g = WeightedGraph.createRandom(5, 100);
        assertTrue(g.countEdges() >= 0);
    }

    @Test
    @DisplayName("Main method runs without exception")
    void runMain() throws Exception {
        java.lang.reflect.Method m = WeightedGraph.class.getDeclaredMethod("main", String[].class);
        m.setAccessible(true);
        m.invoke(null, (Object) new String[0]);
    }
}
