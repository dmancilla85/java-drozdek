package org.drozdek.searching;

/**
 * Binary Search algorithm.
 *
 * A divide-and-conquer algorithm that repeatedly divides the search interval
 * in half by comparing the target to the middle element. Requires a sorted
 * array as input.
 *
 * <p><b>Real-world use case:</b> Looking up a word in a printed dictionary or
 * querying a record by primary key in a sorted database index.
 *
 * Time complexity: O(log n)
 * Memory complexity: O(log n) for the recursive implementation
 *
 * @see <a href="https://en.wikipedia.org/wiki/Binary_search_algorithm">Binary search algorithm (Wikipedia)</a>
 */
public final class BinarySearch {

    private BinarySearch() {
        // do nothing
    }

    /**
     * Searches for a target value in a sorted array using Binary Search.
     *
     * @param array  Sorted array of integers
     * @param left   Left boundary of the search interval
     * @param right  Right boundary of the search interval
     * @param target Value to search for
     * @return Index of the target if found, -1 otherwise
     */
    public static int binarySearch(int[] array, int left, int right, int target) {
        if (left <= right) {

            int mid = left + (right - left) / 2;

            if (target == array[mid]) {
                return mid;
            } else if (target < array[mid]) {
                return binarySearch(array, left, mid - 1, target);
            } else {
                return binarySearch(array, mid + 1, right, target);
            }
        } else {
            return -1;
        }
    }
}
