package org.drozdek.searching.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TriSectionLookupTest {

    @Test
    @DisplayName("Ternary search finds a key in sorted data")
    void findInSorted_found() {
        int[] array = {1, 4, 7, 10, 13, 16, 19};
        assertEquals(4, TriSectionLookup.findInSorted(array, 13));
    }

    @Test
    @DisplayName("Ternary search reports absence")
    void findInSorted_absent() {
        int[] array = {1, 2, 3, 4};
        assertEquals(-1, TriSectionLookup.findInSorted(array, 9));
    }

    @Test
    @DisplayName("Sentinel linear search finds a key in unsorted data")
    void findInAny_found() {
        int[] array = {9, 2, 5, 1, 8};
        assertEquals(3, TriSectionLookup.findInAny(array, 1));
    }

    @Test
    @DisplayName("Sentinel linear search handles an empty array")
    void findInAny_empty() {
        assertEquals(-1, TriSectionLookup.findInAny(new int[]{}, 1));
    }
}
