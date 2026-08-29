package org.drozdek.recursion.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpreadsheetFormulaEngineTest {

    @Test
    @DisplayName("Evaluates expressions with precedence and variables")
    void evaluate_withVariables() {
        assertEquals(14.0, SpreadsheetFormulaEngine.evaluate("(a+b)*2", Map.of("a", 3.0, "b", 4.0)));
    }

    @Test
    @DisplayName("Evaluates plain arithmetic without variables")
    void evaluate_plainArithmetic() {
        assertEquals(7.0, SpreadsheetFormulaEngine.evaluate("3 + 2 * 2", Map.of()));
    }
}
