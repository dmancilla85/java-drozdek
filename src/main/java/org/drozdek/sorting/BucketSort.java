package org.drozdek.sorting;

import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {

    private BucketSort() {
    }

    // Complexity: O(n)
    // Memory: O(n)
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
