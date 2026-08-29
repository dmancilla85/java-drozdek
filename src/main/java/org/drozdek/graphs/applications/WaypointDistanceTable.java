package org.drozdek.graphs.applications;

import org.drozdek.graphs.WeightedGraph;
import org.drozdek.graphs.algorithms.ShortestPathAlgorithms;

/// Waypoint distance table built from an undirected weighted highway network.
///
/// Nodes are waypoints and weighted edges are highway segments. The
/// Floyd-Warshall algorithm precomputes the shortest travel distance between
/// every pair of waypoints in a single all-pairs table.
///
/// **Real-world use case:** Logistics dispatch and fleet routing that need
/// quick queries of distances between any origin-destination pair.
///
/// Complexity Analysis:
/// Time Complexity: O(V^3) to build the all-pairs table
/// Auxiliary Space: O(V^2) for the distance matrix
///
/// Bibliography:
///
/// - R. W. Floyd. *Algorithm 97: Shortest path*. Communications of the ACM, 1962.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
///
/// @see WeightedGraph
/// @see ShortestPathAlgorithms
public class WaypointDistanceTable {

    private static final int INF = Integer.MAX_VALUE / 2;

    private final WeightedGraph network;

    /// Creates a distance table over a highway network of the given size.
    ///
    /// @param waypoints number of waypoints (vertices)
    public WaypointDistanceTable(int waypoints) {
        this.network = new WeightedGraph(waypoints);
    }

    /// Adds an undirected weighted highway segment.
    ///
    /// @param first    one waypoint index
    /// @param second   the other waypoint index
    /// @param distance segment length
    /// @return true if the edge was added
    public boolean addSegment(int first, int second, int distance) {
        return network.createEdge(first, second, distance);
    }

    /// Returns the shortest travel distance between two waypoints.
    ///
    /// @param from source waypoint index
    /// @param to   destination waypoint index
    /// @return shortest distance, or INF when unreachable
    public int distanceBetween(int from, int to) {
        return ShortestPathAlgorithms.floydMarshallAlgorithm(network)[from][to];
    }

    /// Returns the number of waypoints in the network.
    ///
    /// @return vertex count
    public int waypointCount() {
        return network.cardinality();
    }
}
