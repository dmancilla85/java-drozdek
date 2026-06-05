package org.drozdek.graphs.unlam;

import org.drozdek.commons.LoggerService;

import static java.lang.System.out;

/// @author David
public class WeightedGraph extends Graph {

    protected int[][] weightTable;

    /// @param n
    public WeightedGraph(int n) {
        super(n);
        this.weightTable = new int[n][n];
    }

    public static WeightedGraph createRandom(int n, int conexividad) {
        WeightedGraph al = new WeightedGraph(n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if ((int) (Math.random() * 100 + 1) > 100 - conexividad) {
                    al.createEdge(i, j, (int) (Math.random() * 100 + 1));
                }

        return al;
    }

    /// @param args
    static void main(String[] args) {
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

        WeightedGraph e = createRandom(10, 50);
        out.println(e.getAdjacencyTable());
        out.println(e.getWeightTable());
        out.println("Prim");
    }

    /// @param node1
    /// @param node2
    /// @param peso
    /// @return
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
            e.printStackTrace();
            return false;
        }
    }

    /// @param a1
    /// @param a2
    /// @param peso
    /// @return
    public boolean createEdge(char a1, char a2, int weight) {
        int n1 = ((int) a1) - 97;
        int n2 = ((int) a2) - 97;

        return createEdge(n1, n2, weight);
    }

    /// @param node1
    /// @param node2
    /// @return
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

    public StringBuilder getWeightTable() {

        int n = this.weightTable[0].length;

        StringBuilder table = new StringBuilder();
        table.append("Tabla de Pesos\n\\ ");
        for (int i = 0; i < n; i++)
            table.append((char) (i + 97) + " ");
        table.append("\n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j == 0)
                    table.append((char) (i + j + 97) + " ");

                table.append(weightTable[i][j]);
                if (j < n)
                    table.append(" ");
            }
            table.append("\n");

        }

        return table;
    }

}
