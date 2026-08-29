package org.drozdek.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoyerMooreTest {

    @Test
    @DisplayName("Search finds a present pattern")
    void search_findsPattern() {
        assertEquals(2, BoyerMoore.search("xxababxc", "ab"));
    }

    @Test
    @DisplayName("Search returns -1 for an absent pattern")
    void search_absentPattern() {
        assertEquals(-1, BoyerMoore.search("hello", "xyz"));
    }

    @Test
    @DisplayName("Search handles a pattern equal to the whole text")
    void search_wholeText() {
        assertEquals(0, BoyerMoore.search("cat", "cat"));
    }

    @Test
    @DisplayName("Empty pattern matches at the start")
    void search_emptyPattern() {
        assertEquals(0, BoyerMoore.search("text", ""));
    }

    @Test
    @DisplayName("FindAll locates non-overlapping occurrences")
    void findAll_locatesAll() {
        List<Integer> matches = BoyerMoore.findAll("aaaa", "aa");
        assertEquals(List.of(0, 2), matches);
    }

    @Test
    @DisplayName("Last occurrence table records rightmost indices")
    void buildLastOccurrence_rightmost() {
        int[] last = BoyerMoore.buildLastOccurrence("ababc");
        assertEquals(2, last['a']);
        assertEquals(3, last['b']);
        assertEquals(-1, last['z']);
    }

    @Test
    @DisplayName("Separated occurrences are found")
    void findAll_separated() {
        List<Integer> matches = BoyerMoore.findAll("aXbXcX", "X");
        assertEquals(List.of(1, 3, 5), matches);
    }
}
