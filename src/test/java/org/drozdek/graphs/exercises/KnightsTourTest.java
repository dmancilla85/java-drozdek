package org.drozdek.graphs.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightsTourTest {

    @Test
    @DisplayName("Finds a full tour on a 5x5 board")
    void findTour_five() {
        assertEquals(25, KnightsTour.findTour(5).size());
    }

    @Test
    @DisplayName("Finds a full tour on a 6x6 board")
    void findTour_six() {
        assertEquals(36, KnightsTour.findTour(6).size());
    }

    @Test
    @DisplayName("Finds a full tour on an 8x8 board")
    void findTour_eight() {
        assertEquals(64, KnightsTour.findTour(8).size());
    }

    @Test
    @DisplayName("Every square of the tour is visited exactly once")
    void findTour_distinctCells() {
        List<int[]> tour = KnightsTour.findTour(6);
        Set<String> cells = new HashSet<>();
        for (int[] cell : tour) {
            assertTrue(cells.add(cell[0] + "," + cell[1]), "no duplicate cell");
            assertTrue(cell[0] >= 0 && cell[0] < 6);
            assertTrue(cell[1] >= 0 && cell[1] < 6);
        }
        assertEquals(36, cells.size());
    }

    @Test
    @DisplayName("Boards smaller than five return no tour")
    void findTour_tooSmall() {
        assertTrue(KnightsTour.findTour(4).isEmpty());
    }
}
