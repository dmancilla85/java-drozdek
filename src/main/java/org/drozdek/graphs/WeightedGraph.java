package org.drozdek.graphs;

import org.drozdek.commons.LoggerService;

/// Undirected graph with an integer edge-weight table layered over the
/// base Graph structure.
///
/// **Real-world use case:** Road networks with distances, utility grids
/// with connection costs, and inputs to Prim-Jarnik and Kruskal minimum
/// spanning tree algorithms.
///
/// Complexity Analysis:
/// Time Complexity: O(1) edge weight lookup, O(n²) random generation
/// Auxiliary Space: O(n²) for the weight table
///
public class WeightedGraph extends Graph {

    public int[][] weightTable;

    /// Constructor allocating an n-by-n weight table.
    ///
    /// @param n number of vertices
    public WeightedGraph(int n) {
        super(n);
        this.weightTable = new int[n][n];
    }

    /// Factory producing a random weighted graph where each possible edge
    /// appears with probability conexividad% and weights are drawn from
    /// 1..100.
    ///
    /// @param n            number of vertices
    /// @param conexividad  connectivity percentage in [0, 100]
    /// @return a randomly weighted undirected graph
    public static WeightedGraph createRandomWeighted(int n, int conexividad) {
        WeightedGraph al = new WeightedGraph(n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (RANDOM.nextInt(100) + 1 > 100 - conexividad) {
                    al.createEdge(i, j, RANDOM.nextInt(100) + 1);
                }

        return al;
    }

    /// Demo entry point building a sample weighted graph and printing its
    /// adjacency and weight tables.
    ///
    /// @param args unused
    static void main(@SuppressWarnings("unused") String[] args) {
        WeightedGraph g = new WeightedGraph(6);
        g.createEdge('a', 'b', 20);
        g.createEdge('a', 'd', 40);
        g.createEdge('a', 'c', 100);
        g.createEdge('b', 'd', 120);
        g.createEdge('b', 'e', 200);
        g.createEdge('b', 'c', 30);
        g.createEdge('c', 'e', 40);
        g.createEdge('d', 'e', 60);
        g.createEdge('d', 'f', 60);
        g.createEdge('e', 'f', 30);

        WeightedGraph e = createRandomWeighted(10, 50);
        LoggerService.logInfo(e.getAdjacencyTable().toString());
        LoggerService.logInfo(e.getWeightTable().toString());
        LoggerService.logInfo("Prim");
    }

    /// Creates a weighted undirected edge between two vertex indices,
    /// updating the adjacency matrix, weight table, and degree counters.
    ///
    /// @param node1  first vertex index
    /// @param node2  second vertex index
    /// @param weight edge weight
    /// @return true if the edge was created, false if it already existed,
    ///         the vertices coincide, or indices are out of range
    public boolean createEdge(int node1, int node2, int weight) {
        try {
            if (adjacencyMatrix[node1][node2] == 1 || node1 == node2)
                return false;

            adjacencyMatrix[node1][node2] = 1;
            weightTable[node1][node2] = weight;
            adjacencyMatrix[node2][node1] = 1;
            weightTable[node2][node1] = weight;
            edges.add(new Edge(new Vertex(node1), new Vertex(node2), weight));
            vertices.get(node1).increaseDegree();
            vertices.get(node2).increaseDegree();
            return true;

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
            return false;
        }
    }

    /// Creates a weighted undirected edge using letter names, where 'a'
    /// maps to vertex 0.
    ///
    /// @param a1     first vertex name in [a..z]
    /// @param a2     second vertex name in [a..z]
    /// @param weight edge weight
    /// @return true if the edge was created
    public boolean createEdge(char a1, char a2, int weight) {
        int n1 = a1 - 97;
        int n2 = a2 - 97;

        return createEdge(n1, n2, weight);
    }

    /// Removes the weighted edge between two vertex indices, clearing both
    /// matrix cells and adjusting degrees.
    ///
    /// @param node1 first vertex index
    /// @param node2 second vertex index
    @Override
    public void removeEdge(int node1, int node2) {


        if (this.edges.isEmpty())
            return;

        try {
            adjacencyMatrix[node1][node2] = 0;
            weightTable[node1][node2] = 0;
            adjacencyMatrix[node2][node1] = 0;
            weightTable[node2][node1] = 0;
            edges.remove(new Edge(new Vertex(node1), new Vertex(node2)));
            vertices.get(node1).decreaseDegree();
            vertices.get(node2).decreaseDegree();

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
        }
    }

    /// Renders the weight table with letter-labelled rows and columns.
    ///
    /// @return printable representation of the weight table
    public StringBuilder getWeightTable() {

        int n = this.weightTable[0].length;

        StringBuilder table = new StringBuilder();
        table.append("Tabla de Pesos\n\\ ");
        for (int i = 0; i < n; i++)
            table.append((char) (i + 97)).append(" ");
        table.append("\n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j == 0)
                    table.append((char) (i + j + 97)).append(" ");

                table.append(weightTable[i][j]);
                table.append(" ");
            }
            table.append("\n");

        }

        return table;
    }

}
