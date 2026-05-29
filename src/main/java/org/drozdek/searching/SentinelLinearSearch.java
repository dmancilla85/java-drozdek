package org.drozdek.searching;

/// Sentinel Linear Search algorithm.
///
/// An optimization of linear search that places a copy of the target (the
/// "sentinel") at the end of the array before scanning. This eliminates the
/// bounds check (`i < array.length`) in each loop iteration, roughly doubling
/// speed. The original last element is restored before returning.
///
/// Complexity: O(n)
/// Memory: O(1)
public final class SentinelLinearSearch {

    private SentinelLinearSearch() {
        // do nothing
    }

    /// Searches for a target value using Sentinel Linear Search.
    ///
    /// Temporarily places the target at the end of the array to avoid bounds
    /// checking during the scan. The original last element is restored before
    /// the method returns.
    ///
    /// @param array  Array of integers (sorted or unsorted)
    /// @param target Value to search for
    /// @return Index of the target if found, -1 otherwise
    public static int sentinelLinearSearch(int[] array, int target) {
        int n = array.length;
        int last = array[n - 1];
        array[n - 1] = target;

        int i = 0;
        while (array[i] != target) {
            i++;
        }

        array[n - 1] = last;

        if (i < n - 1 || last == target) {
            return i;
        }
        return -1;
    }
}
