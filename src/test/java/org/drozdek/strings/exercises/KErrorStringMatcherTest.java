package org.drozdek.strings.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KErrorStringMatcherTest {

    @Test
    @DisplayName("Exact match with zero errors")
    void search_zeroErrors() {
        List<Integer> matches = KErrorStringMatcher.search("hello world", "world", 0);
        assertEquals(List.of(6), matches);
    }

    @Test
    @DisplayName("Pattern longer than text yields no matches")
    void search_patternLongerThanText() {
        assertEquals(List.of(), KErrorStringMatcher.search("abc", "abcd", 1));
    }

    @Test
    @DisplayName("Matching with one substitution error")
    void search_oneError() {
        List<Integer> matches = KErrorStringMatcher.search("cat", "cut", 1);
        assertEquals(List.of(0), matches);
    }

    @Test
    @DisplayName("Two errors are allowed when k is two")
    void search_twoErrorsWithinK() {
        List<Integer> matches = KErrorStringMatcher.search("sitting", "kitten", 2);
        assertEquals(List.of(0), matches);
    }
}
