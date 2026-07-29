package org.drozdek.graphs.unlam;

@FunctionalInterface
public interface Heuristic {
    int estimate(int source, int target);
}
