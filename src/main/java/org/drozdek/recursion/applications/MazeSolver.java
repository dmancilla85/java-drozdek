package org.drozdek.recursion.applications;

import java.util.ArrayList;
import java.util.List;

/// Recursive backtracking maze solver.
///
/// Finds a path from the top-left cell to the bottom-right cell of a grid,
/// moving only right or down, by trying each direction recursively and
/// backtracking when a move leads to a dead end or a wall. The recursion
/// implicitly performs a depth-first search over the grid.
///
/// **Real-world use case:** Pathfinding in games and robots where the grid is
/// small enough that a simple recursive search is acceptable.
///
/// Complexity Analysis:
/// Time Complexity: O(2^(n+m)) worst case, O(n*m) for the two-move variant
/// Auxiliary Space: O(n*m) for the recursion stack and visited marks
///
/// Bibliography:
///
/// - Maze generation algorithm. *Wikipedia*. https://en.wikipedia.org/wiki/Maze_generation_algorithm
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
public final class MazeSolver {

    private MazeSolver() {
        // do nothing
    }

    /// Finds a path from {@code (0,0)} to the bottom-right cell moving only
    /// right or down through passable (`true`) cells.
    ///
    /// @param maze grid where `true` marks a passable cell and `false` a wall
    /// @return list of {@code [row, col]} cells forming the path, or an empty
    ///         list when no path exists
    public static List<int[]> solve(boolean[][] maze) {
        List<int[]> path = new ArrayList<>();
        int rows = maze.length;
        int cols = rows == 0 ? 0 : maze[0].length;
        if (rows == 0 || cols == 0 || !maze[0][0]) {
            return path;
        }
        search(maze, 0, 0, rows - 1, cols - 1, path);
        return path;
    }

    private static boolean search(boolean[][] maze, int row, int col, int targetRow, int targetCol,
            List<int[]> path) {
        if (!maze[row][col]) {
            return false;
        }
        if (row == targetRow && col == targetCol) {
            path.add(new int[]{row, col});
            return true;
        }
        path.add(new int[]{row, col});
        if (row < targetRow && search(maze, row + 1, col, targetRow, targetCol, path)) {
            return true;
        }
        if (col < targetCol && search(maze, row, col + 1, targetRow, targetCol, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
}
