package org.drozdek.searching;

public final class BinarySearch {

    private BinarySearch() {
        // do nothing
    }

    public static int binarySearch(int[] array, int left, int right, int target) {
        if (left <= right) {

            int mid = left + (right - left) / 2;

            if (target == array[mid]) {
                return mid;
            } else if (target < array[mid]) {
                return binarySearch(array, left, mid - 1, target);
            } else {
                return binarySearch(array, mid + 1, right, target);
            }
        } else {
            return -1;
        }
    }
}
