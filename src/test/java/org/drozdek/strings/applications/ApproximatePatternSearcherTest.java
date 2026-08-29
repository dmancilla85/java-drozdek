package org.drozdek.strings.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApproximatePatternSearcherTest {

    @Test
    @DisplayName("Finds the first occurrence with bit-parallel search")
    void firstOccurrence_matches() {
        assertEquals(0, ApproximatePatternSearcher.firstOccurrence("abacabadab", "aba"));
        assertEquals(-1, ApproximatePatternSearcher.firstOccurrence("xyz", "aba"));
    }

    @Test
    @DisplayName("Finds every non-overlapping occurrence")
    void allOccurrences_multiple() {
        assertEquals(List.of(0, 4), ApproximatePatternSearcher.allOccurrences("abacabadab", "aba"));
    }
}
