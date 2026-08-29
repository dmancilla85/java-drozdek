package org.drozdek.sorting.applications;

import org.drozdek.sorting.BubbleSort;
import org.drozdek.sorting.BucketSort;
import org.drozdek.sorting.CocktailShakerSort;
import org.drozdek.sorting.CountingSort;
import org.drozdek.sorting.InsertionSort;
import org.drozdek.sorting.SelectionSort;
import org.drozdek.sorting.ShellSort;

/// Report-data sorter that dispatches a dataset to a chosen simple sort.
///
/// Each elementary sorting algorithm — bubble, cocktail shaker, insertion,
/// selection, shell, counting, and bucket — is exposed behind one selector so
/// comparable reports can be produced with different ordering strategies.
///
/// **Real-world use case:** Analytics tooling that lets users pick a sort
/// strategy for small report datasets, or comparing CPU behaviour of
/// elementary sorts on the same input.
///
/// Complexity Analysis:
/// Time Complexity: algorithm-dependent (O(n^2) worst case for the exchange
///                  sorts, O(n + k) for counting sort)
/// Auxiliary Space: O(1) for the in-place sorts, O(n + k) for counting sort
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 9.
public final class ReportSorter {

    /// Supported elementary sort strategies.
    public enum Algorithm {
        /// Bubble sort.
        BUBBLE,
        /// Cocktail shaker sort.
        COCKTAIL,
        /// Insertion sort.
        INSERTION,
        /// Selection sort.
        SELECTION,
        /// Shell sort.
        SHELL,
        /// Counting sort (non-negative integers).
        COUNTING,
        /// Bucket sort.
        BUCKET
    }

    private ReportSorter() {
        // do nothing
    }

    /// Returns a sorted copy of the given data using the chosen algorithm.
    ///
    /// @param data      integers to sort (non-negative for {@code COUNTING})
    /// @param algorithm the algorithm to apply
    /// @return a new, sorted array
    public static int[] sort(int[] data, Algorithm algorithm) {
        int[] copy = data.clone();
        switch (algorithm) {
            case BUBBLE -> BubbleSort.bubbleSort(copy);
            case COCKTAIL -> CocktailShakerSort.cocktailShakerSort(copy);
            case INSERTION -> InsertionSort.insertionSort(copy);
            case SELECTION -> SelectionSort.selectionSort(copy);
            case SHELL -> ShellSort.shellSort(copy);
            case COUNTING -> CountingSort.countingSort(copy);
            case BUCKET -> BucketSort.bucketSort(copy, 4);
            default -> throw new IllegalArgumentException("Unknown algorithm " + algorithm);
        }
        return copy;
    }
}
