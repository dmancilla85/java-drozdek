package org.drozdek.recursion.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MazeSolverTest {

    @Test
    @DisplayName("Finds a path through a solvable maze")
    void solve_solvable() {
        boolean[][] maze = {
            {true, true, true, false},
            {false, true, false, false},
            {true, true, true, true}
        };
        List<int[]> path = MazeSolver.solve(maze);
        assertFalse(path.isEmpty());
        assertEquals(0, path.get(0)[0]);
        assertEquals(0, path.get(0)[1]);
        int[] last = path.get(path.size() - 1);
        assertEquals(2, last[0]);
        assertEquals(3, last[1]);
    }

    @Test
    @DisplayName("Returns empty when the start cell is blocked")
    void solve_startBlocked() {
        boolean[][] maze = {
            {false, true},
            {true, true}
        };
        assertTrue(MazeSolver.solve(maze).isEmpty());
    }

    @Test
    @DisplayName("Returns empty when no path exists")
    void solve_noPath() {
        boolean[][] maze = {
            {true, true},
            {false, false}
        };
        assertTrue(MazeSolver.solve(maze).isEmpty());
    }

    @Test
    @DisplayName("A single passable cell is trivially reachable")
    void solve_singleCell() {
        boolean[][] maze = {{true}};
        List<int[]> path = MazeSolver.solve(maze);
        assertEquals(1, path.size());
    }
}
