package org.drozdek.sorting;

public class InsertionSort {

    private InsertionSort(){
        // do nothing
    }

    // Complexity O(n²)
    // Memory O(1)
    // Type: Stable
            public static void insertionSort(int[] array) {
                int n = array.length;

                // starts with the second element (i = 1)
                for (int i = 1; i < n; i++) {

                    // save the current value as the key
                    int key = array[i];

                    // j is the previous element index
                    int j = i - 1;

                    // if the previous element (j) is major than the key, move j-element to the right
                    while (j >= 0 && array[j] > key) {
                        array[j + 1] = array[j];
                        j--;
                    }
                    // insert the key in the j-position found
                    array[j + 1] = key;
                }
    }
}
