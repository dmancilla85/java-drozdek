package org.drozdek.commons;

import java.security.SecureRandom;

/// Shared array helpers: maximum scan, element swap, sortedness check,
/// console printing, and random array generation.
///
/// **Real-world use case:** Test-data generation for sorting and searching
/// benchmarks plus small utility needs across the algorithm packages.
///
/// Complexity Analysis:
/// Time Complexity: O(n) per scan operation
/// Auxiliary Space: O(n) for generated arrays, O(1) otherwise
///
public final class ArrayUtils {

    private static final SecureRandom r = new SecureRandom();

    private ArrayUtils() {
        // do nothing
    }

    /// Scans the array and returns its maximum value.
    ///
    /// Complexity: O(n).
    ///
    /// @param array non-empty array to scan
    /// @return the largest element found
    public static int getMaxValue(int[] array) {
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    /// Swaps two elements of the array in place.
    ///
    /// @param array array holding the elements
    /// @param i     index of the first element
    /// @param j     index of the second element
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /// Checks whether the array is sorted in ascending order.
    ///
    /// Complexity: O(n).
    ///
    /// @param array array to inspect
    /// @return true when every element is less than or equal to its
    ///         successor
    public static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) return false;
        }
        return true;
    }

    /// Prints the array elements space-separated through LoggerService.
    ///
    /// @param array array to print
    public static void printArray(int[] array) {

        StringBuilder msg = new StringBuilder();

        for (int element : array) {
            msg.append(element).append(" ");
        }

        LoggerService.logInfo(msg.toString());
    }

    /// Generates an integer array of length n filled with random
    /// values.
    ///
    /// @param n        requested length; non-positive yields an empty
    ///                 array
    /// @param naturals true restricts values to [1, 999], false allows
    ///                 [-1000, 999]
    /// @return the generated array
    public static int[] randomIntegerArray(int n, boolean naturals) {
        if (n <= 0) {
            return new int[0];
        }

        int[] array = new int[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = naturals ? r.nextInt(1, 1000) : r.nextInt(-1000, 1000);
        }

        return array;
    }
}
