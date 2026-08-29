package org.drozdek.graphs.applications;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HospitalResidentMatcherTest {

    @Test
    @DisplayName("Produces a stable resident-to-hospital assignment")
    void matchResidents_stable() {
        int[][] residentPrefs = {{0, 1}, {0, 1}};
        int[][] hospitalPrefs = {{0, 1}, {0, 1}};
        assertArrayEquals(new int[] {0, 1}, HospitalResidentMatcher.matchResidents(residentPrefs, hospitalPrefs));
    }
}
