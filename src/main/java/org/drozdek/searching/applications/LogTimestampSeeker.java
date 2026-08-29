package org.drozdek.searching.applications;

import org.drozdek.searching.BinarySearch;
import org.drozdek.searching.ExponentialSearch;

/// Locates the position of a timestamped log entry using exponential and
/// binary search.
///
/// In append-only log files the total number of records is often unknown
/// up front. Exponential search rapidly widens the candidate range and then
/// binary search homes in on the exact record, which is far faster than a
/// linear scan for large logs.
///
/// **Real-world use case:** Querying multi-gigabyte sorted log or audit
/// trails by record number, and finding the insertion slot for a new entry
/// in an append-only time-series store.
///
/// Complexity Analysis:
/// Time Complexity: O(log n)
/// Auxiliary Space: O(log n) for the recursive binary search
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 2.
///
/// @see ExponentialSearch
/// @see BinarySearch
public final class LogTimestampSeeker {

    private LogTimestampSeeker() {
        // do nothing
    }

    /// Finds the index of a log entry with the given record number.
    ///
    /// @param entries sorted list of record numbers
    /// @param target  record number to find
    /// @return index of the entry, or -1 if absent
    public static int findEntry(long[] entries, long target) {
        if (entries.length == 0 || target < entries[0] || target > entries[entries.length - 1]) {
            return -1;
        }
        int[] intEntries = new int[entries.length];
        for (int i = 0; i < entries.length; i++) {
            intEntries[i] = (int) entries[i];
        }
        int targetInt = (int) target;
        int last = Math.max(0, intEntries.length - 1);
        int upper = findUpperBound(intEntries, targetInt);
        return BinarySearch.binarySearch(intEntries, upper / 2, Math.min(upper, last), targetInt);
    }

    private static int findUpperBound(int[] array, int target) {
        if (array[0] == target) {
            return 0;
        }
        int i = 1;
        while (i < array.length && array[i] <= target) {
            i *= 2;
        }
        return i;
    }

    /// Finds the index of an entry using the packaged exponential search.
    ///
    /// @param entries sorted array of integers
    /// @param target  value to find
    /// @return index of the entry, or -1 if absent
    public static int findEntryInt(int[] entries, int target) {
        return ExponentialSearch.exponentialSearch(entries, target);
    }
}
