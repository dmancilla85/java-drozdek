package org.drozdek.graphs;

@FunctionalInterface
public interface Heuristic {
    int estimate(int source, int target);
}
