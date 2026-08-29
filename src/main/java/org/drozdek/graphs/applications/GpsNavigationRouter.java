package org.drozdek.graphs.applications;

import org.drozdek.graphs.WeightedDigraph;
import org.drozdek.graphs.algorithms.ShortestPathAlgorithms;

/// GPS navigation router that computes shortest travel times over a road network.
///
/// Intersections are modelled as vertices of a weighted digraph and roads as
/// weighted arcs. Dijkstra's algorithm from a chosen origin yields the shortest
/// travel time to every reachable intersection in the network.
///
/// **Real-world use case:** Turn-by-turn navigation, logistics route planning,
/// and the routing layer of ride-hailing and delivery services.
///
/// Complexity Analysis:
/// Time Complexity: O(V^2) for the dense Dijkstra variant
/// Auxiliary Space: O(V) for the distance table
///
/// Bibliography:
///
/// - E. W. Dijkstra. *A note on two problems in connexion with graphs*. Numerische Mathematik, 1959.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
///
/// @see WeightedDigraph
/// @see ShortestPathAlgorithms
public class GpsNavigationRouter {

    private final WeightedDigraph roadNetwork;

    /// Creates a router over a road network of the given size.
    ///
    /// @param intersections number of intersections (vertices) in the network
    public GpsNavigationRouter(int intersections) {
        this.roadNetwork = new WeightedDigraph(intersections);
    }

    /// Adds a one-way road with a measured travel time.
    ///
    /// @param from    origin intersection index
    /// @param to      destination intersection index
    /// @param minutes travel time along the road
    /// @return true if the road was added
    public boolean addRoad(int from, int to, int minutes) {
        return roadNetwork.createArc(from, to, minutes);
    }

    /// Computes the shortest travel time from the origin to every intersection.
    ///
    /// @param start origin intersection index
    /// @return array of shortest times; unreachable intersections are marked with
    ///         the sentinel used by the algorithm
    public Integer[] shortestTimesFrom(int start) {
        return ShortestPathAlgorithms.dijkstraAlgorithm(roadNetwork, start);
    }
}
