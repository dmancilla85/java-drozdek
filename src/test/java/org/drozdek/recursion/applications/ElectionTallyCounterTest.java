package org.drozdek.recursion.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ElectionTallyCounterTest {

    @Test
    @DisplayName("Finds a candidate with more than half the votes")
    void majorityCandidate_present() {
        assertEquals(3, ElectionTallyCounter.majorityCandidate(List.of(3, 3, 3, 3, 1, 2)));
    }

    @Test
    @DisplayName("Returns null when no candidate holds a majority")
    void majorityCandidate_absent() {
        assertNull(ElectionTallyCounter.majorityCandidate(List.of(1, 2, 3, 1)));
    }
}
