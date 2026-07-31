package org.drozdek.graphs;

public interface Digraph {
    int cardinality();

    boolean hasArc(int from, int to);
}
