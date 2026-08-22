package org.drozdek.recursion;

import org.drozdek.commons.LoggerService;

/// Solves the classic Towers of Hanoi puzzle using recursion.
///
/// **Real-world use case:** Backup rotation schemes (the Tower of Hanoi
/// rotation pattern is used for rotating backup media), elevator dispatching,
/// and teaching recursive problem decomposition.
///
/// Complexity Analysis:
/// Time Complexity: O(2^n) — two recursive calls per invocation
/// Auxiliary Space: O(n) — recursion stack depth
///
/// @see <a href="https://en.wikipedia.org/wiki/Tower_of_Hanoi">Tower of Hanoi (Wikipedia)</a>
public final class TowersOfHanoi {
    private TowersOfHanoi() {
        // do nothing
    }

    /// Solves the puzzle by printing each move to the logger.
    ///
    /// @param n      Number of disks
    /// @param from   Peg identifier for the source
    /// @param to     Peg identifier for the destination
    /// @param aux    Peg identifier for the auxiliary (spare)
    /// @param moves  One-element array to track the move counter (mutable long)
    public static void solve(int n, char from, char to, char aux, long[] moves) {
        if (n <= 0) {
            return;
        }
        if (n == 1) {
            moves[0]++;
            LoggerService.logInfo("Move disk 1 from " + from + " to " + to);
            return;
        }

        char firstLegDestination = aux;
        char firstLegBuffer = to;
        solve(n - 1, from, firstLegDestination, firstLegBuffer, moves);

        moves[0]++;
        LoggerService.logInfo("Move disk " + n + " from " + from + " to " + to);

        char secondLegOrigin = aux;
        solve(n - 1, secondLegOrigin, to, from, moves);
    }

    /// Convenience overload that initialises the move counter internally.
    ///
    /// @param n    Number of disks
    /// @param from Peg identifier for the source
    /// @param to   Peg identifier for the destination
    /// @param aux  Peg identifier for the auxiliary (spare)
    /// @return     Total number of moves performed
    public static long solve(int n, char from, char to, char aux) {
        long[] moves = {0L};
        solve(n, from, to, aux, moves);
        return moves[0];
    }

    /// Returns the minimum number of moves required for `n` disks
    /// without executing the algorithm (closed-form solution).
    ///
    /// @param n Number of disks
    /// @return  2^n - 1
    public static long minimumMoves(int n) {
        return (1L << n) - 1;
    }
}
