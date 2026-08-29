package org.drozdek.recursion.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPuzzleSolverTest {

    @Test
    @DisplayName("Four-queens board has exactly two solutions")
    void solutionCount_fourQueens() {
        assertEquals(2, ChessPuzzleSolver.solutionCount(4));
    }

    @Test
    @DisplayName("Eight-queens board has the classic ninety-two solutions")
    void solutionCount_eightQueens() {
        assertEquals(92, ChessPuzzleSolver.solutionCount(8));
    }
}
