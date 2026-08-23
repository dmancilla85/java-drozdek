package org.drozdek.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/// Directed graph that rejects any arc creating a cycle, guaranteeing
/// acyclicity at all times.
///
/// Arc creation first checks reachability, so an arc is only added when no
/// path already exists from target back to source. Supports topological
/// ordering of its vertices.
///
/// **Real-world use case:** Build systems and task scheduling with
/// dependencies, spreadsheet formula evaluation, course prerequisite
/// planning, and version-control commit histories.
///
/// Complexity Analysis:
/// Time Complexity: O(V + E) cycle check per createArc, O(V + E) topological sort
/// Auxiliary Space: O(V)
///
/// @see <a href="https://en.wikipedia.org/wiki/Directed_acyclic_graph">Directed acyclic graph (Wikipedia)</a>
public class DirectedAcyclicGraph extends DirectedGraph {

    /// Creates an acyclic digraph with n isolated vertices named after
    /// their index ('a' + index).
    ///
    /// @param n number of vertices
    public DirectedAcyclicGraph(int n) {
        super(n);
    }

    private boolean isValidVertex(int v) {
        return v >= 0 && v < cardinality();
    }

    @Override
    public boolean createArc(int node1, int node2) {
        if (!isValidVertex(node1) || !isValidVertex(node2)
                || adjacencyMatrix[node1][node2] == 1 || node1 == node2) {
            return false;
        }

        if (canReach(node2, node1)) {
            return false;
        }

        adjacencyMatrix[node1][node2] = 1;
        edges.add(new Edge(new Vertex(node1), new Vertex(node2), 0, true));
        vertices.get(node1).increaseDegree();
        return true;
    }

    private boolean canReach(int start, int target) {
        if (start == target) {
            return true;
        }
        if (!isValidVertex(start) || !isValidVertex(target)) {
            return false;
        }

        int n = cardinality();
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (v == target) {
                return true;
            }
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            for (int u = 0; u < n; u++) {
                if (adjacencyMatrix[v][u] == 1 && !visited[u]) {
                    stack.push(u);
                }
            }
        }

        return false;
    }

    /// Computes a topological ordering of the vertices using Kahn's
    /// in-degree algorithm.
    ///
    /// Complexity: O(V²) via matrix row scans.
    ///
    /// @return vertex indices in topological order, or an empty list if
    ///         a cycle prevents a complete ordering
    public List<Integer> topologicalSort() {
        int n = cardinality();
        int[] inDegree = new int[n];

        for (Edge e : edges) {
            inDegree[e.destination.key]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int v = queue.poll();
            result.add(v);

            for (int u = 0; u < n; u++) {
                if (adjacencyMatrix[v][u] == 1) {
                    inDegree[u]--;
                    if (inDegree[u] == 0) {
                        queue.add(u);
                    }
                }
            }
        }

        if (result.size() != n) {
            return new ArrayList<>();
        }

        return result;
    }
}
