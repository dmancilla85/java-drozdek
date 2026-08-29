package org.drozdek.searching.applications;

import org.drozdek.searching.SentinelLinearSearch;
import org.drozdek.searching.TernarySearch;

/// Resolves records using ternary partition search on sorted data and a
/// sentinel-bounded linear scan as a fallback.
///
/// Ternary search splits the sorted range into three equal parts with two
/// midpoints, discarding two of the three regions at every step. When the data
/// turns out to be unsorted, a sentinel linear search performs a bounds-check-free
/// scan.
///
/// **Real-world use case:** Resolving identifiers in large sorted tables, with
/// a fast fallback path for dirty or unsorted in-memory batches.
///
/// Complexity Analysis:
/// Time Complexity: O(log₃ n) for ternary search, O(n) for the sentinel scan
/// Auxiliary Space: O(log n) recursion for ternary search
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 2.
///
/// @see TernarySearch
/// @see SentinelLinearSearch
public final class TriSectionLookup {

    private TriSectionLookup() {
        // do nothing
    }

    /// Looks up a value in a sorted array using ternary search.
    ///
    /// @param array  sorted array of keys
    /// @param target key to find
    /// @return index of the key, or -1 if absent
    public static int findInSorted(int[] array, int target) {
        return TernarySearch.ternarySearch(array, 0, array.length - 1, target);
    }

    /// Looks up a value in an (possibly unsorted) array using sentinel linear search.
    ///
    /// @param array  array of keys
    /// @param target key to find
    /// @return index of the key, or -1 if absent
    public static int findInAny(int[] array, int target) {
        if (array.length == 0) {
            return -1;
        }
        return SentinelLinearSearch.sentinelLinearSearch(array.clone(), target);
    }
}
