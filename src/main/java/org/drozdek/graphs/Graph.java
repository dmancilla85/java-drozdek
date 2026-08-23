package org.drozdek.graphs;

import org.drozdek.commons.LoggerService;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.security.SecureRandom;
import java.util.LinkedList;

import static java.time.ZoneId.systemDefault;

/// Undirected graph ADT storing vertices, edges, and a byte adjacency
/// matrix, with BFS/DFS traversal, random generation by connectivity
/// level, and adjacency-table printing.
///
/// **Real-world use case:** Social network analysis, road maps, electrical
/// circuits, and as the base structure for MST and shortest-path
/// algorithms.
///
/// Complexity Analysis:
/// Time Complexity: O(V) neighbour lookup, O(V²) traversals via matrix scans
/// Auxiliary Space: O(V²) for the adjacency matrix
///
/// @see <a href="https://en.wikipedia.org/wiki/Graph_(abstract_data_type)">Graph (abstract data type) (Wikipedia)</a>
public class Graph {

    public List<Vertex> vertices;
    public List<Edge> edges;
    public byte[][] adjacencyMatrix;


    /// Creates an empty graph with no edges and no adjacency matrix.
    public Graph() {
        this.edges = null;
        this.vertices = new ArrayList<>();
        this.adjacencyMatrix = null;
    }

    /// Creates a graph with n isolated vertices whose names follow their
    /// index ('a' + index) plus a zeroed n-by-n adjacency matrix.
    ///
    /// @param n number of vertices
    public Graph(int n) {
        this.adjacencyMatrix = new byte[n][n];
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        for (int i = 0; i < n; i++)
            this.vertices.add(new Vertex(i));
    }

    protected static final SecureRandom RANDOM = new SecureRandom();

    /// Factory producing a random undirected graph where each ordered
    /// pair of vertices gets an edge with probability conexividad%.
    ///
    /// @param n           number of vertices
    /// @param conexividad connectivity percentage in [0, 100]
    /// @return a random graph over n letter-named vertices
    public static Graph createRandom(int n, int conexividad) {
        Graph al = new Graph(n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (RANDOM.nextInt(100) + 1 > 100 - conexividad)
                    al.newEdge(i, j);

        return al;
    }

    static void main() {
        Graph uno = new Graph(9);
        uno.createEdge('a', 'e');
        uno.createEdge('a', 'f');
        uno.createEdge('a', 'i');
        uno.createEdge('a', 'g');
        uno.createEdge('e', 'f');
        uno.createEdge('e', 'i');
        uno.createEdge('e', 'f');
        uno.createEdge('i', 'f');
        uno.createEdge('b', 'g');
        uno.createEdge('c', 'h');
        uno.createEdge('h', 'd');
    }

    /// Appends a vertex to the graph, ignoring null inputs.
    ///
    /// @param v vertex to add
    public void addVertex(Vertex v) {
        if (v != null)
            vertices.add(v);
    }

    /// Runs a breadth-first traversal over the whole graph and stores
    /// the BFS tree edges in a fresh result graph, restarting until every
    /// vertex is visited.
    ///
    /// Complexity: O(V²) due to adjacency-matrix scans.
    ///
    /// @return a graph holding the breadth-first search forest
    public Graph breadthFirstSearch() {

        Clock ini = Clock.tickMillis(systemDefault());

        Graph result = new Graph(cardinality());
        Queue<Vertex> queue = new LinkedList<>();
        List<Vertex> visitedVertices = new ArrayList<>();

        int i = 0;

        while (i < cardinality() && visitedVertices.size() < cardinality()) {
            if (!visitedVertices.contains(vertices.get(i))) {
                bfsFromVertex(i, queue, visitedVertices, result);
            }
            i++;
        }

        Clock end = Clock.tickMillis(systemDefault());
        LoggerService.logInfo("Tiempo algoritmo búsqueda primero en amplitud: "
                + (end.millis() - ini.millis()));

        return result;
    }

    private void bfsFromVertex(int startIndex, Queue<Vertex> queue,
                               List<Vertex> visitedVertices, Graph result) {
        queue.add(vertices.get(startIndex));
        visitedVertices.add(vertices.get(startIndex));

        try {
            while (!queue.isEmpty()) {
                Vertex a = queue.poll();
                int vertex = vertices.indexOf(a);

                for (int j = 0; j < cardinality() && vertex != -1; j++) {
                    if (adjacencyMatrix[vertex][j] == 1
                            && !visitedVertices.contains(vertices.get(j))) {
                        LoggerService.logInfo("vertices Visitados contiene a : "
                                + (char) (j + 97));
                        result.newEdge(vertex, j);
                        queue.add(vertices.get(j));
                        visitedVertices.add(vertices.get(j));
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LoggerService.logError(e.getMessage());
        }
    }

    /// Runs a depth-first traversal over the whole graph and stores the
    /// DFS tree edges in a fresh result graph, restarting until every
    /// vertex is visited.
    ///
    /// Complexity: O(V²) due to adjacency-matrix scans.
    ///
    /// @return a graph holding the depth-first search forest
    public Graph depthFirstSearch() {

        Clock ini = Clock.tickMillis(systemDefault());

        Graph result = new Graph(cardinality());
        List<Vertex> visitedVertices = new ArrayList<>();

        Vertex i = new Vertex(0);

        while (i.key < cardinality() && visitedVertices.size() < cardinality()) {
            if (!visitedVertices.contains(i)) {
                visitedVertices.add(this.vertices.get(i.getKey()));
                deepFirstSearch(i.getKey(), visitedVertices, result);
            }
            i.key++;
        }

        Clock end = Clock.tickMillis(systemDefault());
        LoggerService.logInfo("Tiempo algoritmo búsqueda primero en profundidad: "
                + (end.millis() - ini.millis()));

        return result;
    }

    /// Returns the vertex count derived from the adjacency matrix size.
    ///
    /// @return number of vertices, or zero when no matrix exists yet
    public int cardinality() {

        if (adjacencyMatrix == null)
            return 0;

        return adjacencyMatrix[0].length;
    }

    /// Returns the number of stored edges.
    ///
    /// @return current edge count
    public int countEdges() {
        return this.edges.size();
    }

    /// Creates an undirected edge using letter names, where 'a' maps to
    /// vertex 0.
    ///
    /// @param a1 first vertex name in [a..z]
    /// @param a2 second vertex name in [a..z]
    /// @return true if the edge was created
    public boolean createEdge(char a1, char a2) {
        int n1 = a1 - 97;
        int n2 = a2 - 97;

        return newEdge(n1, n2);
    }

    /// Búsqueda primero en profundidad (Hopcroft - Tarjan)
    public void deepFirstSearch(int v, List<Vertex> visitedVertices, Graph newGraph) {

        int j = 0;
        while (visitedVertices.size() < cardinality() && j < cardinality()) {
            if (adjacencyMatrix[v][j] == 1 && !visitedVertices.contains(this.vertices.get(j))) {
                LoggerService.logInfo("vertices no contiene a : " + (char) (j + 97));
                newGraph.newEdge(v, j);
                visitedVertices.add(this.vertices.get(j));
                deepFirstSearch(j, visitedVertices, newGraph);
            }
            j++;
        }
    }

    /// Lists the neighbours of a vertex via a row scan of the adjacency
    /// matrix.
    ///
    /// Complexity: O(V).
    ///
    /// @param vertex index of the queried vertex
    /// @return adjacent vertices, or an empty list for an invalid index
    public List<Vertex> getAdjacentVertices(int vertex) {
        List<Vertex> ady = new ArrayList<>();

        if (vertex < 0 || vertex >= cardinality())
            return Collections.emptyList();

        for (int i = 0; i < cardinality(); i++)
            if (this.adjacencyMatrix[vertex][i] == 1)
                ady.add(this.vertices.get(i));

        return ady;
    }

    /// Lists the vertices with no edge to the given vertex, excluding
    /// the vertex itself.
    ///
    /// Complexity: O(V).
    ///
    /// @param vertex index of the queried vertex
    /// @return non-adjacent vertices, or an empty list for an invalid
    ///         index
    public List<Vertex> getNonAdjacentVertices(int vertex) {
        List<Vertex> ady = new ArrayList<>();

        if (vertex < 0 || vertex >= cardinality())
            return Collections.emptyList();

        for (int i = 0; i < cardinality(); i++)
            if (this.adjacencyMatrix[vertex][i] == 0 && vertex != i)
                ady.add(this.vertices.get(i));

        return ady;
    }

    /// Creates an undirected edge between two vertex indices, setting
    /// both symmetric matrix cells and raising both degrees.
    ///
    /// @param node1 first vertex index
    /// @param node2 second vertex index
    /// @return true if the edge was created, false if it already
    ///         existed, the vertices coincide, or an index is invalid
    public boolean newEdge(int node1, int node2) {
        try {

            if (adjacencyMatrix == null)
                adjacencyMatrix = new byte[vertices.size()][vertices.size()];

            if (adjacencyMatrix[node1][node2] == 1 || node1 == node2)
                return false;

            edges.add(new Edge(new Vertex(node1), new Vertex(node2)));

            adjacencyMatrix[node1][node2] = 1;
            adjacencyMatrix[node2][node1] = 1;
            vertices.get(node1).increaseDegree();
            vertices.get(node2).increaseDegree();

            return true;

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
            return false;
        }
    }

    /// Compares the present edge count against the undirected maximum
    /// n(n-1)/2.
    ///
    /// @return connectivity ratio formatted as a percentage string
    public String connectivityPercentage() {
        double max = (double) cardinality() * (cardinality() - 1) / 2.0;
        return String.format("Conexividad: %3.2f", edges.size() / max * 100)
                + "%";
    }

    /// Removes the undirected edge between two vertex indices, clearing
    /// both matrix cells and lowering both degrees.
    ///
    /// @param node1 first vertex index
    /// @param node2 second vertex index
    public void removeEdge(int node1, int node2) {

        if (edges.isEmpty())
            return;

        try {
            adjacencyMatrix[node1][node2] = 0;
            adjacencyMatrix[node2][node1] = 0;

            edges.remove(new Edge(new Vertex(node1), new Vertex(node2)));

            vertices.get(node1).decreaseDegree();
            vertices.get(node2).decreaseDegree();

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
        }
    }

    /// Renders the adjacency table of the graph.
    ///
    /// @return printable representation of the adjacency table
    public String toString() {
        return getAdjacencyTable().toString();
    }

    /// Builds the adjacency matrix as a letter-labelled table whose rows
    /// and columns are headed by 'a' + vertex index.
    ///
    /// @return the table contents ready for printing
    public StringBuilder getAdjacencyTable() {
        if (adjacencyMatrix == null)
            return new StringBuilder();

        int n = adjacencyMatrix[0].length;

        StringBuilder table = new StringBuilder();
        table.append("Tabla de Adyacencias\n\\ ");
        for (int i = 0; i < n; i++)
            table.append((char) (i + 97)).append(" ");
        table.append("\n");

        for (int i = 0; i < n; i++) {
            table.append((char) (i + 97)).append(" ");
            for (int j = 0; j < n; j++) {
                table.append(adjacencyMatrix[i][j]);
                if (j < n - 1)
                    table.append(" ");
            }
            table.append("\n");
        }

        return table;
    }

}
