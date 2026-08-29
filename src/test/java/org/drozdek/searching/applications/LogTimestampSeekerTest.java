package org.drozdek.searching.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogTimestampSeekerTest {

    @Test
    @DisplayName("Finds the entry by record number")
    void findEntry_found() {
        long[] entries = {2, 5, 8, 12, 16, 23, 38, 56, 72};
        assertEquals(4, LogTimestampSeeker.findEntry(entries, 16));
    }

    @Test
    @DisplayName("Returns -1 for an absent record")
    void findEntry_absent() {
        long[] entries = {1, 3, 5, 7};
        assertEquals(-1, LogTimestampSeeker.findEntry(entries, 4));
    }

    @Test
    @DisplayName("Empty log returns -1")
    void findEntry_empty() {
        assertEquals(-1, LogTimestampSeeker.findEntry(new long[]{}, 1));
    }

    @Test
    @DisplayName("Exponential search finds the target")
    void findEntryInt_found() {
        int[] entries = {2, 5, 8, 14, 20, 33, 55};
        assertEquals(3, LogTimestampSeeker.findEntryInt(entries, 14));
    }
}
