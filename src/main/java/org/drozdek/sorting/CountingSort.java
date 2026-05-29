package org.drozdek.sorting;

import org.drozdek.commons.ArrayUtils;

public final class CountingSort {

    private CountingSort() {
        // do nothing
    }

    // Complexity: O(n+k)
    // Memory: O(n+k)
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
