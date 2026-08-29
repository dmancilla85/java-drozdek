package org.drozdek.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.drozdek.graphs.algorithms.FordFulkersonAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FordFulkersonAlgorithmTest {

    @Test
    @DisplayName("Computes the max flow of a small network")
    void compute_smallNetwork() {
        int[][] capacity = new int[4][4];
        capacity[0][1] = 3;
        capacity[0][2] = 2;
        capacity[1][2] = 1;
        capacity[1][3] = 2;
        capacity[2][3] = 3;
        FordFulkersonAlgorithm.MaxFlow result = FordFulkersonAlgorithm.compute(capacity, 0, 3);
        assertEquals(5, result.getValue());
    }

    @Test
    @DisplayName("Zero capacity graph yields zero flow")
    void compute_zeroCapacity() {
        int[][] capacity = new int[3][3];
        FordFulkersonAlgorithm.MaxFlow result = FordFulkersonAlgorithm.compute(capacity, 0, 2);
        assertEquals(0, result.getValue());
    }

    @Test
    @DisplayName("Single-edge network")
    void compute_singleEdge() {
        int[][] capacity = new int[2][2];
        capacity[0][1] = 7;
        FordFulkersonAlgorithm.MaxFlow result = FordFulkersonAlgorithm.compute(capacity, 0, 1);
        assertEquals(7, result.getValue());
    }

    @Test
    @DisplayName("Flow out of the source equals the total")
    void compute_flowBalance() {
        int[][] capacity = new int[4][4];
        capacity[0][1] = 3;
        capacity[0][2] = 2;
        capacity[1][2] = 1;
        capacity[1][3] = 2;
        capacity[2][3] = 3;
        FordFulkersonAlgorithm.MaxFlow result = FordFulkersonAlgorithm.compute(capacity, 0, 3);
        int out = 0;
        for (int j = 0; j < 4; j++) {
            out += Math.max(0, result.getFlow()[0][j]);
        }
        assertEquals(result.getValue(), out);
    }
}
