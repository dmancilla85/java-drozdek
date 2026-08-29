package org.drozdek.sorting.applications;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TopKRankerTest {

    @Test
    @DisplayName("Returns the top K via quick sort")
    void topK_returnsLargest() {
        int[] top = TopKRanker.topK(new int[] {3, 1, 4, 1, 5, 9, 2, 6}, 3);
        assertArrayEquals(new int[] {9, 6, 5}, top);
    }

    @Test
    @DisplayName("Returns the top K via heap sort")
    void topKByHeap_returnsLargest() {
        int[] top = TopKRanker.topKByHeap(new int[] {3, 1, 4, 1, 5, 9, 2, 6}, 3);
        assertArrayEquals(new int[] {9, 6, 5}, top);
    }

    @Test
    @DisplayName("Returns all elements in descending order when K exceeds size")
    void topK_largerThanSize() {
        assertEquals(4, TopKRanker.topK(new int[] {1, 2, 3, 4}, 100).length);
    }
}
