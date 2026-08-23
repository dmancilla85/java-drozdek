package org.drozdek.sorting;

/// Merge Sort algorithm.
///
/// A divide-and-conquer algorithm that recursively splits the array in half,
/// sorts each half independently, then merges the two sorted halves into one.
/// Guarantees O(n log n) performance for all inputs and is a stable sort.
///
/// **Real-world use case:** External sorting of large files that do not
/// fit in main memory, or efficiently sorting linked lists where random access
/// is expensive.
///
/// Complexity Analysis:
/// Time Complexity: O(n log n)
/// Auxiliary Space: O(n)
///
/// @see <a href="https://en.wikipedia.org/wiki/Merge_sort">Merge sort (Wikipedia)</a>
public final class MergeSort {

    private MergeSort() {
        // do nothing
    }

    /// Sorts a sub-array in-place using Merge Sort.
    ///
    /// @param array Array of integers to sort
    /// @param left  Starting index of the sub-array
    /// @param right Ending index of the sub-array
    public static void mergeSort(int[] array, int left, int right) {

        if (left < right) {

            int middle = (left + right) / 2;

            // Recursively sort both halves
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Merge the sorted halves
            merge(array, left, middle, right);
        }
    }

    // Merge two sorted subarrays into one sorted array
    public static void merge(int[] array, int left, int middle, int right) {

        // Sizes of the two temporary subarrays
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data into temporary arrays
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, middle + 1, rightArray, 0, n2);

        // Merge the temporary arrays back into the original
        int i = 0;
        int j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from either subarray
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
