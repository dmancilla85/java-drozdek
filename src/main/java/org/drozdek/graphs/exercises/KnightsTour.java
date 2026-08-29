package org.drozdek.graphs.exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/// Solves the Knight's-tour problem using Warnsdorff's heuristic.
///
/// A knight's tour visits every square of an n x n chessboard exactly once.
/// The classic backtracking search is guided here by Warnsdorff's rule: at
/// each move, jump to the square with the fewest onward moves, which keeps
/// the search away from dead ends and runs in linear time for boards of
/// reasonable size. The tour is an example of a Hamiltonian path on the
/// knight-move graph.
///
/// **Real-world use case:** A classic exercise in combinatorial search and
/// heuristics, used to introduce Hamiltonian paths and greedy search.
///
/// Complexity Analysis:
/// Time Complexity: O(n^2) with Warnsdorff's heuristic
/// Auxiliary Space: O(n^2) for the visited board
///
/// Bibliography:
///
/// - Knight's tour. *Wikipedia*. https://en.wikipedia.org/wiki/Knight%27s_tour
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
public final class KnightsTour {

    private static final int[] ROW_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] COL_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};

    private KnightsTour() {
        // do nothing
    }

    /// Finds a full knight's tour on an {@code n x n} board starting from
    /// {@code (0, 0)}.
    ///
    /// @param n board size
    /// @return list of {@code [row, col]} positions in visit order, or an
    ///         empty list if no tour is found
    public static List<int[]> findTour(int n) {
        if (n < 5) {
            return new ArrayList<>();
        }
        int[][] board = new int[n][n];
        List<int[]> tour = new ArrayList<>();
        int[] first = {0, 0};
        tour.add(first);
        board[0][0] = 1;
        if (!search(n, board, tour, 1)) {
            return new ArrayList<>();
        }
        return tour;
    }

    private static boolean search(int n, int[][] board, List<int[]> tour, int step) {
        if (step == n * n) {
            return true;
        }
        int[] current = tour.get(tour.size() - 1);
        List<int[]> candidates = candidates(n, board, current[0], current[1]);
        candidates.sort(Comparator.comparingInt(m -> m[2]));
        for (int[] candidate : candidates) {
            int row = candidate[0];
            int col = candidate[1];
            board[row][col] = step + 1;
            tour.add(new int[]{row, col});
            if (search(n, board, tour, step + 1)) {
                return true;
            }
            board[row][col] = 0;
            tour.remove(tour.size() - 1);
        }
        return false;
    }

    private static List<int[]> candidates(int n, int[][] board, int row, int col) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < ROW_MOVES.length; i++) {
            int nr = row + ROW_MOVES[i];
            int nc = col + COL_MOVES[i];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == 0) {
                result.add(new int[]{nr, nc, countOnward(n, board, nr, nc)});
            }
        }
        return result;
    }

    private static int countOnward(int n, int[][] board, int row, int col) {
        int count = 0;
        for (int i = 0; i < ROW_MOVES.length; i++) {
            int nr = row + ROW_MOVES[i];
            int nc = col + COL_MOVES[i];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == 0) {
                count++;
            }
        }
        return count;
    }
}
