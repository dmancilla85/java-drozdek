package org.drozdek.searching;

public final class JumpSearch {

    private JumpSearch() {
        // do nothing
    }

    // Complexity O(√n)
    // Memory O(1)
    public static int jumpSearch(int[] array, int target) {
        int n = array.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;

        while (array[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) {
                return -1;
            }
        }

        while (array[prev] < target) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }

        if (array[prev] == target) {
            return prev;
        }

        return -1;
    }
}
