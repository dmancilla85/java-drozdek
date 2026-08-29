package org.drozdek.recursion.applications;

import org.drozdek.recursion.TowersOfHanoi;

/// Backup tape rotation planner based on the Towers of Hanoi pattern.
///
/// The Tower of Hanoi rotation scheme assigns daily backup media to tapes
/// following the same peg-moving pattern, so low-frequency tapes are used far
/// less often than high-frequency ones. This service reports the total moves
/// (tape switches) and the closed-form cycle length for a given tape count.
///
/// **Real-world use case:** Grandfather-father-son and Tower of Hanoi backup
/// rotation strategies in enterprise storage administration.
///
/// Complexity Analysis:
/// Time Complexity: O(2^n) to simulate the rotation
/// Auxiliary Space: O(n) for the recursion stack
///
/// Bibliography:
///
/// - Tower of Hanoi. *Wikipedia*. https://en.wikipedia.org/wiki/Tower_of_Hanoi
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
///
/// @see TowersOfHanoi
public final class HanoiBackupRotation {

    private HanoiBackupRotation() {
        // do nothing
    }

    /// Simulates a full backup cycle and returns the number of rotations.
    ///
    /// @param tapes number of tapes participating in the rotation
    /// @return total rotate operations performed
    public static long performCycle(int tapes) {
        return TowersOfHanoi.solve(tapes, 'A', 'C', 'B');
    }

    /// Returns the closed-form number of rotations for a tape count.
    ///
    /// @param tapes number of tapes
    /// @return 2^tapes - 1
    public static long cycleLength(int tapes) {
        return TowersOfHanoi.minimumMoves(tapes);
    }
}
