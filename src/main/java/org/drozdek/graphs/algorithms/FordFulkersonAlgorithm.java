package org.drozdek.graphs.algorithms;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/// Ford–Fulkerson maximum-flow algorithm using breadth-first augmenting paths
/// (the Edmonds–Karp variant).
///
/// Given a directed capacity matrix, a source, and a sink, the algorithm
/// repeatedly finds a residual path with available capacity and pushes the
/// maximum feasible flow along it, accumulating the total until no augmenting
/// path remains. Using BFS guarantees the number of augmentations is bounded
/// by O(V * E).
///
/// **Real-world use case:** Network flow problems such as bandwidth
/// maximisation, image segmentation, and bipartite matching via reduction.
///
/// Complexity Analysis:
/// Time Complexity: O(V * E^2) with the BFS (Edmonds–Karp) variant
/// Auxiliary Space: O(V^2) for the residual/flow matrices
///
/// Bibliography:
///
/// - Ford–Fulkerson algorithm. *Wikipedia*. https://en.wikipedia.org/wiki/Ford%E2%80%93Fulkerson_algorithm
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
public final class FordFulkersonAlgorithm {

    private FordFulkersonAlgorithm() {
        // do nothing
    }

    /// Computes the value and final flow of a maximum flow.
    ///
    /// @param capacity directed capacity matrix of size n x n
    /// @param source   index of the source vertex
    /// @param sink     index of the sink vertex
    /// @return a {@code MaxFlow} holding the total flow value and the flow matrix
    public static MaxFlow compute(int[][] capacity, int source, int sink) {
        int n = capacity.length;
        int[][] flow = new int[n][n];
        int[][] residual = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(capacity[i], 0, residual[i], 0, n);
        }
        int total = 0;
        int[] parent = new int[n];
        while (bfs(residual, source, sink, parent)) {
            int push = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                push = Math.min(push, residual[parent[v]][v]);
            }
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= push;
                residual[v][u] += push;
                flow[u][v] += push;
                flow[v][u] -= push;
            }
            total += push;
        }
        return new MaxFlow(total, flow);
    }

    private static boolean bfs(int[][] residual, int source, int sink, int[] parent) {
        int n = residual.length;
        Arrays.fill(parent, -1);
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        parent[source] = source;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < n; v++) {
                if (parent[v] == -1 && residual[u][v] > 0) {
                    parent[v] = u;
                    if (v == sink) {
                        return true;
                    }
                    queue.add(v);
                }
            }
        }
        return false;
    }

    /// Result of a Ford–Fulkerson computation.
    public static final class MaxFlow {
        private final int value;
        private final int[][] flow;

        private MaxFlow(int value, int[][] flow) {
            this.value = value;
            this.flow = flow;
        }

        /// Returns the total maximum-flow value from source to sink.
        ///
        /// @return flow value
        public int getValue() {
            return value;
        }

        /// Returns the flow allocated to each directed edge.
        ///
        /// @return n x n flow matrix
        public int[][] getFlow() {
            return flow;
        }
    }
}
