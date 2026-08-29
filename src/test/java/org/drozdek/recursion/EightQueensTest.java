package org.drozdek.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EightQueensTest {

    @Test
    @DisplayName("Standard 8x8 board has 92 solutions")
    void solveAll_eightQueens() {
        assertEquals(92, EightQueens.solveAll(8).size());
    }

    @Test
    @DisplayName("4x4 board has 2 solutions")
    void solveAll_fourQueens() {
        assertEquals(2, EightQueens.solveAll(4).size());
    }

    @Test
    @DisplayName("1x1 board has a single solution")
    void solveAll_single() {
        assertEquals(1, EightQueens.solveAll(1).size());
    }

    @Test
    @DisplayName("Each solution places one queen per column")
    void solveAll_queensPerColumn() {
        for (int[] solution : EightQueens.solveAll(6)) {
            assertEquals(6, solution.length);
        }
    }
}
