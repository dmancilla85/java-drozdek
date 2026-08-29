package org.drozdek.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShiftAndMatcherTest {

    @Test
    @DisplayName("Search finds a present pattern")
    void search_findsPattern() {
        assertEquals(2, ShiftAndMatcher.search("xxabcxx", "abc"));
    }

    @Test
    @DisplayName("Search returns -1 for an absent pattern")
    void search_absentPattern() {
        assertEquals(-1, ShiftAndMatcher.search("hello", "xyz"));
    }

    @Test
    @DisplayName("Empty pattern matches at the start")
    void search_emptyPattern() {
        assertEquals(0, ShiftAndMatcher.search("text", ""));
    }

    @Test
    @DisplayName("FindAll locates occurrences")
    void findAll_locatesAll() {
        List<Integer> matches = ShiftAndMatcher.findAll("ababab", "ab");
        assertEquals(List.of(0, 2, 4), matches);
    }

    @Test
    @DisplayName("Patterns longer than the word width are rejected")
    void buildMasks_rejectsLongPatterns() {
        String longPattern = "a".repeat(65);
        assertThrows(IllegalArgumentException.class, () -> ShiftAndMatcher.buildMasks(longPattern));
    }

    @Test
    @DisplayName("Pattern within word width is accepted")
    void buildMasks_acceptsShortPatterns() {
        long[] masks = ShiftAndMatcher.buildMasks("aba");
        assertEquals(5L, masks['a']);
        assertEquals(2L, masks['b']);
    }
}
