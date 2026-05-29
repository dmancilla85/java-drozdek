package org.drozdek.searching;

/// Fibonacci Search algorithm.
///
/// Uses Fibonacci numbers to divide the array into non-uniform partitions.
/// Avoids division operations (uses only addition/subtraction), which can be
/// more efficient on some hardware than binary or ternary search.
///
/// Complexity: O(log n)
/// Memory: O(1)
public final class FibonacciSearch {

    private FibonacciSearch() {
        // do nothing
    }

    /// Searches for a target value in a sorted array using Fibonacci Search.
    ///
    /// @param array  Sorted array of integers
    /// @param target Value to search for
    /// @return Index of the target if found, -1 otherwise
    public static int fibonacciSearch(int[] array, int target) {
        int n = array.length;

        int fibM2 = 0;
        int fibM1 = 1;
        int fibM = fibM2 + fibM1;

        while (fibM < n) {
            fibM2 = fibM1;
            fibM1 = fibM;
            fibM = fibM2 + fibM1;
        }

        int offset = -1;

        while (fibM > 1) {
            int i = Math.min(offset + fibM2, n - 1);

            if (array[i] < target) {
                fibM = fibM1;
                fibM1 = fibM2;
                fibM2 = fibM - fibM1;
                offset = i;
            } else if (array[i] > target) {
                fibM = fibM2;
                fibM1 = fibM1 - fibM2;
                fibM2 = fibM - fibM1;
            } else {
                return i;
            }
        }

        if (fibM1 == 1 && offset + 1 < n && array[offset + 1] == target) {
            return offset + 1;
        }

        return -1;
    }
}
