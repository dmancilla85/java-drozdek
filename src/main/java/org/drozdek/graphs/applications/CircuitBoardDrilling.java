package org.drozdek.graphs.applications;

import java.util.List;
import org.drozdek.graphs.EulerianCircuit;

/// Drill-path planner for a printed circuit board using an Eulerian circuit.
///
/// Holes that must be drilled form the vertices of a graph and the tool moves
/// between them are edges. An Eulerian circuit is a closed path that visits
/// every required connection exactly once, so the drill bit never retraces a
/// move, minimising wasted travel during fabrication.
///
/// **Real-world use case:** CNC drilling of circuit boards and plotting/pencil
/// raster plotters that must trace every segment without lifting the tool.
///
/// Complexity Analysis:
/// Time Complexity: O(V + E) to find the Eulerian circuit
/// Auxiliary Space: O(E) for the circuit stack
///
/// Bibliography:
///
/// - Leonhard Euler. *Solutio problematis ad geometriam situs pertinentis*. 1736.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 9.
///
/// @see EulerianCircuit
public final class CircuitBoardDrilling {

    private CircuitBoardDrilling() {
        // do nothing
    }

    /// Computes a closed drill route covering every required move exactly once.
    ///
    /// @param adjacency adjacency matrix of required hole-to-hole moves
    /// @return the Eulerian circuit as an ordered list of vertices, or an empty
    ///         list if no Eulerian circuit exists
    public static List<Integer> drillRoute(boolean[][] adjacency) {
        return EulerianCircuit.findCircuit(adjacency);
    }
}
