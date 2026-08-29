package org.drozdek.sorting;

/// Bubble Sort algorithm.
///
/// Repeatedly steps through the array, compares adjacent elements, and swaps
/// them if they are in the wrong order. Each pass causes the next largest
/// element to "bubble" to its correct position at the end. The process repeats
/// until no swaps occur, indicating the array is sorted.
///
/// **Real-world use case:** Teaching sorting fundamentals in computer
/// science education, or sorting very small datasets (fewer than ~50 elements)
/// where implementation simplicity matters more than raw speed.
///
/// Complexity Analysis:
/// Time Complexity: O(n�)
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Bubble sort. *Wikipedia*. https://en.wikipedia.org/wiki/Bubble_sort
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 9.
public final class BubbleSort {

    private BubbleSort() {
        // do nothing
    }

    /// Sorts an array in-place using Bubble Sort.
    ///
    /// @param array Array of integers to sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        do {
            swapped = false;

            for (int i = 0; i < n - 1; i++) {

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
