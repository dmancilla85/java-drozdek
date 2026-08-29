package org.drozdek.graphs.algorithms;

/// Brelaz (DSATUR) heuristic for graph vertex coloring.
///
/// Vertices are colored one at a time, always choosing the uncolored vertex
/// with the highest saturation degree (the number of different colors among
/// its neighbours), breaking ties by current degree. Each chosen vertex is
/// given the smallest color not used by any of its neighbours. The heuristic
/// typically produces near-optimal colorings and is exact for many graph
/// classes.
///
/// **Real-world use case:** Register allocation in compilers, exam
/// timetabling, and radio-frequency assignment problems.
///
/// Complexity Analysis:
/// Time Complexity: O(n^2) for an n-vertex graph
/// Auxiliary Space: O(n)
///
/// Bibliography:
///
/// - Brelaz, D. *New methods to color the vertices of a graph*. Communications of the ACM, 1979.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
public final class BrelazColoringAlgorithm {

    private BrelazColoringAlgorithm() {
        // do nothing
    }

    /// Colors the vertices of the graph described by the given adjacency
    /// matrix and returns the color of each vertex.
    ///
    /// @param adjacency symmetric adjacency matrix of size n x n
    /// @return array where entry {@code i} is the color (>= 1) of vertex `i`
    public static int[] color(boolean[][] adjacency) {
        int n = adjacency.length;
        int[] color = new int[n];
        int[] degree = new int[n];
        int[] saturation = new int[n];
        boolean[] colored = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacency[i][j]) {
                    degree[i]++;
                }
            }
        }
        int remaining = n;
        while (remaining > 0) {
            int chosen = selectVertex(n, colored, saturation, degree);
            colored[chosen] = true;
            color[chosen] = smallestAvailable(adjacency, color, chosen);
            for (int j = 0; j < n; j++) {
                if (adjacency[chosen][j] && !colored[j] && increasesSaturation(color, color[chosen], j)) {
                    saturation[j]++;
                }
            }
            remaining--;
        }
        return color;
    }

    private static int selectVertex(int n, boolean[] colored, int[] saturation, int[] degree) {
        int best = -1;
        for (int i = 0; i < n; i++) {
            if (colored[i]) {
                continue;
            }
            if (best < 0
                    || saturation[i] > saturation[best]
                    || (saturation[i] == saturation[best] && degree[i] > degree[best])) {
                best = i;
            }
        }
        return best;
    }

    private static int smallestAvailable(boolean[][] adjacency, int[] color, int vertex) {
        int n = adjacency.length;
        boolean[] used = new boolean[n + 1];
        for (int j = 0; j < n; j++) {
            if (adjacency[vertex][j] && color[j] > 0) {
                used[color[j]] = true;
            }
        }
        for (int c = 1; c <= n; c++) {
            if (!used[c]) {
                return c;
            }
        }
        return 1;
    }

    private static boolean increasesSaturation(int[] color, int usedColor, int vertex) {
        return color[vertex] != usedColor;
    }
}
