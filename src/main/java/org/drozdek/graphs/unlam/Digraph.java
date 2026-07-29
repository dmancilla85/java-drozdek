package org.drozdek.graphs.unlam;

public interface Digraph {
    int cardinality();

    boolean hasArc(int from, int to);
}
