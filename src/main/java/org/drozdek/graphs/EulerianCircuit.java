package org.drozdek.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/// Finds an Eulerian circuit in an undirected graph using Hierholzer's
/// algorithm.
///
/// An Eulerian circuit is a closed walk that traverses every edge exactly
/// once and returns to its start. For an undirected graph such a circuit
/// exists if and only if the graph is connected and every vertex has even
/// degree. The algorithm follows unused edges with a stack of pending
/// vertices, splicing sub-circuits together as it backtracks.
///
/// **Real-world use case:** Route planning that must visit every street
/// exactly once (e.g. snow-ploughing), and the Chinese postman problem.
///
/// Complexity Analysis:
/// Time Complexity: O(V * E) using an adjacency-matrix traversal
/// Auxiliary Space: O(V + E)
///
/// Bibliography:
///
/// - Eulerian path. *Wikipedia*. https://en.wikipedia.org/wiki/Eulerian_path
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
public final class EulerianCircuit {

    private EulerianCircuit() {
        // do nothing
    }

    /// Computes an Eulerian circuit, or returns an empty list when the graph
    /// has no such circuit.
    ///
    /// @param adjacency symmetric adjacency matrix of size n x n
    /// @return a list of vertex indices forming the circuit, or an empty list
    public static List<Integer> findCircuit(boolean[][] adjacency) {
        int n = adjacency.length;
        if (n == 0 || !hasEvenDegrees(adjacency)) {
            return new ArrayList<>();
        }
        int[][] remaining = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                remaining[i][j] = adjacency[i][j] ? 1 : 0;
            }
        }
        List<Integer> circuit = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        int start = 0;
        stack.push(start);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            int w = nextEdge(remaining, v, n);
            if (w >= 0) {
                remaining[v][w]--;
                remaining[w][v]--;
                stack.push(w);
            } else {
                circuit.add(0, stack.pop());
            }
        }
        if (circuit.size() != edgeCount(adjacency) + 1) {
            return new ArrayList<>();
        }
        return circuit;
    }

    private static boolean hasEvenDegrees(boolean[][] adjacency) {
        for (int i = 0; i < adjacency.length; i++) {
            int degree = 0;
            for (int j = 0; j < adjacency.length; j++) {
                if (adjacency[i][j]) {
                    degree++;
                }
            }
            if (degree % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    private static int nextEdge(int[][] remaining, int v, int n) {
        for (int j = 0; j < n; j++) {
            if (remaining[v][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    private static int edgeCount(boolean[][] adjacency) {
        int count = 0;
        for (int i = 0; i < adjacency.length; i++) {
            for (int j = i + 1; j < adjacency.length; j++) {
                if (adjacency[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
