package org.drozdek.searching;

/// Jump Search algorithm.
///
/// Divides the sorted array into blocks of size √n and jumps ahead by one
/// block at a time until the target is bracketed, then performs a linear scan
/// within that block. A middle-ground between linear search and binary search.
///
/// **Real-world use case:** Searching on data stored in tape drives or
/// other sequential-access media where jumping forward is cheap but rewinding
/// is expensive.
///
/// Complexity Analysis:
/// Time Complexity: O(√n)
/// Auxiliary Space: O(1)
///
/// @see <a href="https://en.wikipedia.org/wiki/Jump_search">Jump search (Wikipedia)</a>
public final class JumpSearch {

    private JumpSearch() {
        // do nothing
    }

    /// Searches for a target value in a sorted array using Jump Search.
    ///
    /// @param array  Sorted array of integers
    /// @param target Value to search for
    /// @return Index of the target if found, -1 otherwise
    public static int jumpSearch(int[] array, int target) {
        int n = array.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;

        while (array[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) {
                return -1;
            }
        }

        while (array[prev] < target) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }

        if (array[prev] == target) {
            return prev;
        }

        return -1;
    }
}
