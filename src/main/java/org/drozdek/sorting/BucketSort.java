package org.drozdek.sorting;

import java.util.ArrayList;
import java.util.Collections;

/// Bucket Sort algorithm.
///
/// Distributes elements into a number of buckets based on their value range,
/// sorts each bucket individually (e.g., using insertion sort or
/// {@link Collections#sort}), then concatenates the buckets back into the
/// original array. Performs best when input values are uniformly distributed.
///
/// **Real-world use case:** Sorting a large set of exam scores by grade
/// range, or processing uniformly distributed floating-point data such as
/// sensor readings.
///
/// Complexity Analysis:
/// Time Complexity: O(n) average, O(n²) worst case
/// Auxiliary Space: O(n)
///
/// @see <a href="https://en.wikipedia.org/wiki/Bucket_sort">Bucket sort (Wikipedia)</a>
public final class BucketSort {

    private BucketSort() {
    }

    /// Sorts an array in-place using Bucket Sort.
    ///
    /// @param array      Array of integers to sort
    /// @param bucketSize Number of elements per bucket
    public static void bucketSort(int[] array, int bucketSize) {

        // Find min and max values in the array
        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }

        // Calculate number of buckets
        int bucketCount = (max - min) / bucketSize + 1;

        // Create empty bucket list
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribute elements into buckets
        for (int j : array) {
            int bucketIndex = (j - min) / bucketSize;
            buckets.get(bucketIndex).add(j);
        }

        // Sort each bucket and copy back to the original array
        int index = 0;

        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);

            for (Integer integer : bucket) {
                array[index++] = integer;
            }
        }
    }
}
