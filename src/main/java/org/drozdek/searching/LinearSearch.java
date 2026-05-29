package org.drozdek.searching;

/// Linear Search algorithm.
///
/// Sequentially scans every element until the target is found or the end is
/// reached. Works on both sorted and unsorted data.
///
/// Complexity: O(n)
/// Memory: O(1)
public final class LinearSearch {

    private LinearSearch() {
        // do nothing
    }

    /// Searches for a target value using Linear Search.
    ///
    /// @param array  Array of integers (sorted or unsorted)
    /// @param target Value to search for
    /// @return Index of the target if found, -1 otherwise
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
