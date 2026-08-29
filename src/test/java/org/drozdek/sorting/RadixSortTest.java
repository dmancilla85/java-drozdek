package org.drozdek.sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RadixSortTest {

    @Test
    @DisplayName("Sorts a small array of single and multi-digit numbers")
    void radixSort_sortsArray() {
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};
        RadixSort.radixSort(array);
        assertArrayEquals(new int[]{2, 24, 45, 66, 75, 90, 170, 802}, array);
    }

    @Test
    @DisplayName("Sorts an already sorted array")
    void radixSort_sortedArrayUnchanged() {
        int[] array = {1, 2, 3, 4, 5};
        RadixSort.radixSort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    @DisplayName("Handles a single element")
    void radixSort_singleElement() {
        int[] array = {7};
        RadixSort.radixSort(array);
        assertArrayEquals(new int[]{7}, array);
    }

    @Test
    @DisplayName("Handles duplicate values")
    void radixSort_duplicates() {
        int[] array = {3, 1, 3, 1, 2};
        RadixSort.radixSort(array);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, array);
    }

    @Test
    @DisplayName("Sorts numbers with varying digit counts")
    void radixSort_varyingDigits() {
        int[] array = {9, 99, 5, 555, 0, 1000};
        RadixSort.radixSort(array);
        assertArrayEquals(new int[]{0, 5, 9, 99, 555, 1000}, array);
    }
}
