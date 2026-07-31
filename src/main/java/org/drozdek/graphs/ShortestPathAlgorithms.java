package org.drozdek.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ShortestPathAlgorithms {
    private ShortestPathAlgorithms() {
    }

    private static int minDistance(Integer[] d, boolean[] visited, WeightedDigraph g) {
        int minimum = Integer.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < g.cardinality(); i++) {
            if (!visited[i] && d[i] <= minimum) {
                minimum = d[i];
                index = i;
            }
        }

        return index;
    }

    /// Dijkstra's shortest path algorithm.
    ///
    /// Finds the shortest paths from a source vertex to all other vertices in
    /// a weighted directed graph with non-negative edge weights. Uses a linear
    /// scan to pick the unvisited vertex with the smallest distance at each step
    /// (dense-graph variant, O(V²)).
    ///
    /// Time complexity: O(V²)
    /// Space complexity: O(V)
    ///
    /// Real-world use case: GPS navigation — vertices are intersections, edges are
    /// roads with travel times, the algorithm finds the fastest route from a user's
    /// location to every other intersection in the map.
    ///
    /// Reference: Dijkstra, E. W. (1959). "A note on two problems in connexion with
    /// graphs." Numerische Mathematik 1: 269–271. https://doi.org/10.1007/BF01386390
    ///
    /// @param g       weighted directed graph
    /// @param inicial source vertex
    /// @return array of shortest distances from the source to every vertex
    public static Integer[] dijkstraAlgorithm(WeightedDigraph g, int inicial) {

        Integer[] d = new Integer[g.cardinality()];
        boolean[] visited = new boolean[g.cardinality()];

        for (int i = 0; i < g.cardinality(); i++) {
            d[i] = (i != inicial) ? Integer.MAX_VALUE : 0;
        }

        int visitedCount = 0;
        while (visitedCount < g.cardinality()) {
            int v0 = minDistance(d, visited, g);

            if (d[v0] == Integer.MAX_VALUE) {
                break;
            }

            visited[v0] = true;
            visitedCount++;

            for (int u = 0; u < g.cardinality(); u++) {
                if (!visited[u] && g.weightTable[v0][u] != 0
                        && d[u] > d[v0] + g.weightTable[v0][u]) {
                    d[u] = d[v0] + g.weightTable[v0][u];
                }
            }
        }

        return d;
    }

    /// Bellman-Ford shortest path algorithm (Bellman 1958, Ford 1956).
    ///
    /// Relaxes all edges V-1 times using dynamic programming, then checks for
    /// negative-weight cycles. Handles negative edge weights, unlike Dijkstra.
    /// Returns null if a negative cycle is reachable from the source.
    ///
    /// Time complexity: O(V·E)
    /// Space complexity: O(V)
    ///
    /// Real-world use case: Currency arbitrage detection (negative cycles in forex
    /// exchange rates), RIP (Routing Information Protocol) in computer networks.
    ///
    /// Reference: Bellman, R. (1958). "On a routing problem."
    /// Quarterly of Applied Mathematics 16(1): 87–90.
    /// https://doi.org/10.1090/qam/102435
    /// Reference: Ford, L. R. (1956). "Network flow theory." RAND Corp. P-923.
    ///
    /// @param g      weighted directed graph
    /// @param source source vertex
    /// @return array of shortest distances, or null if a negative cycle is detected
    public static Integer[] bellmanFordAlgorithm(WeightedDigraph g, int source) {
        int n = g.cardinality();
        int inf = Integer.MAX_VALUE / 2;
        Integer[] dist = new Integer[n];
        Arrays.fill(dist, inf);
        dist[source] = 0;

        for (int i = 1; i < n; i++) {
            for (int u = 0; u < n; u++) {
                if (dist[u] == inf) {
                    continue;
                }
                for (int v = 0; v < n; v++) {
                    if (g.adjacencyMatrix[u][v] == 1
                            && dist[v] > dist[u] + g.weightTable[u][v]) {
                        dist[v] = dist[u] + g.weightTable[u][v];
                    }
                }
            }
        }

        for (int u = 0; u < n; u++) {
            if (dist[u] == inf) {
                continue;
            }
            for (int v = 0; v < n; v++) {
                if (g.adjacencyMatrix[u][v] == 1
                        && dist[v] > dist[u] + g.weightTable[u][v]) {
                    return null;
                }
            }
        }

        return dist;
    }

    /// Floyd-Warshall all-pairs shortest path algorithm.
    ///
    /// Considers every vertex as an intermediate point for every pair of source
    /// and destination vertices. Uses dynamic programming to iteratively improve
    /// the shortest-path estimate until the optimal distances are found.
    ///
    /// Time complexity: O(V³)
    /// Space complexity: O(V²)
    ///
    /// Real-world use case: Urban traffic management — computes the shortest travel
    /// distance between every pair of intersections in a city grid, used for
    /// real-time route optimization across a fleet of vehicles.
    ///
    /// Reference: Floyd, R. W. (1962). "Algorithm 97: Shortest path."
    /// Communications of the ACM 5(6): 345. https://doi.org/10.1145/367766.368168
    /// Reference: Warshall, S. (1962). "A theorem on Boolean matrices."
    /// Journal of the ACM 9(1): 11–12. https://doi.org/10.1145/321105.321107
    ///
    /// @param g weighted graph
    /// @return matrix of shortest distances between every pair of vertices
    public static int[][] floydMarshallAlgorithm(WeightedGraph g) {

        int n = g.cardinality();
        int inf = Integer.MAX_VALUE / 2;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], inf);
            dist[i][i] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g.adjacencyMatrix[i][j] == 1) {
                    dist[i][j] = g.weightTable[i][j];
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != inf && dist[k][j] != inf) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        return dist;
    }

    /// A* shortest path algorithm (Hart, Nilsson & Raphael, 1968).
    ///
    /// Best-first search using f(n) = g(n) + h(n), where g is the actual cost
    /// from the source and h is an admissible heuristic estimate to the target.
    /// Guarantees optimality if the heuristic never overestimates the true cost.
    ///
    /// Time complexity: O(E) typical, O(b^d) worst-case
    /// Space complexity: O(V)
    ///
    /// Real-world use case: GPS route planning, video game enemy pathfinding,
    /// robot motion planning, puzzle solving (15-puzzle, Sokoban).
    ///
    /// Reference: Hart, P. E., Nilsson, N. J. & Raphael, B. (1968). "A formal basis
    /// for the heuristic determination of minimum cost paths."
    /// IEEE Trans. Systems Science and Cybernetics 4(2): 100–107.
    /// https://doi.org/10.1109/TSSC.1968.300136
    ///
    /// @param g       an undirected weighted graph
    /// @param source  source vertex
    /// @param target  target vertex
    /// @param h       admissible heuristic function
    /// @return list of vertices forming the shortest path, or empty if unreachable
    public static List<Integer> aStarAlgorithm(WeightedGraph g, int source, int target,
                                               Heuristic h) {
        int n = g.cardinality();
        int[] gScore = new int[n];
        int[] fScore = new int[n];
        int[] parent = new int[n];
        boolean[] closed = new boolean[n];
        Arrays.fill(gScore, Integer.MAX_VALUE);
        Arrays.fill(fScore, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        gScore[source] = 0;
        fScore[source] = h.estimate(source, target);

        for (int count = 0; count < n; count++) {
            int current = -1;
            int bestF = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!closed[i] && fScore[i] < bestF) {
                    bestF = fScore[i];
                    current = i;
                }
            }

            if (current == -1 || current == target) {
                break;
            }

            closed[current] = true;

            for (int v = 0; v < n; v++) {
                if (g.adjacencyMatrix[current][v] == 1 && !closed[v]) {
                    int tentative = gScore[current] + g.weightTable[current][v];
                    if (tentative < gScore[v]) {
                        parent[v] = current;
                        gScore[v] = tentative;
                        fScore[v] = tentative + h.estimate(v, target);
                    }
                }
            }
        }

        if (parent[target] == -1 && source != target) {
            return new ArrayList<>();
        }

        List<Integer> path = new ArrayList<>();
        for (int v = target; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);
        return path;
    }

    /// A* shortest path on a weighted digraph.
    public static List<Integer> aStarAlgorithm(WeightedDigraph g, int source, int target,
                                               Heuristic h) {
        int n = g.cardinality();
        int[] gScore = new int[n];
        int[] fScore = new int[n];
        int[] parent = new int[n];
        boolean[] closed = new boolean[n];
        Arrays.fill(gScore, Integer.MAX_VALUE);
        Arrays.fill(fScore, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        gScore[source] = 0;
        fScore[source] = h.estimate(source, target);

        for (int count = 0; count < n; count++) {
            int current = -1;
            int bestF = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!closed[i] && fScore[i] < bestF) {
                    bestF = fScore[i];
                    current = i;
                }
            }

            if (current == -1 || current == target) {
                break;
            }

            closed[current] = true;

            for (int v = 0; v < n; v++) {
                if (g.adjacencyMatrix[current][v] == 1 && !closed[v]) {
                    int tentative = gScore[current] + g.weightTable[current][v];
                    if (tentative < gScore[v]) {
                        parent[v] = current;
                        gScore[v] = tentative;
                        fScore[v] = tentative + h.estimate(v, target);
                    }
                }
            }
        }

        if (parent[target] == -1 && source != target) {
            return new ArrayList<>();
        }

        List<Integer> path = new ArrayList<>();
        for (int v = target; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);
        return path;
    }
}
