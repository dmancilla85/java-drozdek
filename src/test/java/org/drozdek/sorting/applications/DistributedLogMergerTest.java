package org.drozdek.sorting.applications;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DistributedLogMergerTest {

    @Test
    @DisplayName("Sorts an out-of-order bucket of timestamps")
    void sortTimestamps_orders() {
        int[] timestamps = {5, 1, 4, 2, 3};
        DistributedLogMerger.sortTimestamps(timestamps);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, timestamps);
    }

    @Test
    @DisplayName("Merges two ordered streams into one")
    void mergeStreams_combinesOrdered() {
        int[] merged = DistributedLogMerger.mergeStreams(new int[] {1, 3, 5}, new int[] {2, 4, 6});
        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6}, merged);
    }

    @Test
    @DisplayName("Merge with an empty stream returns the other stream")
    void mergeStreams_emptySide() {
        assertArrayEquals(new int[] {1, 3, 5}, DistributedLogMerger.mergeStreams(new int[] {1, 3, 5}, new int[0]));
    }

    @Test
    @DisplayName("Merges many streams into a single sorted array")
    void mergeMany_allStreams() {
        int[] merged = DistributedLogMerger.mergeMany(List.of(
            new int[] {1, 4},
            new int[] {2, 3},
            new int[] {0}));
        assertArrayEquals(new int[] {0, 1, 2, 3, 4}, merged);
    }
}
