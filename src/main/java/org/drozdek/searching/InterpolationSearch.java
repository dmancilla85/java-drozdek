package org.drozdek.searching;

public final class InterpolationSearch {

    private InterpolationSearch() {
        // do nothing
    }

    // Complexity O(log log n) average, O(n) worst
    // Memory O(1)
    public static int interpolationSearch(int[] array, int left, int right, int target) {
        int result = -1;

        while (left <= right && target >= array[left] && target <= array[right] && result == -1) {
            if (left == right) {
                if (array[left] == target) {
                    result = left;
                }
                break;
            }

            int pos = left + (target - array[left]) * (right - left)
                    / (array[right] - array[left]);

            if (array[pos] == target) {
                result = pos;
            } else if (array[pos] < target) {
                left = pos + 1;
            } else {
                right = pos - 1;
            }
        }

        return result;
    }
}
