package org.drozdek.recursion.applications;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LexicographicOrderCheckerTest {

    @Test
    @DisplayName("Accepts non-decreasing sequences")
    void isSorted_true() {
        assertTrue(LexicographicOrderChecker.isSorted("abc"));
        assertTrue(LexicographicOrderChecker.isSorted("a"));
    }

    @Test
    @DisplayName("Rejects decreasing sequences")
    void isSorted_false() {
        assertFalse(LexicographicOrderChecker.isSorted("cba"));
        assertFalse(LexicographicOrderChecker.isSorted("azb"));
    }
}
