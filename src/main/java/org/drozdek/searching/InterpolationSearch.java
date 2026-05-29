package org.drozdek.searching;

public final class InterpolationSearch {

    private InterpolationSearch() {
        // do nothing
    }

    // Complexity O(log log n) average, O(n) worst
    // Memory O(1)
    public static int interpolationSearch(int[] array, int left, int right, int target) {
        while (left <= right && target >= array[left] && target <= array[right]) {
            if (left == right) {
                return array[left] == target ? left : -1;
            }

            int pos = left + (target - array[left]) * (right - left)
                    / (array[right] - array[left]);

            if (array[pos] == target) {
                return pos;
            }

            if (array[pos] < target) {
                left = pos + 1;
            } else {
                right = pos - 1;
            }
        }

        return -1;
    }
}
