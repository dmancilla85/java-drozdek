package org.drozdek.searching;

/**
 * Linear Search algorithm.
 *
 * Sequentially scans every element until the target is found or the end is
 * reached. Works on both sorted and unsorted data.
 *
 * <p><b>Real-world use case:</b> Scanning an unsorted list of transactions
 * to find the first occurrence of a specific account number or locating a
 * defective item in a small batch of products.
 *
 * Time complexity: O(n)
 * Memory complexity: O(1)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Linear_search">Linear search (Wikipedia)</a>
 */
public final class LinearSearch {

    private LinearSearch() {
        // do nothing
    }

    /**
     * Searches for a target value using Linear Search.
     *
     * @param array  Array of integers (sorted or unsorted)
     * @param target Value to search for
     * @return Index of the target if found, -1 otherwise
     */
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
