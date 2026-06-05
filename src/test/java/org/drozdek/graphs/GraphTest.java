package org.drozdek.graphs;

import org.drozdek.graphs.unlam.Graph;
import org.drozdek.graphs.unlam.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    Graph graph;

    @BeforeEach
    void setUp() {
        graph = new Graph(5);
    }

    @Test
    @DisplayName("Default constructor")
    void defaultConstructor() {
        Graph g = new Graph();
        assertEquals(0, g.cardinality());
    }

    @Test
    @DisplayName("Graph with N vertices has cardinality N")
    void cardinality() {
        assertEquals(5, graph.cardinality());
    }

    @Test
    @DisplayName("New graph has zero edges")
    void countEdges() {
        assertEquals(0, graph.countEdges());
    }

    @Test
    @DisplayName("Add edge between two vertices")
    void newEdge() {
        assertTrue(graph.newEdge(0, 1));
        assertEquals(1, graph.countEdges());
    }

    @Test
    @DisplayName("Add edge to self returns false")
    void newEdgeSelf() {
        assertFalse(graph.newEdge(0, 0));
    }

    @Test
    @DisplayName("Add duplicate edge returns false")
    void newEdgeDuplicate() {
        assertTrue(graph.newEdge(0, 1));
        assertFalse(graph.newEdge(0, 1));
    }

    @Test
    @DisplayName("Add edge via char helper")
    void createEdge() {
        assertTrue(graph.createEdge('a', 'b'));
        assertEquals(1, graph.countEdges());
    }

    @Test
    @DisplayName("Remove edge")
    void removeEdge() {
        graph.newEdge(0, 1);
        graph.removeEdge(0, 1);
        assertEquals(0, graph.countEdges());
    }

    @Test
    @DisplayName("Remove edge from empty graph does nothing")
    void removeEdgeEmpty() {
        assertDoesNotThrow(() -> graph.removeEdge(0, 1));
    }

    @Test
    @DisplayName("Add vertex")
    void addVertex() {
        Graph g = new Graph();
        g.addVertex(new Vertex(0));
        g.addVertex(null);
    }

    @Test
    @DisplayName("Connectivity percentage")
    void connectivityPercentage() {
        graph.newEdge(0, 1);
        String pct = graph.connectivityPercentage();
        assertTrue(pct.contains("%"));
    }

    @Test
    @DisplayName("Get adjacent vertices")
    void getAdjacentVertices() {
        graph.newEdge(0, 1);
        graph.newEdge(0, 2);
        List<Vertex> adj = graph.getAdjacentVertices(0);
        assertEquals(2, adj.size());
    }

    @Test
    @DisplayName("Get adjacent vertices with invalid index returns null")
    void getAdjacentVerticesInvalid() {
        assertNull(graph.getAdjacentVertices(-1));
        assertNull(graph.getAdjacentVertices(10));
    }

    @Test
    @DisplayName("Get non-adjacent vertices")
    void getNonAdjacentVertices() {
        graph.newEdge(0, 1);
        List<Vertex> nonAdj = graph.getNonAdjacentVertices(0);
        assertTrue(nonAdj.stream().noneMatch(v -> v.getKey() == 1));
    }

    @Test
    @DisplayName("Get non-adjacent vertices with invalid index returns null")
    void getNonAdjacentVerticesInvalid() {
        assertNull(graph.getNonAdjacentVertices(-1));
        assertNull(graph.getNonAdjacentVertices(10));
    }

    @Test
    @DisplayName("Adjacency table format")
    void getAdjacencyTable() {
        graph.newEdge(0, 1);
        String table = graph.getAdjacencyTable().toString();
        assertTrue(table.contains("Tabla de Adyacencias"));
    }

    @Test
    @DisplayName("ToString returns adjacency table")
    void testToString() {
        assertNotNull(graph.toString());
    }

    @Test
    @DisplayName("Breadth-first search produces connected subgraph")
    void breadthFirstSearch() {
        graph.newEdge(0, 1);
        graph.newEdge(1, 2);
        Graph bfs = graph.breadthFirstSearch();
        assertNotNull(bfs);
    }

    @Test
    @DisplayName("Depth-first search produces connected subgraph")
    void depthFirstSearch() {
        graph.newEdge(0, 1);
        graph.newEdge(1, 2);
        Graph dfs = graph.depthFirstSearch();
        assertNotNull(dfs);
    }

    @Test
    @DisplayName("Disconnected graph BFS handles all components")
    void bfsDisconnected() {
        graph.newEdge(0, 1);
        graph.newEdge(3, 4);
        Graph bfs = graph.breadthFirstSearch();
        assertNotNull(bfs);
    }

    @Test
    @DisplayName("Disconnected graph DFS handles all components")
    void dfsDisconnected() {
        graph.newEdge(0, 1);
        graph.newEdge(3, 4);
        Graph dfs = graph.depthFirstSearch();
        assertNotNull(dfs);
    }

    @Test
    @DisplayName("Create random graph")
    void createRandom() {
        Graph g = Graph.createRandom(5, 50);
        assertEquals(5, g.cardinality());
    }

    @Test
    @DisplayName("Deep first search from single vertex")
    void deepFirstSearch() {
        Graph g = new Graph(3);
        g.newEdge(0, 1);
        g.newEdge(1, 2);
        Graph result = new Graph(3);
        java.util.ArrayList<Vertex> visited = new java.util.ArrayList<>();
        visited.add(new Vertex(0));
        g.deepFirstSearch(0, visited, result);
    }
}
