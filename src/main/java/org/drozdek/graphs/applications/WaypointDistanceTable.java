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

    /// Sentinel distance used when two waypoints are unreachable.
    public static final int INF = Integer.MAX_VALUE / 2;

    private final WeightedGraph network;
    private int[][] distances;
    private boolean dirty = true;

    /// Creates a distance table over a highway network of the given size.
    ///
    /// The all-pairs shortest-distance matrix is built lazily from the current
    /// segments the first time a distance is queried, and rebuilt whenever the
    /// network changes.
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
        if (network.createEdge(first, second, distance)) {
            dirty = true;
            return true;
        }
        return false;
    }

    /// Returns the shortest travel distance between two waypoints.
    ///
    /// @param from source waypoint index
    /// @param to   destination waypoint index
    /// @return shortest distance, or INF when unreachable
    /// @throws IllegalArgumentException when either waypoint index is out of range
    public int distanceBetween(int from, int to) {
        int size = network.cardinality();
        if (from < 0 || from >= size || to < 0 || to >= size) {
            throw new IllegalArgumentException("Waypoint index out of range");
        }
        if (dirty) {
            distances = ShortestPathAlgorithms.floydMarshallAlgorithm(network);
            dirty = false;
        }
        return distances[from][to];
    }

    /// Returns the number of waypoints in the network.
    ///
    /// @return vertex count
    public int waypointCount() {
        return network.cardinality();
    }
}
