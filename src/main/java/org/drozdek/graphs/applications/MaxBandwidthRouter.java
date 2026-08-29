package org.drozdek.graphs.applications;

import org.drozdek.graphs.FlowNetwork;
import org.drozdek.graphs.algorithms.FordFulkersonAlgorithm;

/// Routing service that computes maximum throughput between two nodes.
///
/// Network links are modelled as capacitated arcs of a flow network. The
/// Ford-Fulkerson algorithm (Edmonds-Karp variant) determines the maximum data
/// that can be pushed from a source to a sink without violating any link
/// capacity.
///
/// **Real-world use case:** WAN bandwidth planning, supply-chain throughput
/// analysis, and maximum-capacity path provisioning.
///
/// Complexity Analysis:
/// Time Complexity: O(V * E^2) for the Edmonds-Karp variant
/// Auxiliary Space: O(V^2) for the capacity and flow matrices
///
/// Bibliography:
///
/// - L. R. Ford and D. R. Fulkerson. *Maximal flow through a network*. Canadian Journal of Mathematics, 1956.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
///
/// @see FlowNetwork
/// @see FordFulkersonAlgorithm
public class MaxBandwidthRouter {

    private final FlowNetwork network;

    /// Creates a throughput router over a network of the given size.
    ///
    /// @param nodes number of nodes (vertices) in the network
    public MaxBandwidthRouter(int nodes) {
        this.network = new FlowNetwork(nodes);
    }

    /// Adds a directed link with a bandwidth capacity.
    ///
    /// @param from      source node index
    /// @param to        destination node index
    /// @param bandwidth capacity of the link
    /// @return true if the link was added
    public boolean addLink(int from, int to, int bandwidth) {
        return network.addEdge(from, to, bandwidth);
    }

    /// Computes the maximum throughput from source to sink.
    ///
    /// @param source source node index
    /// @param sink   sink node index
    /// @return the maximum achievable flow value
    public int maxThroughput(int source, int sink) {
        FordFulkersonAlgorithm.MaxFlow result =
                FordFulkersonAlgorithm.compute(network.getCapacityMatrix(), source, sink);
        return result.getValue();
    }
}
