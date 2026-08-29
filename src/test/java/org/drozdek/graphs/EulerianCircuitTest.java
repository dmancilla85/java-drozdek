package org.drozdek.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EulerianCircuitTest {

    @Test
    @DisplayName("Finds a circuit in a graph with even degrees")
    void findCircuit_evenDegrees() {
        boolean[][] adjacency = {
            {false, true, true, true, true},
            {true, false, true, false, false},
            {true, true, false, false, false},
            {true, false, false, false, true},
            {true, false, false, true, false}
        };
        List<Integer> circuit = EulerianCircuit.findCircuit(adjacency);
        assertTrue(circuit.size() >= 2, "has a circuit");
        assertEquals(circuit.get(0), circuit.get(circuit.size() - 1), "closed walk");
    }

    @Test
    @DisplayName("Returns empty for a graph with an odd-degree vertex")
    void findCircuit_oddDegree() {
        boolean[][] adjacency = {
            {false, true, true},
            {true, false, false},
            {true, false, false}
        };
        assertTrue(EulerianCircuit.findCircuit(adjacency).isEmpty());
    }

    @Test
    @DisplayName("A square cycle has a circuit of length five")
    void findCircuit_cycle() {
        boolean[][] adjacency = {
            {false, true, false, true},
            {true, false, true, false},
            {false, true, false, true},
            {true, false, true, false}
        };
        List<Integer> circuit = EulerianCircuit.findCircuit(adjacency);
        assertEquals(5, circuit.size());
        assertEquals(circuit.get(0), circuit.get(circuit.size() - 1));
    }

    @Test
    @DisplayName("A triangle has a circuit of length four")
    void findCircuit_triangle() {
        boolean[][] adjacency = {
            {false, true, true},
            {true, false, true},
            {true, true, false}
        };
        List<Integer> circuit = EulerianCircuit.findCircuit(adjacency);
        assertEquals(4, circuit.size());
    }
}
