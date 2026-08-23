package org.drozdek.searching;

/// Interpolation Search algorithm.
///
/// An improved variant of binary search that estimates the probe position using
/// linear interpolation on the key values rather than always splitting at the
/// midpoint. Performs best on uniformly distributed sorted data.
///
/// **Real-world use case:** Looking up a name in a sorted phone book or
/// querying a ZIP code from a uniformly distributed address database.
///
/// Complexity Analysis:
/// Time Complexity: O(log log n) average, O(n) worst case
/// Auxiliary Space: O(1)
///
/// @see <a href="https://en.wikipedia.org/wiki/Interpolation_search">Interpolation search (Wikipedia)</a>
public final class InterpolationSearch {

    private InterpolationSearch() {
        // do nothing
    }

    /// Searches for a target value in a sorted array using Interpolation Search.
    ///
    /// @param array  Sorted array of integers
    /// @param left   Left boundary of the search interval
    /// @param right  Right boundary of the search interval
    /// @param target Value to search for
    /// @return Index of the target if found, -1 otherwise
    public static int interpolationSearch(int[] array, int left, int right, int target) {
        int result = -1;

        while (left <= right && target >= array[left] && target <= array[right] && result == -1) {
            if (left == right) {
                if (array[left] == target) {
                    result = left;
                }
                break;
            }

            int pos = left + (target - array[left]) * (right - left)
                    / (array[right] - array[left]);

            if (array[pos] == target) {
                result = pos;
            } else if (array[pos] < target) {
                left = pos + 1;
            } else {
                right = pos - 1;
            }
        }

        return result;
    }
}
