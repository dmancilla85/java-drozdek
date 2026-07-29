package org.drozdek.graphs.unlam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DirectedAcyclicGraph extends DirectedGraph {

    public DirectedAcyclicGraph(int n) {
        super(n);
    }

    @Override
    public boolean createArc(int node1, int node2) {
        if (adjacencyMatrix[node1][node2] == 1 || node1 == node2) {
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
