package org.drozdek.sorting.applications;

import java.util.List;
import org.drozdek.sorting.MergeSort;

/// Merges and sorts distributed log timestamp streams using merge sort.
///
/// Log chunks arriving from independent producers are merged into a single
/// chronologically ordered stream. An out-of-order bucket of timestamps is
/// sorted by recursive merge sort, while two already-ordered streams are fused
/// in a single linear merge pass over the split halves.
///
/// **Real-world use case:** Centralized log aggregators (ELK-style pipelines)
/// that combine timestamped records from many hosts into a time-ordered view.
///
/// Complexity Analysis:
/// Time Complexity: O(n log n) to sort, O(n) to merge two streams
/// Auxiliary Space: O(n) for scratch storage
///
/// Bibliography:
///
/// - Merge sort. *Wikipedia*. https://en.wikipedia.org/wiki/Merge_sort
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
///
/// @see MergeSort
public final class DistributedLogMerger {

    private DistributedLogMerger() {
        // do nothing
    }

    /// Sorts a bucket of unsorted timestamp entries in place.
    ///
    /// @param timestamps the timestamps to order, mutated in place
    public static void sortTimestamps(int[] timestamps) {
        MergeSort.mergeSort(timestamps, 0, timestamps.length - 1);
    }

    /// Merges two already-sorted timestamp streams into one ordered array.
    ///
    /// @param first  first ordered stream
    /// @param second second ordered stream
    /// @return combined stream in ascending order
    public static int[] mergeStreams(int[] first, int[] second) {
        if (first.length == 0) {
            return second.clone();
        }
        if (second.length == 0) {
            return first.clone();
        }
        int[] combined = new int[first.length + second.length];
        System.arraycopy(first, 0, combined, 0, first.length);
        System.arraycopy(second, 0, combined, first.length, second.length);
        MergeSort.merge(combined, 0, first.length - 1, combined.length - 1);
        return combined;
    }

    /// Merges many sorted streams into a single ascending array.
    ///
    /// @param streams list of ordered timestamp arrays
    /// @return single merged, sorted array
    public static int[] mergeMany(List<int[]> streams) {
        int total = 0;
        for (int[] stream : streams) {
            total += stream.length;
        }
        int[] result = new int[total];
        int cursor = 0;
        for (int[] stream : streams) {
            System.arraycopy(stream, 0, result, cursor, stream.length);
            cursor += stream.length;
        }
        sortTimestamps(result);
        return result;
    }
}
