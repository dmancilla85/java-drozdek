package org.drozdek.graphs.unlam;

import org.drozdek.commons.LoggerService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.LinkedList;

/// @author David
public class Graph {

    protected ArrayList<Vertex> vertices;
    protected ArrayList<Edge> edges;
    protected byte[][] adjacencyMatrix;


    public Graph() {
        this.edges = null;
        this.vertices = new ArrayList<>();
        this.adjacencyMatrix = null;
    }

    /// @param n
    public Graph(int n) {
        this.adjacencyMatrix = new byte[n][n];
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        for (int i = 0; i < n; i++)
            this.vertices.add(new Vertex(i));
    }

    protected static final Random RANDOM = new Random();

    public static Graph createRandom(int n, int conexividad) {
        Graph al = new Graph(n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (RANDOM.nextInt(100) + 1 > 100 - conexividad)
                    al.newEdge(i, j);

        return al;
    }

    /// @param args
    static void main(String[] args) {
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

    public void addVertex(Vertex v) {
        if (v != null)
            vertices.add(v);
    }

    /// @return
    public Graph breadthFirstSearch() {

        Calendar ini = Calendar.getInstance();

        Graph result = new Graph(cardinality());
        Queue<Vertex> queue = new LinkedList<Vertex>();
        ArrayList<Vertex> visitedVertices = new ArrayList<Vertex>();

        int i = 0;

        while (i < cardinality() && visitedVertices.size() < cardinality()) {
            if (!visitedVertices.contains(vertices.get(i))) {
                bfsFromVertex(i, queue, visitedVertices, result);
            }
            i++;
        }

        Calendar end = Calendar.getInstance();
        LoggerService.logInfo("Tiempo algoritmo b�squeda primero en amplitud: "
                + (end.getTimeInMillis() - ini.getTimeInMillis()));

        return result;
    }

    private void bfsFromVertex(int startIndex, Queue<Vertex> queue,
                               ArrayList<Vertex> visitedVertices, Graph result) {
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
            e.printStackTrace();
        }
    }

    /// @return
    public Graph depthFirstSearch() {

        Calendar ini = Calendar.getInstance();

        Graph result = new Graph(cardinality());
        ArrayList<Vertex> visitedVertices = new ArrayList<Vertex>();

        int i = 0;

        while (i < cardinality() && visitedVertices.size() < cardinality()) {
            if (!visitedVertices.contains(i)) {
                visitedVertices.add(this.vertices.get(i));
                deepFirstSearch(i, visitedVertices, result);
            }
            i++;
        }

        Calendar end = Calendar.getInstance();
        LoggerService.logInfo("Tiempo algoritmo b�squeda primero en profundidad: "
                + (end.getTimeInMillis() - ini.getTimeInMillis()));

        return result;
    }

    public int cardinality() {

        if (adjacencyMatrix == null)
            return 0;

        return adjacencyMatrix[0].length;
    }

    /// @return
    public int countEdges() {
        return this.edges.size();
    }

    /// @param a1
    /// @param a2
    /// @return
    public boolean createEdge(char a1, char a2) {
        int n1 = a1 - 97;
        int n2 = a2 - 97;

        return newEdge(n1, n2);
    }

    /// B\u00fasqueda primero en profundidad (Hopcroft - Tarjan)
    ///
    /// @param v
    /// @param visitedVertices
    /// @param newGraph
    public void deepFirstSearch(int v, ArrayList<Vertex> visitedVertices, Graph newGraph) {

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

    /// @param vertice
    /// @return
    public List<Vertex> getAdjacentVertices(int vertex) {
        ArrayList<Vertex> ady = new ArrayList<>();

        if (vertex < 0 || vertex >= cardinality())
            return null;

        for (int i = 0; i < cardinality(); i++)
            if (this.adjacencyMatrix[vertex][i] == 1)
                ady.add(this.vertices.get(i));

        return ady;
    }

    /// @param vertice
    /// @return
    public List<Vertex> getNonAdjacentVertices(int vertex) {
        ArrayList<Vertex> ady = new ArrayList<Vertex>();

        if (vertex < 0 || vertex >= cardinality())
            return null;

        for (int i = 0; i < cardinality(); i++)
            if (this.adjacencyMatrix[vertex][i] == 0 && vertex != i)
                ady.add(this.vertices.get(i));

        return ady;
    }

    /// @param node1
    /// @param node2
    /// @return
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

    public String connectivityPercentage() {
        double max = cardinality() * (cardinality() - 1) / 2.0;
        return String.format("Conexividad: %3.2f", edges.size() / max * 100)
                + "%";
    }

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

    public String toString() {
        return getAdjacencyTable().toString();
    }

    public StringBuilder getAdjacencyTable() {

        int n = this.adjacencyMatrix[0].length;

        StringBuilder table = new StringBuilder();
        table.append("Tabla de Adyacencias\n\\ ");
        for (int i = 0; i < n; i++)
            table.append((char) (i + 97)).append(" ");
        table.append("\n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j == 0)
                    table.append((char) (i + j + 97)).append(" ");

                table.append(adjacencyMatrix[i][j]);
                if (j < n)
                    table.append(" ");
            }
            table.append("\n");

        }

        return table;
    }

}
