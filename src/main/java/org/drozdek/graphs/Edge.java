package org.drozdek.graphs;

import org.drozdek.commons.LoggerService;

/// Graph edge connecting two vertices, optionally weighted and directed.
///
/// **Real-world use case:** Road segments between intersections in
/// navigation graphs, friendship links in social networks, and weighted
/// connections in network cost models.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for accessors, compareTo, equals, hashCode
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
///
public class Edge implements Comparable<Edge> {

    protected Vertex origin;
    protected Vertex destination;
    protected int weight;
    protected boolean directed;


    /// Creates an unweighted undirected edge.
    ///
    /// @param v1 origin vertex
    /// @param v2 destination vertex
    public Edge(Vertex v1, Vertex v2) {
        this(v1, v2, 0, false);
    }

    /// Creates an undirected edge with an explicit weight.
    ///
    /// @param v1     origin vertex
    /// @param v2     destination vertex
    /// @param weight edge weight
    public Edge(Vertex v1, Vertex v2, int weight) {
        this(v1, v2, weight, false);
    }

    /// Constructor with explicit weight and direction flag.
    ///
    /// @param v1       origin vertex
    /// @param v2       destination vertex
    /// @param weight   edge weight
    /// @param directed true if the edge is directed
    public Edge(Vertex v1, Vertex v2, int weight, boolean directed) {
        this.origin = v1;
        this.destination = v2;
        this.weight = weight;
        this.directed = directed;
    }

    static void main(@SuppressWarnings("unused") String[] args) {
        Vertex a = new Vertex(0);
        Vertex b = new Vertex(1);
        Vertex c = new Vertex(2, "Juan"); //$NON-NLS-1$
        Vertex d = new Vertex(3);

        LoggerService.logInfo(a.toString());
        LoggerService.logInfo(b.toString());
        LoggerService.logInfo(c.toString());
        LoggerService.logInfo(d.toString());

        Edge ab = new Edge(a, b);
        Edge ba = new Edge(b, a);
        LoggerService.logInfo(ab.toString());
        LoggerService.logInfo(ba.toString());
        LoggerService.logInfo("ab equals ba: " + ab.equals(ba)); //$NON-NLS-1$

    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Edge other))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (directed != other.directed)
            return false;
        if (origin == null) {
            return other.origin == null;
        } else return origin.equals(other.origin);
    }

    /// Returns the destination vertex of this edge.
    ///
    /// @return the destination
    public Vertex getDestination() {
        return destination;
    }

    /// Replaces the destination vertex of this edge.
    ///
    /// @param destination the destination to set
    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    /// Returns the origin vertex of this edge.
    ///
    /// @return the origin
    public Vertex getOrigin() {
        return origin;
    }

    /// Replaces the origin vertex of this edge.
    ///
    /// @param origin the origin to set
    public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

    /// Returns the weight assigned to this edge.
    ///
    /// @return the weight
    public int getWeight() {
        return weight;
    }

    /// Assigns a new weight to this edge.
    ///
    /// @param weight the weight to set
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + (directed ? 1231 : 1237);
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        return result;
    }

    /// Renders the edge as its origin and destination names.
    ///
    /// @return printable representation of the edge
    public String toString() {
        return "{" + this.origin.getName()
                + ", " + this.destination.getName() + "}";
    }

}
