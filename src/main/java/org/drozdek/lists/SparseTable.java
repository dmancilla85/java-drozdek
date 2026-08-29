package org.drozdek.lists;

import java.util.ArrayList;
import java.util.List;

/// Sparse table for O(1) range queries over an immutable array.
///
/// A sparse table precomputes results for intervals of power-of-two lengths.
/// Any range can then be answered by overlapping two precomputed intervals,
/// making each query constant time at the cost of O(n log n) space. It is
/// most commonly used for idempotent operations such as range minimum/maximum
/// queries.
///
/// **Real-world use case:** Static-input range-minimum/maximum queries in
/// competitive programming and database range summaries over frozen data.
///
/// Complexity Analysis:
/// Time Complexity: O(n log n) build, O(1) per range query
/// Auxiliary Space: O(n log n)
///
/// Bibliography:
///
/// - Sparse table. *Wikipedia*. https://en.wikipedia.org/wiki/Sparse_table
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 3.
public class SparseTable {

    private final List<List<Integer>> table;

    /// Builds a sparse table for the given array, returning range minimums.
    ///
    /// @param data the source array (must be immutable after construction)
    public SparseTable(int[] data) {
        int n = data.length;
        int levels = 32 - Integer.numberOfLeadingZeros(Math.max(n, 1));
        table = new ArrayList<>(levels);
        List<Integer> current = new ArrayList<>(n);
        for (int value : data) {
            current.add(value);
        }
        table.add(current);
        for (int level = 1; (1 << level) <= n; level++) {
            List<Integer> previous = table.get(level - 1);
            List<Integer> next = new ArrayList<>();
            int span = 1 << (level - 1);
            for (int i = 0; i + (1 << level) <= n; i++) {
                next.add(Math.min(previous.get(i), previous.get(i + span)));
            }
            table.add(next);
        }
    }

    /// Returns the minimum value in the inclusive range {@code [left, right]}.
    ///
    /// @param left  start index (inclusive)
    /// @param right end index (inclusive)
    /// @return the minimum value over the range
    public int rangeMinimum(int left, int right) {
        int length = right - left + 1;
        int level = 31 - Integer.numberOfLeadingZeros(length);
        int span = 1 << level;
        int first = table.get(level).get(left);
        int second = table.get(level).get(right - span + 1);
        return Math.min(first, second);
    }
}
