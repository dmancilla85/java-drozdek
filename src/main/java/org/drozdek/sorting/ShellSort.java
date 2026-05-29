package org.drozdek.sorting;

public final class ShellSort {

    private ShellSort() {
        // do nothing
    }

    // Complexity: O(n1.25)
    // Memory: O(1)
    public static void shellSort(int[] array) {

        int n = array.length;

        // Start with gap = half the array size, then halve each iteration
        int gap = n / 2;

        while (gap > 0) {

            // Perform a gapped insertion sort
            for (int i = gap; i < n; i++) {

                int key = array[i];
                int j = i - gap;

                // Shift elements that are greater than key to the right
                while (j >= 0 && array[j] > key) {
                    array[j + gap] = array[j];
                    j -= gap;
                }

                // Insert key at its correct position
                array[j + gap] = key;
            }
            gap /= 2;
        }
    }
}
