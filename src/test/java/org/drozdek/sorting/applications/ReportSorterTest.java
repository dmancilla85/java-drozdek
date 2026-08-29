package org.drozdek.sorting.applications;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.drozdek.sorting.applications.ReportSorter.Algorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReportSorterTest {

    @Test
    @DisplayName("Every elementary strategy yields a sorted copy")
    void sort_allStrategies() {
        int[] input = {5, 2, 9, 1, 5, 6};
        int[] expected = {1, 2, 5, 5, 6, 9};
        assertArrayEquals(expected, ReportSorter.sort(input, Algorithm.BUBBLE));
        assertArrayEquals(expected, ReportSorter.sort(input, Algorithm.COCKTAIL));
        assertArrayEquals(expected, ReportSorter.sort(input, Algorithm.INSERTION));
        assertArrayEquals(expected, ReportSorter.sort(input, Algorithm.SELECTION));
        assertArrayEquals(expected, ReportSorter.sort(input, Algorithm.SHELL));
    }

    @Test
    @DisplayName("Counting and bucket sort handle small non-negative ranges")
    void sort_integerSorts() {
        assertArrayEquals(new int[] {2, 2, 4, 8}, ReportSorter.sort(new int[] {4, 2, 2, 8}, Algorithm.COUNTING));
        assertArrayEquals(new int[] {1, 2, 3, 7}, ReportSorter.sort(new int[] {3, 1, 7, 2}, Algorithm.BUCKET));
    }
}
