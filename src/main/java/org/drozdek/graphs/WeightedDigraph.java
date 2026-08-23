package org.drozdek.graphs;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneId.systemDefault;

import org.drozdek.commons.LoggerService;

/// Directed graph with integer arc weights kept both as adjacency lists
/// and as a weight table.
///
/// **Real-world use case:** Flight networks with fares, road networks with
/// travel times, and communication networks with latency — typical inputs
/// to Dijkstra's algorithm.
///
/// Complexity Analysis:
/// Time Complexity: O(V) average createArc/removeArc via lists
/// Auxiliary Space: O(V²) weight table plus adjacency lists
///
/// @see <a href="https://en.wikipedia.org/wiki/Directed_graph">Directed graph (Wikipedia)</a>
public class WeightedDigraph implements Digraph {

    protected List<Vertex> v;
    protected List<Vertex>[] adjacencyList;
    public byte[][] adjacencyMatrix;
    public int[][] weightTable;
    protected int totalArcs;

    /// Creates a weighted digraph with n vertices, a zeroed adjacency
    /// matrix, and a zeroed n-by-n weight table.
    ///
    /// @param n number of vertices
    public WeightedDigraph(int n) {
        this.adjacencyList = null;
        this.adjacencyMatrix = new byte[n][n];
        this.weightTable = new int[n][n];
        this.totalArcs = 0;
        this.v = new ArrayList<>();

        for (int i = 0; i < cardinality(); i++)
            v.add(new Vertex(i));

    }

    /// Demo entry point exercising arc creation, table printing, and
    /// DFS.
    ///
    /// @param args unused
    static void main(@SuppressWarnings("unused") String[] args) {
        WeightedDigraph dp = new WeightedDigraph(10);
        dp.createArc('a', 'e', 1);
        dp.createArc('d', 'a', 4);
        dp.createArc('a', 'h', 10);
        dp.createArc('d', 'h', 1);
        dp.createArc('h', 'e', 5);
        dp.createArc('h', 'i', 9);
        dp.createArc('e', 'f', 3);
        dp.createArc('f', 'c', 3);
        dp.createArc('f', 'b', 1);
        dp.createArc('f', 'g', 7);
        dp.createArc('b', 'c', 2);
        dp.createArc('f', 'i', 1);
        dp.createArc('g', 'j', 1);
        dp.createArc('i', 'j', 2);

        dp.printAdjacencyTable();
        dp.printArcWeightTable();

        WeightedDigraph newGraph = dp.depthFirstSearch();
        newGraph.printArcWeightTable();
    }

    /// Traverses the whole weighted digraph depth-first, copying each
    /// traversed arc with its original weight into a result graph and
    /// restarting until every vertex is visited.
    ///
    /// Complexity: O(V²) due to adjacency-matrix scans.
    ///
    /// @return a weighted digraph holding the depth-first search forest
    @SuppressWarnings({"java:S3776", "java:S6541"})
    public WeightedDigraph depthFirstSearch() {

        Clock ini = Clock.tickMillis(systemDefault());

        WeightedDigraph result = new WeightedDigraph(cardinality());
        List<Integer> visitedVertices = new ArrayList<>();

        int i = 0;

        while (i < cardinality() && visitedVertices.size() < cardinality()) {
            if (!visitedVertices.contains(i)) {
                visitedVertices.add(i);
                dfs(i, visitedVertices, result);
            }
            i++;
        }

        Clock end = Clock.tickMillis(systemDefault());
        LoggerService.logInfo("Tiempo algoritmo b\u00fasqueda primero en profundidad: "
                + (end.millis() - ini.millis()));

        return result;
    }

    /// Returns the vertex count derived from the adjacency matrix size.
    ///
    /// @return number of vertices
    public int cardinality() {
        return adjacencyMatrix[0].length;
    }

    /// Checks whether the weighted directed arc from-to exists,
    /// validating both indices first.
    ///
    /// @param from source vertex index
    /// @param to   target vertex index
    /// @return true if the arc is present
    public boolean hasArc(int from, int to) {
        return from >= 0 && from < cardinality() && to >= 0 && to < cardinality()
                && adjacencyMatrix[from][to] == 1;
    }

    /// Creates a weighted directed arc using letter names, where 'a'
    /// maps to vertex 0.
    ///
    /// @param a1     source vertex name in [a..z]
    /// @param a2     target vertex name in [a..z]
    /// @param weight arc weight
    /// @return true if the arc was created
    public boolean createArc(char a1, char a2, int weight) {
        int n1 = a1 - 97;
        int n2 = a2 - 97;

        return createArc(n1, n2, weight);
    }

    /// Creates a weighted directed arc between two vertex indices,
    /// recording it in both the matrix and the weight table.
    ///
    /// @param node1  source vertex index
    /// @param node2  target vertex index
    /// @param weight arc weight
    /// @return true if the arc was created, false if it already existed,
    ///         the vertices coincide, or an index is invalid
    public boolean createArc(int node1, int node2, int weight) {
        try {
            if (adjacencyMatrix[node1][node2] == 1 || node1 == node2)
                return false;

            adjacencyMatrix[node1][node2] = 1;
            weightTable[node1][node2] = weight;

            this.totalArcs++;
            return true;

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
            return false;
        }
    }

    /// B\u00fasqueda primero en profundidad (Hopcroft - Tarjan)
    public void dfs(int v, List<Integer> visitedVertices,
                    WeightedDigraph newGraph) {

        int j = 0;
        while (visitedVertices.size() < cardinality() && j < cardinality()) {
            if (adjacencyMatrix[v][j] == 1 && !visitedVertices.contains(j)) {
                newGraph.createArc(v, j, weightTable[v][j]);
                visitedVertices.add(j);
                dfs(j, visitedVertices, newGraph);
            }
            j++;
        }
    }

    /// Removes the weighted arc between two vertex indices, clearing
    /// its matrix cell and weight-table entry.
    ///
    /// @param node1 source vertex index
    /// @param node2 target vertex index
    /// @return the weight previously stored for the arc, or null when no
    ///         arcs remain or the operation fails
    public Integer removeArc(int node1, int node2) {
        if (totalArcs == 0)
            return null;

        try {
            int weight = weightTable[node1][node2];
            adjacencyMatrix[node1][node2] = 0;
            weightTable[node1][node2] = 0;
            this.totalArcs--;
            return weight;

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
            return null;
        }
    }

    /// Reports adjacency in either direction between two vertices.
    ///
    /// @param i first vertex index
    /// @param j second vertex index
    /// @return true if an arc exists from i to j or from j to i
    public boolean isAdjacent(int i, int j) {
        return adjacencyMatrix[i][j] == 1 ||
                adjacencyMatrix[j][i] == 1;
    }

    /// Prints the letter-labelled adjacency matrix, where cell `[i][j]`
    /// marks an arc from vertex 'a' + i to 'a' + j.
    public void printAdjacencyTable() {
        StringBuilder sb = new StringBuilder();

        int n = this.adjacencyMatrix[0].length;

        sb.append(System.lineSeparator());
        sb.append("\\ ");
        for (int i = 0; i < n; i++)
            sb.append((char) (i + 97)).append(" ");
        sb.append(System.lineSeparator());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j == 0)
                    sb.append((char) (i + j + 97)).append(" ");

                sb.append(adjacencyMatrix[i][j]);
                sb.append(" ");
            }
            sb.append(System.lineSeparator());
        }

        LoggerService.logInfo(sb.toString());
    }

    /// Prints the letter-labelled arc weight table aligned with the
    /// adjacency matrix layout.
    public void printArcWeightTable() {
        StringBuilder sb = new StringBuilder();

        int n = this.weightTable[0].length;

        sb.append(System.lineSeparator());
        sb.append("\\ ");
        for (int i = 0; i < n; i++)
            sb.append((char) (i + 97)).append(" ");
        sb.append(System.lineSeparator());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j == 0)
                    sb.append((char) (i + j + 97)).append(" ");

                sb.append(weightTable[i][j]);
                sb.append(" ");
            }
            sb.append(System.lineSeparator());

        }

        LoggerService.logInfo(sb.toString());
    }

}
