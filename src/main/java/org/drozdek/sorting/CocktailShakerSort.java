package org.drozdek.sorting;

/// Cocktail Shaker Sort algorithm.
///
/// A bidirectional variant of bubble sort that passes alternately from left
/// to right and right to left, allowing small and large elements to move
/// toward the correct end of the array in the same pass sequence. This
/// reduces the number of passes needed on partially sorted data.
///
/// **Real-world use case:** Educational demonstrations and sorting tiny
/// nearly-sorted arrays where simplicity is valued over asymptotic speed.
///
/// Complexity Analysis:
/// Time Complexity: O(n^2) worst and average case, O(n) when already sorted
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Cocktail shaker sort. *Wikipedia*. https://en.wikipedia.org/wiki/Cocktail_shaker_sort
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 9.
public final class CocktailShakerSort {

    private CocktailShakerSort() {
        // do nothing
    }

    /// Sorts an array in-place using cocktail shaker sort.
    ///
    /// @param array Array of integers to sort
    public static void cocktailShakerSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int start = 0;
        int end = array.length - 1;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            end--;
            for (int i = end - 1; i >= start; i--) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }
            start++;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
