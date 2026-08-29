package org.drozdek.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WagnerFischerEditDistanceTest {

    @Test
    @DisplayName("Identical strings have zero distance")
    void distance_identical() {
        assertEquals(0, WagnerFischerEditDistance.distance("kitten", "kitten"));
    }

    @Test
    @DisplayName("Classic kitten to sitting distance is three")
    void distance_kittenToSitting() {
        assertEquals(3, WagnerFischerEditDistance.distance("kitten", "sitting"));
    }

    @Test
    @DisplayName("Empty to non-empty distance is the length")
    void distance_fromEmpty() {
        assertEquals(5, WagnerFischerEditDistance.distance("", "hello"));
    }

    @Test
    @DisplayName("One substitution is distance one")
    void distance_singleSubstitution() {
        assertEquals(1, WagnerFischerEditDistance.distance("cat", "cut"));
    }

    @Test
    @DisplayName("Within distance respects the threshold")
    void withinDistance_threshold() {
        assertTrue(WagnerFischerEditDistance.withinDistance("kitten", "kittens", 1));
        assertFalse(WagnerFischerEditDistance.withinDistance("kitten", "sitting", 2));
    }
}
