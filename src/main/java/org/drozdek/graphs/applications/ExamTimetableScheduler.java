package org.drozdek.graphs.applications;

import org.drozdek.graphs.algorithms.BrelazColoringAlgorithm;

/// Exam timetable scheduler using DSATUR (Brelaz) graph colouring.
///
/// Each exam is a vertex and a conflict between two exams is an edge. The
/// Brelaz saturation-degree algorithm colours the conflict graph so that no two
/// adjacent exams share a colour, and each colour maps to a distinct exam slot.
/// The number of colours used equals the number of required time slots.
///
/// **Real-world use case:** University exam scheduling, register allocation in
/// compilers, and any slot-assignment problem expressed as a conflict graph.
///
/// Complexity Analysis:
/// Time Complexity: O(V^2) for colour selection on V vertices
/// Auxiliary Space: O(V^2) for the adjacency matrix
///
/// Bibliography:
///
/// - Daniel Brélaz. *New methods to color the vertices of a graph*. Communications of the ACM, 1979.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 9.
///
/// @see BrelazColoringAlgorithm
public class ExamTimetableScheduler {

    private final int exams;
    private final boolean[][] conflicts;

    /// Creates a scheduler for a given number of exams with no conflicts yet.
    ///
    /// @param exams number of exams (vertices) to schedule
    public ExamTimetableScheduler(int exams) {
        if (exams <= 0) {
            throw new IllegalArgumentException("exams must be positive");
        }
        this.exams = exams;
        this.conflicts = new boolean[exams][exams];
    }

    /// Registers that two exams cannot be scheduled in the same slot.
    ///
    /// @param first  first exam index
    /// @param second second exam index
    public void addConflict(int first, int second) {
        conflicts[first][second] = true;
        conflicts[second][first] = true;
    }

    /// Assigns every exam a slot colour such that conflicting exams differ.
    ///
    /// @return array where the element at index i is the slot assigned to exam i
    public int[] scheduleSlots() {
        return BrelazColoringAlgorithm.color(conflicts);
    }

    /// Returns the number of slot colours required by the current conflict set.
    ///
    /// @return number of time slots needed
    public int requiredSlots() {
        int[] slots = scheduleSlots();
        int max = 0;
        for (int slot : slots) {
            if (slot > max) {
                max = slot;
            }
        }
        return max;
    }

    /// Returns the number of exams managed by this scheduler.
    ///
    /// @return the vertex count
    public int examCount() {
        return exams;
    }
}
