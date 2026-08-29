package org.drozdek.trees.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExpressionEvaluatorTest {

    @Test
    @DisplayName("Evaluates an infix expression with precedence")
    void evaluateInfix_precedence() {
        assertEquals(14, ExpressionEvaluator.evaluateInfix("2+3*4"));
    }

    @Test
    @DisplayName("Evaluates an infix expression with parentheses")
    void evaluateInfix_parentheses() {
        assertEquals(18, ExpressionEvaluator.evaluateInfix("3*(4+2)"));
    }

    @Test
    @DisplayName("Builds a tree and reports its structure")
    void build_reportsStructure() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator("23*4+");
        assertFalse(evaluator.isEmpty());
        assertEquals(5, evaluator.nodeCount());
        assertEquals("2*3+4", evaluator.infixForm());
    }
}
