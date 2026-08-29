package org.drozdek.graphs;

/// Capacity-matrix representation of a flow network for maximum-flow
/// computations.
///
/// **Real-world use case:** Input structure for Edmonds-Karp max-flow:
/// traffic routing, logistics capacity planning, bipartite matching, and
/// image segmentation.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for addEdge/getCapacity/getVertexCount
/// Auxiliary Space: O(n�) for the capacity matrix
///
/// Bibliography:
///
/// - Flow network. *Wikipedia*. https://en.wikipedia.org/wiki/Flow_network
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
public class FlowNetwork {
    private final int[][] capacity;
    private final int vertexCount;

    /// Creates a flow network with n vertices and a zeroed capacity
    /// matrix.
    ///
    /// @param n number of vertices
    public FlowNetwork(int n) {
        this.vertexCount = n;
        this.capacity = new int[n][n];
    }

    /// Sets the capacity of the directed edge from u to v, rejecting
    /// self-loops and out-of-range vertices.
    ///
    /// @param u   source vertex index
    /// @param v   target vertex index
    /// @param cap capacity assigned to the edge
    /// @return true if the capacity was recorded, false otherwise
    public boolean addEdge(int u, int v, int cap) {
        if (u < 0 || u >= vertexCount || v < 0 || v >= vertexCount || u == v) {
            return false;
        }
        capacity[u][v] = cap;
        return true;
    }

    /// Reads the capacity of the directed edge between two vertices.
    ///
    /// @param u source vertex index
    /// @param v target vertex index
    /// @return current capacity of the edge
    public int getCapacity(int u, int v) {
        return capacity[u][v];
    }

    /// Returns the number of vertices in the network.
    ///
    /// @return vertex count fixed at construction time
    public int getVertexCount() {
        return vertexCount;
    }

    /// Exposes the raw capacity matrix backing the network.
    ///
    /// @return the internal n-by-n capacity matrix
    public int[][] getCapacityMatrix() {
        return capacity;
    }
}
