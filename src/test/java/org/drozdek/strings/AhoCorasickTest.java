package org.drozdek.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AhoCorasickTest {

    @Test
    @DisplayName("Single pattern found in text")
    void search_singlePattern() {
        AhoCorasick automaton = new AhoCorasick();
        automaton.addPattern("he");
        automaton.build();
        List<int[]> results = automaton.search("ushers");
        assertEquals(1, results.size());
        assertEquals(2, results.get(0)[0]);
        assertEquals(4, results.get(0)[1]);
    }

    @Test
    @DisplayName("Multiple patterns are all reported")
    void search_multiplePatterns() {
        AhoCorasick automaton = new AhoCorasick();
        automaton.addPattern("he");
        automaton.addPattern("she");
        automaton.addPattern("hers");
        automaton.build();
        List<int[]> results = automaton.search("ushers");
        assertEquals(3, results.size());
    }

    @Test
    @DisplayName("No match returns an empty list")
    void search_noMatch() {
        AhoCorasick automaton = new AhoCorasick();
        automaton.addPattern("xyz");
        automaton.build();
        assertEquals(0, automaton.search("hello").size());
    }

    @Test
    @DisplayName("Overlapping patterns sharing suffixes are found")
    void search_overlappingSuffixes() {
        AhoCorasick automaton = new AhoCorasick();
        automaton.addPattern("a");
        automaton.addPattern("aa");
        automaton.build();
        List<int[]> results = automaton.search("aaa");
        assertEquals(5, results.size());
    }

    @Test
    @DisplayName("Patterns at the very start are reported")
    void search_patternAtStart() {
        AhoCorasick automaton = new AhoCorasick();
        automaton.addPattern("abc");
        automaton.build();
        List<int[]> results = automaton.search("abcdef");
        assertEquals(1, results.size());
        assertEquals(0, results.get(0)[0]);
        assertEquals(3, results.get(0)[1]);
    }
}
