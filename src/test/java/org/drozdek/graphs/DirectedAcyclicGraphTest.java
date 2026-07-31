package org.drozdek.graphs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectedAcyclicGraphTest {

    @Test
    @DisplayName("Add arc to DAG succeeds")
    void createArc() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph(3);
        assertTrue(dag.createArc(0, 1));
        assertTrue(dag.createArc(1, 2));
        assertEquals(2, dag.countArcs());
    }

    @Test
    @DisplayName("Add arc that creates a cycle is rejected")
    void createArcCycleRejected() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph(3);
        dag.createArc(0, 1);
        dag.createArc(1, 2);
        assertFalse(dag.createArc(2, 0));
        assertEquals(2, dag.countArcs());
    }

    @Test
    @DisplayName("Transitive cycle detection")
    void createArcTransitiveCycle() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph(4);
        dag.createArc(0, 1);
        dag.createArc(1, 2);
        dag.createArc(2, 3);
        assertFalse(dag.createArc(3, 0));
    }

    @Test
    @DisplayName("Topological sort on linear DAG")
    void topologicalSortLinear() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph(3);
        dag.createArc(0, 1);
        dag.createArc(1, 2);
        List<Integer> order = dag.topologicalSort();
        assertEquals(3, order.size());
        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.indexOf(1) < order.indexOf(2));
    }

    @Test
    @DisplayName("Topological sort on DAG returns empty list when cycle present")
    @SuppressWarnings("unchecked")
    void topologicalSortCycle() throws Exception {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph(3);
        dag.createArc(0, 1);
        dag.createArc(1, 2);

        java.lang.reflect.Field adjField = DirectedGraph.class
                .getDeclaredField("adjacencyMatrix");
        adjField.setAccessible(true);
        byte[][] adj = (byte[][]) adjField.get(dag);
        adj[2][0] = 1;

        java.lang.reflect.Field edgesField = DirectedGraph.class
                .getDeclaredField("edges");
        edgesField.setAccessible(true);
        List<Edge> edges =
                (List<Edge>) edgesField.get(dag);
        edges.add(new Edge(
                new Vertex(2),
                new Vertex(0), 0, true));

        List<Integer> order = dag.topologicalSort();
        assertTrue(order.isEmpty());
    }
}
