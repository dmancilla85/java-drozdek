package org.drozdek.graphs.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CircuitBoardDrillingTest {

    @Test
    @DisplayName("Finds a closed route covering every edge of a triangle")
    void drillRoute_triangle() {
        boolean[][] adjacency = {
            {false, true, true},
            {true, false, true},
            {true, true, false},
        };
        List<Integer> circuit = CircuitBoardDrilling.drillRoute(adjacency);
        assertEquals(4, circuit.size());
        assertEquals(circuit.get(0), circuit.get(circuit.size() - 1));
        Set<Integer> visited = new HashSet<>(circuit);
        assertEquals(Set.of(0, 1, 2), visited);
    }

    @Test
    @DisplayName("Returns an empty route when no Eulerian circuit exists")
    void drillRoute_noCircuit() {
        boolean[][] adjacency = {
            {false, true, false},
            {true, false, true},
            {false, true, false},
        };
        assertTrue(CircuitBoardDrilling.drillRoute(adjacency).isEmpty());
    }
}
