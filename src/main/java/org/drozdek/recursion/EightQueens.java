package org.drozdek.recursion;

import java.util.ArrayList;
import java.util.List;

/// Solves the eight-queens puzzle using recursive backtracking.
///
/// Queens are placed one per column, recursively trying each row and
/// backtracking whenever the current placement conflicts with previously
/// placed queens. The search explores the board row by row and reports every
/// valid arrangement of eight non-attacking queens.
///
/// **Real-world use case:** A canonical demonstration of backtracking used to
/// teach constraint satisfaction; analogous to N-queens scheduling problems.
///
/// Complexity Analysis:
/// Time Complexity: O(n!) worst case for an n x n board
/// Auxiliary Space: O(n) for the board and recursion stack
///
/// Bibliography:
///
/// - Eight queens puzzle. *Wikipedia*. https://en.wikipedia.org/wiki/Eight_queens_puzzle
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
public final class EightQueens {

    private EightQueens() {
        // do nothing
    }

    /// Returns every solution for a board of the given size.
    ///
    /// Each solution is a list of column positions in row order (the row in
    /// which the queen in each column is placed).
    ///
    /// @param boardSize number of rows and columns
    /// @return list of solutions, each a placement per column
    public static List<int[]> solveAll(int boardSize) {
        List<int[]> solutions = new ArrayList<>();
        int[] columnForRow = new int[boardSize];
        placeQueens(boardSize, 0, columnForRow, solutions);
        return solutions;
    }

    private static void placeQueens(int boardSize, int column, int[] columnForRow, List<int[]> solutions) {
        if (column == boardSize) {
            solutions.add(columnForRow.clone());
            return;
        }
        for (int row = 0; row < boardSize; row++) {
            columnForRow[column] = row;
            if (isSafe(columnForRow, column)) {
                placeQueens(boardSize, column + 1, columnForRow, solutions);
            }
        }
    }

    private static boolean isSafe(int[] columnForRow, int column) {
        for (int previousColumn = 0; previousColumn < column; previousColumn++) {
            int row = columnForRow[previousColumn];
            int currentRow = columnForRow[column];
            if (row == currentRow || Math.abs(row - currentRow) == column - previousColumn) {
                return false;
            }
        }
        return true;
    }
}
