package org.drozdek.searching;

public final class ExponentialSearch {

    private ExponentialSearch() {
        // do nothing
    }

    // Complexity O(log n)
    // Memory O(1)
    public static int exponentialSearch(int[] array, int target) {
        if (array[0] == target) {
            return 0;
        }

        int i = 1;
        while (i < array.length && array[i] <= target) {
            i *= 2;
        }

        return BinarySearch.binarySearch(array, i / 2, Math.min(i, array.length - 1), target);
    }
}
