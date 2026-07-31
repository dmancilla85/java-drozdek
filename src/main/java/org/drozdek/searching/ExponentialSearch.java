package org.drozdek.searching;

/**
 * Exponential Search algorithm.
 *
 * Finds a range where the target may exist by repeatedly doubling the search
 * index, then performs a binary search within that range. Particularly useful
 * for unbounded or infinite-length arrays where the size is not known in
 * advance.
 *
 * <p><b>Real-world use case:</b> Searching through an unbounded log stream or
 * a very large sorted file (e.g., a multi-gigabyte sorted CSV) where the total
 * record count is unknown at the start.
 *
 * Time complexity: O(log n)
 * Memory complexity: O(1)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Exponential_search">Exponential search (Wikipedia)</a>
 */
public final class ExponentialSearch {

    private ExponentialSearch() {
        // do nothing
    }

    /**
     * Searches for a target value in a sorted array using Exponential Search.
     *
     * @param array  Sorted array of integers
     * @param target Value to search for
     * @return Index of the target if found, -1 otherwise
     */
    public static int exponentialSearch(int[] array, int target) {
        if (array[0] == target) {
            return 0;
        }

        int i = 1;
        while (i < array.length && array[i] <= target) {
            i *= 2;
        }

        return BinarySearch.binarySearch(array, i / 2, Math.min(i, array.length - 1), target);
    }
}
