package org.drozdek.strings.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SundayQuickSearchTest {

    @Test
    @DisplayName("Search finds a present pattern")
    void search_findsPattern() {
        assertEquals(2, SundayQuickSearch.search("xxabcdeabc", "abc"));
    }

    @Test
    @DisplayName("Search returns -1 for an absent pattern")
    void search_absentPattern() {
        assertEquals(-1, SundayQuickSearch.search("hello", "xyz"));
    }

    @Test
    @DisplayName("Empty pattern matches at the start")
    void search_emptyPattern() {
        assertEquals(0, SundayQuickSearch.search("text", ""));
    }

    @Test
    @DisplayName("FindAll locates non-overlapping matches")
    void findAll_locatesAll() {
        List<Integer> matches = SundayQuickSearch.findAll("aaaa", "aa");
        assertEquals(List.of(0, 2), matches);
    }

    @Test
    @DisplayName("Shift table gives the pattern length by default")
    void buildShiftTable_defaultShift() {
        int[] shift = SundayQuickSearch.buildShiftTable("abc");
        assertEquals(4, shift['z']);
    }

    @Test
    @DisplayName("Shift table position for a pattern character")
    void buildShiftTable_characterShift() {
        int[] shift = SundayQuickSearch.buildShiftTable("abc");
        assertEquals(3, shift['a']);
    }
}
