package org.drozdek.graphs;

/// Contract for directed graph structures (digraphs).
///
/// **Real-world use case:** Dependency graphs, workflow engines, and
/// network routing models where relationships have direction.
///
/// Complexity Analysis:
/// Time Complexity: implementation-defined for cardinality and arc lookup
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
///
/// @see DirectedGraph
public interface Digraph {
    /// Returns the number of vertices in the digraph.
    ///
    /// @return vertex count derived from the underlying storage
    int cardinality();

    /// Checks whether a directed arc connects two vertices.
    ///
    /// @param from source vertex index
    /// @param to   target vertex index
    /// @return true if the arc from-to exists
    boolean hasArc(int from, int to);
}
