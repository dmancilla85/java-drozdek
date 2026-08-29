package org.drozdek.graphs;

/// Admissible heuristic function contract consumed by A* pathfinding.
///
/// **Real-world use case:** Grid/map distance estimates (Manhattan or
/// Euclidean), puzzle solvers, and route estimation in navigation
/// systems.
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
///
@FunctionalInterface
public interface Heuristic {
    /// Estimates the traversal cost between two vertices for A* search.
    ///
    /// @param source start vertex index
    /// @param target goal vertex index
    /// @return a non-negative cost estimate; admissible implementations
    ///         never overestimate the true cost
    int estimate(int source, int target);
}
