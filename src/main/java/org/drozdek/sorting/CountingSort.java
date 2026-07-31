package org.drozdek.sorting;

import org.drozdek.commons.ArrayUtils;

/**
 * Counting Sort algorithm.
 *
 * An integer sorting algorithm that counts the frequency of each distinct
 * value using an auxiliary count array, then computes prefix sums to
 * determine the final position of each element. Only works when the range
 * of possible input values (k) is reasonably small and known in advance.
 *
 * <p><b>Real-world use case:</b> Sorting the ages of a large population, or
 * ranking items by a small integer key such as priority levels or
 * categorical labels.
 *
 * Time complexity: O(n + k) where k is the range of input values
 * Memory complexity: O(n + k)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Counting_sort">Counting sort (Wikipedia)</a>
 */
public final class CountingSort {

    private CountingSort() {
        // do nothing
    }

    /**
     * Sorts an array in-place using Counting Sort.
     *
     * @param array Array of non-negative integers to sort
     */
    public static void countingSort(int[] array) {
        int n = array.length;

        // find the maximum array value
        int max = ArrayUtils.getMaxValue(array);

        // Count the frequency of each element
        int[] count = new int[max + 1];

        for (int j : array) {
            count[j]++;
        }

        // Accumulate frequencies to determine final positions
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Build the sorted output array
        int[] output = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            // Place each element at its correct sorted position
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        // Copy output back to the original array
        System.arraycopy(output, 0, array, 0, n);
    }
}
