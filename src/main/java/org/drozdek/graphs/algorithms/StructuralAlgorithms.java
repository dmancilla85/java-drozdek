package org.drozdek.graphs.algorithms;

import org.drozdek.graphs.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class StructuralAlgorithms {
    private StructuralAlgorithms() {
    }

    /// Detects cycles in an undirected unweighted graph.
    ///
    /// Uses the Union-Find (Disjoint Set) algorithm. For each edge (u, v),
    /// if u and v already belong to the same set, a cycle exists.
    ///
    /// Time complexity: O(E α(V))
    /// Space complexity: O(V)
    ///
    /// Real-world use case: Verifying that a prerequisite dependency graph
    /// (e.g., course prerequisites) has no circular dependencies before scheduling.
    ///
    /// Reference: Galler, B. A. & Fisher, M. J. (1964). "An improved equivalence
    /// algorithm." Communications of the ACM 7(5): 301–303.
    /// [...](https://doi.org/10.1145/364099.364331)
    ///
    /// @param g an undirected unweighted graph
    /// @return true if the graph contains at least one cycle
    public static boolean cycleDetector(Graph g) {
        int n = g.cardinality();
        if (n == 0) {
            return false;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (Edge e : g.edges) {
            int u = DisjointSetUtils.find(parent, e.getOrigin().getKey());
            int v = DisjointSetUtils.find(parent, e.getDestination().getKey());

            if (u == v) {
                return true;
            }
            parent[u] = v;
        }

        return false;
    }

    /// Detects cycles in an undirected weighted graph.
    ///
    /// Identical algorithm to [cycleDetector(Graph)] since WeightedGraph
    /// inherits the edge list from Graph. Edge weights do not affect cycle detection.
    ///
    /// Reference: Galler, B. A. & Fisher, M. J. (1964). "An improved equivalence
    /// algorithm." Communications of the ACM 7(5): 301–303.
    ///
    /// @param g an undirected weighted graph
    /// @return true if the graph contains at least one cycle
    public static boolean cycleDetector(WeightedGraph g) {
        int n = g.cardinality();
        if (n == 0) {
            return false;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (Edge e : g.edges) {
            int u = DisjointSetUtils.find(parent, e.getOrigin().getKey());
            int v = DisjointSetUtils.find(parent, e.getDestination().getKey());

            if (u == v) {
                return true;
            }
            parent[u] = v;
        }

        return false;
    }

    /// Detects cycles in a directed graph (via Digraph interface).
    ///
    /// Uses DFS with 3-color marking: WHITE (unvisited), GRAY (in current recursion
    /// stack), BLACK (fully explored). If a back-edge to a GRAY vertex is found,
    /// a cycle exists.
    ///
    /// Time complexity: O(V + E)
    /// Space complexity: O(V)
    ///
    /// Real-world use case: Deadlock detection in operating systems — processes and
    /// resources form a directed wait-for graph; a cycle indicates a deadlock.
    ///
    /// Reference: Tarjan, R. (1972). "Depth-first search and linear graph algorithms."
    /// SIAM Journal on Computing 1(2): 146–160. [...](https://doi.org/10.1137/0201010)
    ///
    /// @param g a directed graph
    /// @return true if the graph contains at least one cycle
    public static boolean cycleDetector(Digraph g) {
        int n = g.cardinality();
        if (n == 0) {
            return false;
        }

        int[] state = new int[n];

        for (int i = 0; i < n; i++) {
            if (state[i] == 0 && dfsHasCycle(g, i, state)) {
                return true;
            }
        }

        return false;
    }

    private static boolean dfsHasCycle(Digraph g, int v, int[] state) {
        state[v] = 1;

        for (int u = 0; u < g.cardinality(); u++) {
            if (g.hasArc(v, u)) {
                if (state[u] == 1) {
                    return true;
                }

                if (state[u] == 0 && dfsHasCycle(g, u, state)) {
                    return true;
                }
            }
        }

        state[v] = 2;
        return false;
    }

    /// Counts incoming arcs for every vertex of the graph.
    ///
    /// @param g a directed graph
    /// @param n number of vertices
    /// @return array with the in-degree of each vertex
    private static int[] computeInDegrees(Digraph g, int n) {
        int[] inDegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g.hasArc(i, j)) {
                    inDegree[j]++;
                }
            }
        }

        return inDegree;
    }

    /// Topological sort (Kahn's algorithm, 1962).
    ///
    /// Processes vertices with in-degree zero iteratively. Each time a vertex is
    /// processed, its outgoing edges are removed, potentially freeing new vertices.
    /// If the graph contains a cycle, an empty list is returned.
    ///
    /// Time complexity: O(V + E)
    /// Space complexity: O(V)
    ///
    /// Real-world use case: Build system dependency resolution (make, Maven),
    /// course prerequisite scheduling, task scheduling in project management.
    ///
    /// Reference: Kahn, A. B. (1962). "Topological sorting of large networks."
    /// Communications of the ACM 5(11): 558–562. [...](https://doi.org/10.1145/368996.369025)
    ///
    /// @param g a directed graph
    /// @return topological ordering of vertices, or empty list if a cycle exists
    public static List<Integer> topologicalSort(Digraph g) {
        int n = g.cardinality();
        int[] inDegree = computeInDegrees(g, n);

        Queue<Integer> queue = new LinkedList<>();
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
                if (g.hasArc(v, u)) {
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

    /// Strongly connected components — Tarjan's algorithm (Tarjan, 1972).
    ///
    /// Uses a single DFS pass with a stack and low-link values. Each maximal
    /// strongly connected subgraph is identified and returned as a list of
    /// vertex indices.
    ///
    /// Time complexity: O(V + E)
    /// Space complexity: O(V)
    ///
    /// Real-world use case: Web page link analysis (Google PageRank), cycle-based
    /// garbage collection, formal verification of concurrent systems.
    ///
    /// Reference: Tarjan, R. (1972). "Depth-first search and linear graph algorithms."
    /// SIAM Journal on Computing 1(2): 146–160. [...](https://doi.org/10.1137/0201010)
    ///
    /// @param g a directed graph
    /// @return list of strongly connected components (each is a list of vertex indices)
    public static List<List<Integer>> stronglyConnectedComponents(Digraph g) {
        int n = g.cardinality();
        TarjanState state = new TarjanState(n);

        for (int i = 0; i < n; i++) {
            if (state.index[i] == -1) {
                tarjanScc(g, i, state);
            }
        }

        return state.components;
    }

    /// Mutable DFS state shared across the recursive Tarjan traversal.
    private static final class TarjanState {
        private final int[] index;
        private final int[] lowlink;
        private final boolean[] onStack;
        private final Deque<Integer> stack = new ArrayDeque<>();
        private final List<List<Integer>> components = new ArrayList<>();
        private int counter;

        private TarjanState(int n) {
            index = new int[n];
            lowlink = new int[n];
            onStack = new boolean[n];
            Arrays.fill(index, -1);
        }
    }

    private static void tarjanScc(Digraph g, int v, TarjanState state) {
        state.index[v] = state.counter;
        state.lowlink[v] = state.counter;
        state.counter++;
        state.stack.push(v);
        state.onStack[v] = true;

        for (int u = 0; u < g.cardinality(); u++) {
            if (!g.hasArc(v, u)) {
                continue;
            }
            if (state.index[u] == -1) {
                tarjanScc(g, u, state);
                state.lowlink[v] = Math.min(state.lowlink[v], state.lowlink[u]);
            } else if (state.onStack[u]) {
                state.lowlink[v] = Math.min(state.lowlink[v], state.index[u]);
            }
        }

        if (state.lowlink[v] == state.index[v]) {
            List<Integer> component = new ArrayList<>();
            int w;
            do {
                w = state.stack.pop();
                state.onStack[w] = false;
                component.add(w);
            } while (w != v);
            state.components.add(component);
        }
    }

    /// Finds articulation points (cut-vertices) in an undirected graph (Tarjan, 1974).
    ///
    /// Uses DFS with discovery time and low-link values. A vertex is an articulation
    /// point if removing it increases the number of connected components.
    ///
    /// Time complexity: O(V + E)
    /// Space complexity: O(V)
    ///
    /// Real-world use case: Identifying single points of failure in a
    /// telecommunications network, network reliability analysis.
    ///
    /// Reference: Tarjan, R. E. (1974). "A note on finding the bridges of a graph."
    /// Information Processing Letters 2(6): 160–161.
    /// [...](https://doi.org/10.1016/0020-0190(74)90003-9)
    ///
    /// @param g an undirected unweighted graph
    /// @return list of articulation point vertex indices
    public static List<Integer> articulationPoints(Graph g) {
        int n = g.cardinality();
        if (n == 0) {
            return new ArrayList<>();
        }

        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        boolean[] ap = new boolean[n];
        Arrays.fill(disc, -1);
        Arrays.fill(parent, -1);
        int[] time = {0};

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfsArticulation(g, i, disc, low, parent, ap, time);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (ap[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private static void dfsArticulation(Graph g, int u, int[] disc, int[] low,
                                         int[] parent, boolean[] ap, int[] time) {
        disc[u] = time[0];
        low[u] = time[0];
        time[0]++;
        int children = 0;

        for (int v = 0; v < g.cardinality(); v++) {
            if (g.adjacencyMatrix[u][v] != 1) {
                continue;
            }
            if (disc[v] == -1) {
                children++;
                parent[v] = u;
                dfsArticulation(g, v, disc, low, parent, ap, time);
                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == -1 && children > 1) {
                    ap[u] = true;
                }
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    ap[u] = true;
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    /// Finds bridges (cut-edges) in an undirected graph (Tarjan, 1974).
    ///
    /// Uses DFS with discovery time and low-link values. An edge is a bridge if
    /// removing it disconnects the graph.
    ///
    /// Time complexity: O(V + E)
    /// Space complexity: O(V)
    ///
    /// Real-world use case: Finding critical connections in a network whose
    /// removal would isolate entire subnets.
    ///
    /// Reference: Tarjan, R. E. (1974). "A note on finding the bridges of a graph."
    /// Information Processing Letters 2(6): 160–161.
    ///
    /// @param g an undirected unweighted graph
    /// @return list of bridge edges
    public static List<Edge> bridges(Graph g) {
        int n = g.cardinality();
        if (n == 0) {
            return new ArrayList<>();
        }

        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        List<Edge> bridgeList = new ArrayList<>();
        Arrays.fill(disc, -1);
        Arrays.fill(parent, -1);
        int[] time = {0};

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfsBridges(g, i, disc, low, parent, bridgeList, time);
            }
        }

        return bridgeList;
    }

    private static void dfsBridges(Graph g, int u, int[] disc, int[] low,
                                    int[] parent, List<Edge> bridgeList, int[] time) {
        disc[u] = time[0];
        low[u] = time[0];
        time[0]++;

        for (int v = 0; v < g.cardinality(); v++) {
            if (g.adjacencyMatrix[u][v] != 1) {
                continue;
            }
            if (disc[v] == -1) {
                parent[v] = u;
                dfsBridges(g, v, disc, low, parent, bridgeList, time);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    bridgeList.add(new Edge(g.vertices.get(u), g.vertices.get(v)));
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
