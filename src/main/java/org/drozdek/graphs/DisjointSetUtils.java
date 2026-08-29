package org.drozdek.graphs;

/// Union-find helper providing find with path halving over a parent
/// array.
///
/// **Real-world use case:** Cycle detection in Kruskal's minimum spanning
/// tree algorithm, network connectivity queries, and image segmentation.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) amortized per find with path halving
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
///
public final class DisjointSetUtils {
    private DisjointSetUtils() {
    }

    /// Finds the representative of the set containing x, applying path
    /// halving along the way.
    ///
    /// Complexity: amortized near-constant; O(log n) worst case per call.
    ///
    /// @param parent union-find parent array
    /// @param x      element whose set representative is sought
    /// @return the root index representing the set of x
    public static int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
