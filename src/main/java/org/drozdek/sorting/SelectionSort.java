package org.drozdek.sorting;

/**
 * Selection Sort algorithm.
 *
 * Repeatedly finds the minimum element from the unsorted portion of the
 * array and swaps it to the beginning. Builds the sorted output from left
 * to right, one element at a time. Minimizes the number of writes to O(n).
 *
 * <p><b>Real-world use case:</b> Sorting data stored in EEPROM or flash
 * memory where write operations are expensive and each swap has a wear cost.
 *
 * Time complexity: O(n²)
 * Memory complexity: O(1)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Selection_sort">Selection sort (Wikipedia)</a>
 */
public final class SelectionSort {

    private SelectionSort() {
        // do nothing
    }

    /**
     * Sorts an array in-place using Selection Sort.
     *
     * @param array Array of integers to sort
     */
    public static void selectionSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            int minElementIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[minElementIndex] > array[j]) {
                    minElementIndex = j;
                }
            }
            if (minElementIndex != i) {
                int temp = array[i];
                array[i] = array[minElementIndex];
                array[minElementIndex] = temp;
            }
        }
    }
}
