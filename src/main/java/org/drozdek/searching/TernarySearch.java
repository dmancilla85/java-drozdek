package org.drozdek.searching;

public class TernarySearch {

    private TernarySearch() {
        // do nothing
    }

    // Complexity O(log₃ n)
    // Memory O(log n)
    public static int ternarySearch(int[] array, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid1 = left + (right - left) / 3;
        int mid2 = right - (right - left) / 3;

        if (target == array[mid1]) {
            return mid1;
        }
        if (target == array[mid2]) {
            return mid2;
        }

        if (target < array[mid1]) {
            return ternarySearch(array, left, mid1 - 1, target);
        } else if (target > array[mid2]) {
            return ternarySearch(array, mid2 + 1, right, target);
        } else {
            return ternarySearch(array, mid1 + 1, mid2 - 1, target);
        }
    }
}
