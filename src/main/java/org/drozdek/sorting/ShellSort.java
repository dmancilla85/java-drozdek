package org.drozdek.sorting;

/// Shell Sort algorithm.
///
/// A generalization of insertion sort that sorts elements at progressively
/// smaller gaps, allowing distant elements to move into place quickly in
/// the early passes before a final fine-grained insertion sort. The gap
/// sequence determines the overall time complexity.
///
/// **Real-world use case:** Embedded systems where code size is
/// constrained and a moderate-speed sort is needed with minimal memory
/// overhead — Shell Sort requires no recursion and no auxiliary arrays.
///
/// Complexity Analysis:
/// Time Complexity: O(n3/�) with the original gap sequence (n/2, n/4, ...)
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Shellsort. *Wikipedia*. https://en.wikipedia.org/wiki/Shellsort
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 9.
public final class ShellSort {

    private ShellSort() {
        // do nothing
    }

    /// Sorts an array in-place using Shell Sort.
    ///
    /// @param array Array of integers to sort
    public static void shellSort(int[] array) {

        int n = array.length;

        // Start with gap = half the array size, then halve each iteration
        int gap = n / 2;

        while (gap > 0) {

            // Perform a gapped insertion sort
            for (int i = gap; i < n; i++) {

                int key = array[i];
                int j = i - gap;

                // Shift elements that are greater than key to the right
                while (j >= 0 && array[j] > key) {
                    array[j + gap] = array[j];
                    j -= gap;
                }

                // Insert key at its correct position
                array[j + gap] = key;
            }
            gap /= 2;
        }
    }
}
