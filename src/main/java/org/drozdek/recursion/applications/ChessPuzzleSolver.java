package org.drozdek.recursion.applications;

import java.util.List;
import org.drozdek.recursion.EightQueens;

/// N-queens puzzle solver exposing solution counts and boards.
///
/// The recursive eight-queens backtracking algorithm enumerates every valid
/// placement of non-attacking queens on an N x N board; this service shells
/// out to it to report the total number of distinct solutions.
///
/// **Real-world use case:** Benchmarking constraint-satisfaction search and a
/// teaching aid for backtracking over N-queens-style scheduling problems.
///
/// Complexity Analysis:
/// Time Complexity: O(n!) worst case
/// Auxiliary Space: O(n) for the board and recursion stack
///
/// Bibliography:
///
/// - Eight queens puzzle. *Wikipedia*. https://en.wikipedia.org/wiki/Eight_queens_puzzle
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
///
/// @see EightQueens
public final class ChessPuzzleSolver {

    private ChessPuzzleSolver() {
        // do nothing
    }

    /// Returns the number of distinct solutions for an N-queens board.
    ///
    /// @param boardSize number of rows and columns
    /// @return count of valid queen placements
    public static int solutionCount(int boardSize) {
        return EightQueens.solveAll(boardSize).size();
    }

    /// Returns every solution for an N-queens board.
    ///
    /// @param boardSize number of rows and columns
    /// @return list of placements, each a row position per column
    public static List<int[]> solutions(int boardSize) {
        return EightQueens.solveAll(boardSize);
    }
}
