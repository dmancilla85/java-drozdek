package org.drozdek.sorting;

/// Insertion Sort algorithm.
///
/// Builds the sorted array one element at a time by repeatedly taking the
/// next unsorted element and inserting it into its correct position among
/// the already-sorted elements. Elements are shifted right to make room.
/// Performs very well on small or nearly-sorted datasets.
///
/// **Real-world use case:** Sorting a hand of playing cards, or
/// processing small real-time data streams where new items are added
/// incrementally to an already-sorted list.
///
/// Complexity Analysis:
/// Time Complexity: O(n²)
/// Auxiliary Space: O(1)
/// Type: Stable
///
/// @see <a href="https://en.wikipedia.org/wiki/Insertion_sort">Insertion sort (Wikipedia)</a>
public final class InsertionSort {

    private InsertionSort() {
        // do nothing
    }

    /// Sorts an array in-place using Insertion Sort.
    ///
    /// @param array Array of integers to sort
    public static void insertionSort(int[] array) {
        int n = array.length;

        // starts with the second element (i = 1)
        for (int i = 1; i < n; i++) {

            // save the current value as the key
            int key = array[i];

            // j is the previous element index
            int j = i - 1;

            // if the previous element (j) is major than the key, move j-element to the right
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            // insert the key in the j-position found
            array[j + 1] = key;
        }
    }
}
