package org.drozdek.sorting.applications;

import org.drozdek.sorting.HeapSort;
import org.drozdek.sorting.QuickSort;

/// Computes the top-K ranking of scores using quick sort and heap sort.
///
/// A full ordering of the score array is produced with quick sort, from which
/// the K largest elements are extracted in descending order. An alternate path
/// uses heap sort to obtain the same ordering, useful when the backing data is
/// already heap-shaped.
///
/// **Real-world use case:** Leaderboards, "best of" lists, and ranking endpoints
/// that surface the highest-scoring members of a large population.
///
/// Complexity Analysis:
/// Time Complexity: O(n log n) for the sort, O(k) to extract the top K
/// Auxiliary Space: O(n) for the sorting scratch space
///
/// Bibliography:
///
/// - Quicksort. *Wikipedia*. https://en.wikipedia.org/wiki/Quicksort
/// - Heapsort. *Wikipedia*. https://en.wikipedia.org/wiki/Heapsort
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
///
/// @see QuickSort
/// @see HeapSort
public final class TopKRanker {

    private TopKRanker() {
        // do nothing
    }

    /// Returns the {@code k} largest scores in descending order.
    ///
    /// @param scores all candidate scores
    /// @param k       how many leading scores to return
    /// @return the top k scores, largest first
    public static int[] topK(int[] scores, int k) {
        int bound = Math.min(Math.max(k, 0), scores.length);
        int[] copy = scores.clone();
        QuickSort.quickSort(copy, 0, copy.length - 1);
        int[] result = new int[bound];
        for (int i = 0; i < bound; i++) {
            result[i] = copy[copy.length - 1 - i];
        }
        return result;
    }

    /// Returns the {@code k} largest scores using heap sort.
    ///
    /// @param scores all candidate scores
    /// @param k       how many leading scores to return
    /// @return the top k scores, largest first
    public static int[] topKByHeap(int[] scores, int k) {
        int bound = Math.min(Math.max(k, 0), scores.length);
        int[] copy = scores.clone();
        HeapSort.heapSort(copy);
        int[] result = new int[bound];
        for (int i = 0; i < bound; i++) {
            result[i] = copy[copy.length - 1 - i];
        }
        return result;
    }
}
