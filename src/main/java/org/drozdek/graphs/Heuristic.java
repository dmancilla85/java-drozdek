package org.drozdek.graphs;

/// Admissible heuristic function contract consumed by A* pathfinding.
///
/// **Real-world use case:** Grid/map distance estimates (Manhattan or
/// Euclidean), puzzle solvers, and route estimation in navigation
/// systems.
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
