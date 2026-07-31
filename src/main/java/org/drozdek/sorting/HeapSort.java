package org.drozdek.sorting;

/**
 * Heap Sort algorithm.
 *
 * Builds a max-heap from the input array, then repeatedly extracts the
 * root (the largest element) and swaps it to the end of the unsorted
 * portion. The heap property is restored after each extraction via
 * heapify, yielding a sorted array in-place.
 *
 * <p><b>Real-world use case:</b> Priority-queue-backed scheduling in
 * operating systems, or any scenario requiring guaranteed O(n log n) time
 * with minimal auxiliary memory overhead.
 *
 * Time complexity: O(n log n)
 * Memory complexity: O(1)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Heapsort">Heapsort (Wikipedia)</a>
 */
public final class HeapSort {

    private HeapSort() {
        // do nothing
    }

    /**
     * Sorts an array in-place using Heap Sort.
     *
     * @param array Array of integers to sort
     */
    public static void heapSort(int[] array) {
        int n = array.length;

        // Build initial max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {

            // Move the root (max) to the end
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Restore max-heap property on the reduced heap
            heapify(array, i, 0);
        }
    }

    // Restore max-heap property for the subtree rooted at i
    private static void heapify(int[] array, int n, int i) {
        // Find the largest among root, left child, and right child
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // If the largest is not the root, swap and recursively heapify the affected subtree
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, n, largest);
        }
    }
}
