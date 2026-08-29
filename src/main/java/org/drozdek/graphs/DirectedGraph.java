package org.drozdek.graphs;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.ZoneId.systemDefault;

import org.drozdek.commons.LoggerService;

/// Directed graph ADT backed by a byte adjacency matrix with arc
/// insertion/removal, depth-first traversal, and adjacency-table rendering.
///
/// **Real-world use case:** Dependency modeling, workflow engines, and
/// network routing where relationships between vertices are directional.
///
/// Complexity Analysis:
/// Time Complexity: O(1) arc lookup, O(V) per adjacency scan, O(V + E) DFS
/// Auxiliary Space: O(V�) for the adjacency matrix
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
public class DirectedGraph implements Digraph {

    protected List<Vertex> vertices;
    protected List<Edge> edges;
    protected byte[][] adjacencyMatrix;

    /// Creates a digraph with n isolated vertices whose names follow
    /// their index ('a' + index) plus a zeroed n-by-n adjacency matrix.
    ///
    /// @param n number of vertices
    public DirectedGraph(int n) {
        this.adjacencyMatrix = new byte[n][n];
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            this.vertices.add(new Vertex(i));
        }
    }

    /// Returns the vertex count derived from the adjacency matrix size.
    ///
    /// @return number of vertices, or zero when no matrix exists yet
    public int cardinality() {
        if (adjacencyMatrix == null) {
            return 0;
        }
        return adjacencyMatrix[0].length;
    }

    /// Checks whether the directed arc from-to exists, validating both
    /// indices first.
    ///
    /// @param from source vertex index
    /// @param to   target vertex index
    /// @return true if the arc is present
    public boolean hasArc(int from, int to) {
        if (adjacencyMatrix == null) {
            return false;
        }
        return from >= 0 && from < cardinality() && to >= 0 && to < cardinality()
                && adjacencyMatrix[from][to] == 1;
    }

    /// Returns the number of stored arcs.
    ///
    /// @return current arc count
    public int countArcs() {
        return this.edges.size();
    }

    /// Creates a directed arc between two vertex indices, updating the
    /// matrix and increasing only the source degree.
    ///
    /// @param node1 source vertex index
    /// @param node2 target vertex index
    /// @return true if the arc was created, false if it already existed,
    ///         the vertices coincide, or an index is invalid
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

    /// Creates a directed arc using letter names, where 'a' maps to
    /// vertex 0.
    ///
    /// @param a1 source vertex name in [a..z]
    /// @param a2 target vertex name in [a..z]
    /// @return true if the arc was created
    public boolean createArc(char a1, char a2) {
        int n1 = a1 - 97;
        int n2 = a2 - 97;
        return createArc(n1, n2);
    }

    /// Removes the directed arc between two vertex indices, clearing the
    /// matrix cell and lowering the source degree.
    ///
    /// @param node1 source vertex index
    /// @param node2 target vertex index
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

    /// Lists the direct successors of a vertex via a row scan of the
    /// adjacency matrix.
    ///
    /// Complexity: O(V).
    ///
    /// @param vertex index of the queried vertex
    /// @return successors of the vertex, or an empty list for an invalid
    ///         index
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

    /// Traverses the whole digraph depth-first and stores the DFS tree
    /// arcs in a fresh result graph, restarting until every vertex is
    /// visited.
    ///
    /// Complexity: O(V²) due to adjacency-matrix scans.
    ///
    /// @return a digraph holding the depth-first search forest
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

    /// Recursive depth-first visit collecting traversed arcs into
    /// newGraph.
    ///
    /// @param v               current vertex index
    /// @param visitedVertices vertices visited so far
    /// @param newGraph        sink graph receiving the DFS arcs
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

    /// Builds the adjacency matrix as a letter-labelled table for the
    /// directed case, where cell `[i][j]` marks an arc from i to j.
    ///
    /// @return the table contents ready for printing
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

    /// Renders the adjacency table of the digraph.
    ///
    /// @return printable representation of the adjacency table
    public String toString() {
        return getAdjacencyTable().toString();
    }
}
