package org.drozdek.graphs;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.ZoneId.systemDefault;

import org.drozdek.commons.LoggerService;

public class DirectedGraph implements Digraph {

    protected List<Vertex> vertices;
    protected List<Edge> edges;
    protected byte[][] adjacencyMatrix;

    public DirectedGraph(int n) {
        this.adjacencyMatrix = new byte[n][n];
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            this.vertices.add(new Vertex(i));
        }
    }

    public int cardinality() {
        if (adjacencyMatrix == null) {
            return 0;
        }
        return adjacencyMatrix[0].length;
    }

    public boolean hasArc(int from, int to) {
        if (adjacencyMatrix == null) {
            return false;
        }
        return from >= 0 && from < cardinality() && to >= 0 && to < cardinality()
                && adjacencyMatrix[from][to] == 1;
    }

    public int countArcs() {
        return this.edges.size();
    }

    public boolean createArc(int node1, int node2) {
        try {
            if (adjacencyMatrix[node1][node2] == 1 || node1 == node2) {
                return false;
            }

            adjacencyMatrix[node1][node2] = 1;
            edges.add(new Edge(new Vertex(node1), new Vertex(node2), 0, true));
            vertices.get(node1).increaseDegree();
            return true;

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
            return false;
        }
    }

    public boolean createArc(char a1, char a2) {
        int n1 = a1 - 97;
        int n2 = a2 - 97;
        return createArc(n1, n2);
    }

    public void removeArc(int node1, int node2) {
        if (edges.isEmpty()) {
            return;
        }

        try {
            adjacencyMatrix[node1][node2] = 0;
            edges.remove(new Edge(new Vertex(node1), new Vertex(node2), 0, true));
            vertices.get(node1).decreaseDegree();

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
        }
    }

    public List<Vertex> getAdjacentVertices(int vertex) {
        List<Vertex> ady = new ArrayList<>();

        if (vertex < 0 || vertex >= cardinality()) {
            return Collections.emptyList();
        }

        for (int i = 0; i < cardinality(); i++) {
            if (this.adjacencyMatrix[vertex][i] == 1) {
                ady.add(this.vertices.get(i));
            }
        }

        return ady;
    }

    public DirectedGraph depthFirstSearch() {
        Clock ini = Clock.tickMillis(systemDefault());

        DirectedGraph result = new DirectedGraph(cardinality());
        List<Vertex> visitedVertices = new ArrayList<>();

        int i = 0;

        while (i < cardinality() && visitedVertices.size() < cardinality()) {
            if (!visitedVertices.contains(vertices.get(i))) {
                visitedVertices.add(vertices.get(i));
                dfs(i, visitedVertices, result);
            }
            i++;
        }

        Clock end = Clock.tickMillis(systemDefault());
        LoggerService.logInfo("Tiempo algoritmo b\u00fasqueda primero en profundidad: "
                + (end.millis() - ini.millis()));

        return result;
    }

    protected void dfs(int v, List<Vertex> visitedVertices, DirectedGraph newGraph) {
        int j = 0;
        while (visitedVertices.size() < cardinality() && j < cardinality()) {
            if (adjacencyMatrix[v][j] == 1 && !visitedVertices.contains(this.vertices.get(j))) {
                newGraph.createArc(v, j);
                visitedVertices.add(this.vertices.get(j));
                dfs(j, visitedVertices, newGraph);
            }
            j++;
        }
    }

    public StringBuilder getAdjacencyTable() {
        if (adjacencyMatrix == null) {
            return new StringBuilder();
        }

        int n = adjacencyMatrix[0].length;

        StringBuilder table = new StringBuilder();
        table.append("Tabla de Adyacencias (Dirigido)\n\\ ");
        for (int i = 0; i < n; i++) {
            table.append((char) (i + 97)).append(" ");
        }
        table.append("\n");

        for (int i = 0; i < n; i++) {
            table.append((char) (i + 97)).append(" ");
            for (int j = 0; j < n; j++) {
                table.append(adjacencyMatrix[i][j]);
                if (j < n - 1) {
                    table.append(" ");
                }
            }
            table.append("\n");
        }

        return table;
    }

    public String toString() {
        return getAdjacencyTable().toString();
    }
}
