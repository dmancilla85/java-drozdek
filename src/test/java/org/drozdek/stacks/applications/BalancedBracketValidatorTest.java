package org.drozdek.stacks.applications;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BalancedBracketValidator Tests")
class BalancedBracketValidatorTest {

  @Test
  @DisplayName("Null or empty string is considered balanced")
  void testNullOrEmpty() {
    assertTrue(BalancedBracketValidator.isBalanced(null));
    assertTrue(BalancedBracketValidator.isBalanced(""));
    assertEquals(-1, BalancedBracketValidator.findMismatchIndex(null));
    assertEquals(-1, BalancedBracketValidator.findMismatchIndex(""));
  }

  @Test
  @DisplayName("Strings without any brackets are balanced")
  void testNoBrackets() {
    assertTrue(BalancedBracketValidator.isBalanced("hello world 123 + 456"));
    assertEquals(-1, BalancedBracketValidator.findMismatchIndex("hello world 123 + 456"));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "()",
      "[]",
      "{}",
      "()[]{}",
      "{[()]}",
      "((a + b) * [c - d] / {e})",
      "function foo() { if (x[0] == 1) { return (y); } }",
      "{[()]}{[()]}"
  })
  @DisplayName("Valid balanced bracket expressions")
  void testBalancedExpressions(String expression) {
    assertTrue(BalancedBracketValidator.isBalanced(expression));
    assertEquals(-1, BalancedBracketValidator.findMismatchIndex(expression));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "(",
      ")",
      "(]",
      "([)]",
      "{{{{",
      "))))",
      "{[(])}",
      "function() { return x[0); }",
      "(a + b"
  })
  @DisplayName("Unbalanced bracket expressions")
  void testUnbalancedExpressions(String expression) {
    assertFalse(BalancedBracketValidator.isBalanced(expression));
    assertNotEquals(-1, BalancedBracketValidator.findMismatchIndex(expression));
  }

  @Test
  @DisplayName("Mismatch index detection")
  void testMismatchIndexDetails() {
    assertEquals(0, BalancedBracketValidator.findMismatchIndex(")"));
    assertEquals(2, BalancedBracketValidator.findMismatchIndex("([)"));
    assertEquals(0, BalancedBracketValidator.findMismatchIndex("("));
  }
}
