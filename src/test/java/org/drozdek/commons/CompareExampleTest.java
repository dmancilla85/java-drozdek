package org.drozdek.commons;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompareExampleTest {

    @Test
    @DisplayName("Compare equal values returns zero")
    void compareEqual() {
        CompareExample comp = new CompareExample();
        assertEquals(0, comp.compare(5, 5));
    }

    @Test
    @DisplayName("Compare first less than second returns negative")
    void compareLess() {
        CompareExample comp = new CompareExample();
        assertTrue(comp.compare(3, 7) < 0);
    }

    @Test
    @DisplayName("Compare first greater than second returns positive")
    void compareGreater() {
        CompareExample comp = new CompareExample();
        assertTrue(comp.compare(9, 2) > 0);
    }
}
