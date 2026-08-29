package org.drozdek.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnuthMorrisPrattTest {

    @Test
    @DisplayName("Failure function reflects proper prefix-suffix lengths")
    void buildFailureFunction_matchesPrefixSuffix() {
        int[] failure = KnuthMorrisPratt.buildFailureFunction("ababac");
        assertEquals(2, failure[3]);
        assertEquals(0, failure[1]);
    }

    @Test
    @DisplayName("Search finds a present pattern")
    void search_findsPattern() {
        assertEquals(2, KnuthMorrisPratt.search("xxababxc", "ab"));
    }

    @Test
    @DisplayName("Search returns -1 for an absent pattern")
    void search_absentPattern() {
        assertEquals(-1, KnuthMorrisPratt.search("hello", "xyz"));
    }

    @Test
    @DisplayName("Search handles a pattern equal to the whole text")
    void search_wholeText() {
        assertEquals(0, KnuthMorrisPratt.search("ababab", "ababab"));
    }

    @Test
    @DisplayName("Search with an empty pattern matches at the start")
    void search_emptyPattern() {
        assertEquals(0, KnuthMorrisPratt.search("text", ""));
    }

    @Test
    @DisplayName("FindAll locates all occurrences")
    void findAll_locatesAll() {
        List<Integer> matches = KnuthMorrisPratt.findAll("aaaa", "aa");
        assertEquals(List.of(0, 2), matches);
    }

    @Test
    @DisplayName("FindAll with no match returns empty list")
    void findAll_noMatch() {
        assertEquals(List.of(), KnuthMorrisPratt.findAll("abc", "z"));
    }
}
