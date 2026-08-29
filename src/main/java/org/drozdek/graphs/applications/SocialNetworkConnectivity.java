package org.drozdek.graphs.applications;

import org.drozdek.graphs.DirectedGraph;
import org.drozdek.graphs.DisjointSetUtils;

/// Social-network connectivity tracker combining a directed follow graph with
/// disjoint-set reachability over user accounts.
///
/// Follow relationships are stored as arcs of a directed graph, while a union-
/// find parent table supports fast checks of which manager (set representative)
/// a given user ultimately reports to.
///
/// **Real-world use case:** Modelling follow graphs and permission/ownership
/// hierarchy lookups in social and enterprise platforms.
///
/// Complexity Analysis:
/// Time Complexity: O(1) arc ops; near-constant named find with path halving
/// Auxiliary Space: O(V^2) for the adjacency matrix, O(V) for the parent table
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
///
/// @see DirectedGraph
/// @see DisjointSetUtils
public class SocialNetworkConnectivity {

    private final int users;
    private final DirectedGraph follow;
    private final int[] parent;

    /// Creates a connectivity tracker over a fixed number of user accounts.
    ///
    /// @param users number of users
    public SocialNetworkConnectivity(int users) {
        this.users = users;
        this.follow = new DirectedGraph(users);
        this.parent = new int[users];
        for (int i = 0; i < users; i++) {
            parent[i] = i;
        }
    }

    /// Records that one user follows another.
    ///
    /// @param from following user index
    /// @param to   followed user index
    /// @return true if the arc was added
    public boolean addFollow(int from, int to) {
        return follow.createArc(from, to);
    }

    /// Returns whether a follow arc exists.
    ///
    /// @param from following user index
    /// @param to   followed user index
    /// @return true if present
    public boolean hasFollow(int from, int to) {
        return follow.hasArc(from, to);
    }

    /// Returns the number of recorded follow arcs.
    ///
    /// @return arc count
    public int followCount() {
        return follow.countArcs();
    }

    /// Returns the set representative (manager) for a user.
    ///
    /// @param user user index
    /// @return the disjoint-set root for that user
    public int managerOf(int user) {
        return DisjointSetUtils.find(parent, user);
    }

    /// Returns the number of users tracked.
    ///
    /// @return user count
    public int userCount() {
        return users;
    }
}
