package org.drozdek.sorting;

public class BubbleSort {

    private BubbleSort() {
        // do nothing
    }

    // Complexity O(n²)
    // Memory O(1)
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        // Repeat until no swaps are made
        do {
            swapped = false;

            for (int i = 0; i < n - 1; i++) {

                // Swap adjacent elements if out of order
                if (array[i] > array[i + 1]) {

                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    swapped = true;
                }
            }
        } while (swapped);
    }
}
