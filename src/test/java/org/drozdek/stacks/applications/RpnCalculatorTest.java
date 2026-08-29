package org.drozdek.stacks.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RpnCalculatorTest {

    @Test
    @DisplayName("Evaluates a simple addition")
    void evaluate_addition() {
        assertEquals(7, RpnCalculator.evaluate("3 4 +"));
    }

    @Test
    @DisplayName("Evaluates a multi-operator expression")
    void evaluate_compound() {
        assertEquals(14, RpnCalculator.evaluate("3 4 + 2 *"));
    }

    @Test
    @DisplayName("Handles subtraction and division")
    void evaluate_subtractDivide() {
        assertEquals(0, RpnCalculator.evaluate("6 2 / 1 3 * -"));
    }

    @Test
    @DisplayName("Rejects a malformed expression")
    void evaluate_invalid() {
        assertThrows(IllegalArgumentException.class, () -> RpnCalculator.evaluate("3 +"));
    }
}
