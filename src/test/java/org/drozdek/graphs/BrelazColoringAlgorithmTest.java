package org.drozdek.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.drozdek.graphs.algorithms.BrelazColoringAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BrelazColoringAlgorithmTest {

    @Test
    @DisplayName("Colors a bipartite graph with two colors")
    void color_bipartite() {
        boolean[][] adjacency = {
            {false, true, true, false},
            {true, false, false, true},
            {true, false, false, true},
            {false, true, true, false}
        };
        int[] colors = BrelazColoringAlgorithm.color(adjacency);
        assertValidColoring(adjacency, colors);
        assertTrue(maxColor(colors) <= 2);
    }

    @Test
    @DisplayName("Colors a complete graph K4 with four colors")
    void color_k4() {
        boolean[][] adjacency = {{false, true, true, true},
            {true, false, true, true},
            {true, true, false, true},
            {true, true, true, false}};
        int[] colors = BrelazColoringAlgorithm.color(adjacency);
        assertValidColoring(adjacency, colors);
        assertEquals(4, maxColor(colors));
    }

    @Test
    @DisplayName("Colors a complete graph K3 with three colors")
    void color_k3() {
        boolean[][] adjacency = {{false, true, true},
            {true, false, true},
            {true, true, false}};
        int[] colors = BrelazColoringAlgorithm.color(adjacency);
        assertValidColoring(adjacency, colors);
        assertEquals(3, maxColor(colors));
    }

    @Test
    @DisplayName("Isolated vertices receive color one")
    void color_isolated() {
        boolean[][] adjacency = {{false, false}, {false, false}};
        int[] colors = BrelazColoringAlgorithm.color(adjacency);
        assertEquals(1, colors[0]);
        assertEquals(1, colors[1]);
    }

    private static void assertValidColoring(boolean[][] adjacency, int[] colors) {
        for (int i = 0; i < adjacency.length; i++) {
            assertTrue(colors[i] >= 1);
            for (int j = 0; j < adjacency.length; j++) {
                if (adjacency[i][j]) {
                    assertTrue(colors[i] != colors[j], "adjacent vertices differ");
                }
            }
        }
    }

    private static int maxColor(int[] colors) {
        int max = 0;
        for (int c : colors) {
            max = Math.max(max, c);
        }
        return max;
    }
}
