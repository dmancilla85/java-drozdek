package org.drozdek.strings.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneSequenceSearchTest {

    @Test
    @DisplayName("Finds the first occurrence with KMP and Boyer-Moore")
    void firstOccurrence_matches() {
        String genome = "ACGTACGTGCA";
        assertEquals(0, GeneSequenceSearch.kmpFirst(genome, "ACG"));
        assertEquals(0, GeneSequenceSearch.boyerMooreFirst(genome, "ACG"));
    }

    @Test
    @DisplayName("Finds every non-overlapping occurrence with KMP")
    void allOccurrences_multiple() {
        String genome = "ACGTACGTGCA";
        assertEquals(List.of(0, 4), GeneSequenceSearch.allOccurrences(genome, "ACG"));
    }

    @Test
    @DisplayName("Returns -1 when the pattern is absent")
    void firstOccurrence_absent() {
        assertEquals(-1, GeneSequenceSearch.kmpFirst("ACGTACGTGCA", "TTT"));
        assertEquals(-1, GeneSequenceSearch.boyerMooreFirst("ACGTACGTGCA", "TTT"));
    }
}
