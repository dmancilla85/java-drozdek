package org.drozdek.searching;

/// Ternary Search algorithm.
///
/// A divide-and-conquer algorithm that splits the sorted array into three equal
/// parts using two midpoints, then recursively searches the relevant one-third
/// interval. Although it makes more comparisons than binary search per level,
/// it divides the space into smaller portions.
///
/// **Real-world use case:** Finding the maximum of a unimodal function
/// in numerical optimization (e.g., locating the peak signal strength in
/// antenna calibration).
///
/// Complexity Analysis:
/// Time Complexity: O(log₃ n)
/// Auxiliary Space: O(log n) for the recursive stack
///
/// @see <a href="https://en.wikipedia.org/wiki/Ternary_search">Ternary search (Wikipedia)</a>
public final class TernarySearch {

    private TernarySearch() {
        // do nothing
    }

    /// Searches for a target value in a sorted array using Ternary Search.
    ///
    /// @param array  Sorted array of integers
    /// @param left   Left boundary of the search interval
    /// @param right  Right boundary of the search interval
    /// @param target Value to search for
    /// @return Index of the target if found, -1 otherwise
    public static int ternarySearch(int[] array, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid1 = left + (right - left) / 3;
        int mid2 = right - (right - left) / 3;

        if (target == array[mid1]) {
            return mid1;
        }
        if (target == array[mid2]) {
            return mid2;
        }

        if (target < array[mid1]) {
            return ternarySearch(array, left, mid1 - 1, target);
        } else if (target > array[mid2]) {
            return ternarySearch(array, mid2 + 1, right, target);
        } else {
            return ternarySearch(array, mid1 + 1, mid2 - 1, target);
        }
    }
}
