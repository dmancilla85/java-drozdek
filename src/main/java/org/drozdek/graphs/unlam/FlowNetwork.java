package org.drozdek.graphs.unlam;

public class FlowNetwork {
    private final int[][] capacity;
    private final int vertexCount;

    public FlowNetwork(int n) {
        this.vertexCount = n;
        this.capacity = new int[n][n];
    }

    public boolean addEdge(int u, int v, int cap) {
        if (u < 0 || u >= vertexCount || v < 0 || v >= vertexCount || u == v) {
            return false;
        }
        capacity[u][v] = cap;
        return true;
    }

    public int getCapacity(int u, int v) {
        return capacity[u][v];
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int[][] getCapacityMatrix() {
        return capacity;
    }
}
