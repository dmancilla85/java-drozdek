package org.drozdek.graphs.algorithms;

import java.time.Clock;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static java.time.ZoneId.systemDefault;

import org.drozdek.commons.LoggerService;
import org.drozdek.graphs.*;
import org.drozdek.trees.MinimumHeap;

/// Graph construction algorithms: minimum spanning trees (Prim-Jarnik and
/// Kruskal), maximum flow (Edmonds-Karp), and graph coloring.
///
/// **Real-world use case:** Network design, road and pipeline planning,
/// traffic capacity routing, and register allocation in compilers.
///
/// Complexity Analysis:
/// Time Complexity: O(E log V) Prim-Jarnik, O(E log E) Kruskal,
///                  O(V E^2) Edmonds-Karp, O(V + E) graph coloring
/// Auxiliary Space: O(V) for temporary structures
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
public final class ConstructionAlgorithms {
    private ConstructionAlgorithms() {
    }

    private static void validateVertexIndices(int cardinality, int... vertices) {
        for (int v : vertices) {
            Objects.checkIndex(v, cardinality);
        }
    }

    /// Loads every edge incident to the start vertex into the frontier heap.
    ///
    /// @param g       an undirected weighted graph
    /// @param inicial starting vertex
    /// @return min-heap seeded with the edges leaving the starting vertex
    private static MinimumHeap<Edge> seedFrontierHeap(WeightedGraph g, int inicial) {
        MinimumHeap<Edge> heap = new MinimumHeap<>(g.countEdges());
        for (int i = 0; i < g.cardinality(); i++) {
            if (g.adjacencyMatrix[inicial][i] == 1) {
                heap.insert(new Edge(g.vertices.get(inicial), g.vertices.get(i),
                        g.weightTable[inicial][i]));
            }
        }
        return heap;
    }

    /// Prim-Jarník minimum spanning tree algorithm.
    ///
    /// Grows a tree one vertex at a time from a starting vertex. At each step,
    /// the cheapest edge connecting the visited set to an unvisited vertex is
    /// added, expanding the frontier. Uses a min-heap for efficient edge selection.
    ///
    /// Time complexity: O(E log V)
    /// Space complexity: O(V + E)
    ///
    /// Real-world use case: Laying fiber-optic cable across a city at minimal cost —
    /// vertices are neighborhoods, edges are possible cable routes with installation
    /// costs.
    ///
    /// Reference: Jarník, V. (1930). "O jistém problému minimálním." Práce Moravské
    /// Přírodovědecké Společnosti 6: 57–63.
    /// Reference: Prim, R. C. (1957). "Shortest connection networks and some
    /// generalizations." Bell System Technical Journal 36(6): 1389–1401.
    ///
    /// @param g       an undirected weighted graph
    /// @param inicial starting vertex
    /// @return minimum spanning tree
    public static WeightedGraph primJarnikAlgorithm(WeightedGraph g, int inicial) {
        validateVertexIndices(g.cardinality(), inicial);

        WeightedGraph newGraph = new WeightedGraph(g.cardinality());
        boolean[] visited = new boolean[g.cardinality()];
        MinimumHeap<Edge> heap = seedFrontierHeap(g, inicial);

        visited[inicial] = true;

        int edgesInTree = 0;
        while (!heap.isEmpty() && edgesInTree < g.cardinality() - 1) {
            Edge e = heap.extractMin();
            int dest = e.getDestination().getKey();

            if (visited[dest]) {
                continue;
            }

            visited[dest] = true;
            newGraph.createEdge(e.getOrigin().getKey(), dest, e.getWeight());
            edgesInTree++;

            for (int i = 0; i < g.cardinality(); i++) {
                if (g.adjacencyMatrix[dest][i] == 1 && !visited[i]) {
                    heap.insert(new Edge(g.vertices.get(dest), g.vertices.get(i),
                            g.weightTable[dest][i]));
                }
            }
        }

        Clock ini = Clock.tickMillis(systemDefault());
        Clock end = Clock.tickMillis(systemDefault());
        LoggerService.logInfo("Tiempo algoritmo de Prim para N = " + g.cardinality() + ": "
                + (end.millis() - ini.millis()));

        return newGraph;
    }

    /// Kruskal's minimum spanning tree algorithm.
    ///
    /// Processes all edges in ascending weight order. An edge is added to the
    /// spanning tree only if its endpoints belong to different components
    /// (tracked via a Union-Find / Disjoint Set). This avoids cycles without
    /// requiring explicit visited-set checks.
    ///
    /// Time complexity: O(E log V)
    /// Space complexity: O(V)
    ///
    /// Real-world use case: Designing an electrical power grid where transformers
    /// must be connected with the least total cable length.
    ///
    /// Reference: Kruskal, J. B. (1956). "On the shortest spanning subtree of a
    /// graph and the traveling salesman problem." Proceedings of the AMS 7(1): 48–50.
    /// https://doi.org/10.1090/S0002-9939-1956-0078686-7
    ///
    /// @param g an undirected weighted graph
    /// @return minimum spanning tree
    public static WeightedGraph kruskalAlgorithm(WeightedGraph g) {

        WeightedGraph newGraph = new WeightedGraph(g.cardinality());
        MinimumHeap<Edge> queue = new MinimumHeap<>(g.countEdges());

        int[] parent = new int[g.cardinality()];
        for (int i = 0; i < g.cardinality(); i++) {
            parent[i] = i;
        }

        for (Edge element : g.edges) {
            queue.insert(element);
        }

        int edgesInTree = 0;
        while (!queue.isEmpty() && edgesInTree < g.cardinality() - 1) {
            Edge e = queue.extractMin();

            int u = DisjointSetUtils.find(parent, e.getOrigin().getKey());
            int v = DisjointSetUtils.find(parent, e.getDestination().getKey());

            if (u != v) {
                parent[u] = v;
                newGraph.createEdge(e.getOrigin().getKey(), e.getDestination().getKey(), e.getWeight());
                edgesInTree++;
            }
        }

        return newGraph;
    }

    /// Finds the shortest augmenting path from source to sink using BFS over
    /// residual capacities.
    ///
    /// @param capacity capacity matrix
    /// @param flow     current flow matrix
    /// @param source   source vertex
    /// @param sink     sink vertex
    /// @return parent array encoding the path (parent[sink] == -1 when none exists)
    private static int[] findAugmentingPath(int[][] capacity, int[][] flow,
                                            int source, int sink) {
        int n = capacity.length;
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        parent[source] = source;

        while (!queue.isEmpty() && parent[sink] == -1) {
            int u = queue.poll();
            for (int v = 0; v < n; v++) {
                if (parent[v] == -1 && capacity[u][v] - flow[u][v] > 0) {
                    parent[v] = u;
                    queue.add(v);
                }
            }
        }

        return parent;
    }

    /// Pushes the bottleneck amount along the augmenting path encoded by the
    /// parent array.
    ///
    /// @param capacity  capacity matrix
    /// @param flow      current flow matrix, mutated in place
    /// @param parent    parent array from the BFS phase
    /// @param source    source vertex
    /// @param sink      sink vertex
    /// @return the amount of flow pushed along the path
    private static int pushFlowAlongPath(int[][] capacity, int[][] flow, int[] parent,
                                         int source, int sink) {
        int bottleneck = Integer.MAX_VALUE;
        for (int v = sink; v != source; v = parent[v]) {
            int u = parent[v];
            bottleneck = Math.min(bottleneck, capacity[u][v] - flow[u][v]);
        }

        for (int v = sink; v != source; v = parent[v]) {
            int u = parent[v];
            flow[u][v] += bottleneck;
            flow[v][u] -= bottleneck;
        }

        return bottleneck;
    }

    /// Edmonds-Karp maximum flow algorithm (Edmonds & Karp, 1972).
    ///
    /// Uses BFS to find the shortest augmenting path at each iteration.
    /// This guarantees O(V·E²) runtime regardless of the capacity values,
    /// unlike the basic Ford-Fulkerson which can be exponential.
    ///
    /// Time complexity: O(V·E²)
    /// Space complexity: O(V²)
    ///
    /// Real-world use case: Network bandwidth planning, airline crew scheduling,
    /// bipartite matching, image segmentation (graph cuts).
    ///
    /// Reference: Edmonds, J. & Karp, R. M. (1972). "Theoretical improvements in
    /// algorithmic efficiency for network flow problems."
    /// Journal of the ACM 19(2): 248–264. https://doi.org/10.1145/321694.321699
    ///
    /// @param network a flow network with capacities
    /// @param source  source vertex
    /// @param sink    sink vertex
    /// @return maximum flow value from source to sink
    public static int edmondsKarpMaxFlow(FlowNetwork network, int source, int sink) {
        int n = network.getVertexCount();
        int[][] capacity = network.getCapacityMatrix();
        int[][] flow = new int[n][n];
        int maxFlow = 0;

        while (true) {
            int[] parent = findAugmentingPath(capacity, flow, source, sink);

            if (parent[sink] == -1) {
                break;
            }

            maxFlow += pushFlowAlongPath(capacity, flow, parent, source, sink);
        }

        return maxFlow;
    }

    /// Welsh-Powell graph coloring algorithm (Welsh & Powell, 1967).
    ///
    /// Sorts vertices by descending degree, then greedily assigns the smallest
    /// available color to each. Not guaranteed to find the chromatic number,
    /// but provides a fast, high-quality heuristic.
    ///
    /// Time complexity: O(V² + V·E)
    /// Space complexity: O(V)
    ///
    /// Real-world use case: Exam timetabling (no two exams with shared students
    /// at the same time), register allocation in compilers, frequency assignment
    /// in cellular networks.
    ///
    /// Reference: Welsh, D. J. A. & Powell, M. B. (1967). "An upper bound for the
    /// chromatic number of a graph and its application to timetabling problems."
    /// The Computer Journal 10(1): 85–86. https://doi.org/10.1093/comjnl/10.1.85
    ///
    /// @param g an undirected graph
    /// @return color assignment per vertex (0-based color index)
    public static int[] colorGraph(Graph g) {
        int n = g.cardinality();
        if (n == 0) {
            return new int[0];
        }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(
                g.vertices.get(b).getDegree(), g.vertices.get(a).getDegree()));

        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int idx : order) {
            boolean[] used = new boolean[n];
            for (int v = 0; v < n; v++) {
                if (g.adjacencyMatrix[idx][v] == 1 && color[v] != -1) {
                    used[color[v]] = true;
                }
            }
            for (int c = 0; c < n; c++) {
                if (!used[c]) {
                    color[idx] = c;
                    break;
                }
            }
        }

        return color;
    }
}
