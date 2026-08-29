package org.drozdek.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecursiveDescentInterpreterTest {

    @Test
    @DisplayName("Evaluates a simple addition")
    void evaluate_addition() {
        assertEquals(7.0, RecursiveDescentInterpreter.evaluate("3 + 4", Map.of()));
    }

    @Test
    @DisplayName("Respects operator precedence")
    void evaluate_precedence() {
        assertEquals(13.0, RecursiveDescentInterpreter.evaluate("3 + 5 * 2", Map.of()));
    }

    @Test
    @DisplayName("Respects parentheses")
    void evaluate_parentheses() {
        assertEquals(16.0, RecursiveDescentInterpreter.evaluate("(3 + 5) * 2", Map.of()));
    }

    @Test
    @DisplayName("Handles division and modulo")
    void evaluate_divisionAndModulo() {
        assertEquals(3.0, RecursiveDescentInterpreter.evaluate("9 / 3", Map.of()));
        assertEquals(1.0, RecursiveDescentInterpreter.evaluate("7 % 3", Map.of()));
    }

    @Test
    @DisplayName("Uses variable bindings")
    void evaluate_variables() {
        assertEquals(14.0, RecursiveDescentInterpreter.evaluate("x + y", Map.of("x", 6.0, "y", 8.0)));
    }

    @Test
    @DisplayName("Handles unary minus")
    void evaluate_unaryMinus() {
        assertEquals(-4.0, RecursiveDescentInterpreter.evaluate("-4", Map.of()));
        assertEquals(1.0, RecursiveDescentInterpreter.evaluate("5 + -4", Map.of()));
    }
}
