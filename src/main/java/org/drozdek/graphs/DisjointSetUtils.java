package org.drozdek.graphs;

public final class DisjointSetUtils {
    private DisjointSetUtils() {
    }

    public static int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
