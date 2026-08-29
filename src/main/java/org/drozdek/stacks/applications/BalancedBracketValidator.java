package org.drozdek.stacks.applications;

import org.drozdek.stacks.ArrayStack;
import org.drozdek.stacks.interfaces.StackInterface;

/// Delimiter and bracket validator utilizing an array-backed stack to verify
/// proper matching and nesting of parentheses `()`, square brackets `[]`, and
/// curly braces `{}`.
///
/// As characters are scanned left-to-right, every opening delimiter is pushed
/// onto the stack. When a closing delimiter is encountered, the stack is popped
/// and checked for a matching opening symbol. If the stack is empty on closing,
/// or symbols do not match, or unclosed delimiters remain at EOF, the input is
/// invalid.
///
/// **Real-world use case:** Syntax analysis and linting in compilers, IDE code
/// editors (matching bracket highlighting), JSON/XML payload validation, and
/// mathematical formula parsers.
///
/// Complexity Analysis:
/// Time Complexity: O(n) where n is the length of the string (single pass)
/// Auxiliary Space: O(n) worst-case stack depth for nested opening brackets
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 4.
///
/// @see ArrayStack
public final class BalancedBracketValidator {

  private BalancedBracketValidator() {
    // Utility class
  }

  /// Determines whether all brackets `()`, `[]`, and `{}` in the given
  /// expression are correctly paired and nested. Non-bracket characters are
  /// ignored.
  ///
  /// @param expression the string containing code or mathematical expressions
  /// @return true if all brackets are balanced and correctly nested; false otherwise
  public static boolean isBalanced(String expression) {
    if (expression == null) {
      return true;
    }

    StackInterface<Character> stack = new ArrayStack<>(Math.max(16, expression.length()));

    for (int i = 0; i < expression.length(); i++) {
      char ch = expression.charAt(i);

      if (isOpening(ch)) {
        stack.push(ch);
      } else if (isClosing(ch)) {
        if (stack.isEmpty()) {
          return false;
        }
        char top = stack.pop();
        if (!isMatchingPair(top, ch)) {
          return false;
        }
      }
    }

    return stack.isEmpty();
  }

  /// Finds the index of the first syntax violation (unmatched closing bracket
  /// or start of unclosed opening bracket). Returns -1 if fully balanced.
  ///
  /// @param expression the string to analyze
  /// @return 0-based index of the first unbalanced bracket, or -1 if valid
  public static int findMismatchIndex(String expression) {
    if (expression == null || expression.isEmpty()) {
      return -1;
    }

    StackInterface<Integer> indexStack = new ArrayStack<>(expression.length());
    StackInterface<Character> charStack = new ArrayStack<>(expression.length());

    for (int i = 0; i < expression.length(); i++) {
      char ch = expression.charAt(i);

      if (isOpening(ch)) {
        charStack.push(ch);
        indexStack.push(i);
      } else if (isClosing(ch)) {
        if (charStack.isEmpty()) {
          return i;
        }
        char top = charStack.pop();
        indexStack.pop();
        if (!isMatchingPair(top, ch)) {
          return i;
        }
      }
    }

    if (!indexStack.isEmpty()) {
      return indexStack.pop();
    }

    return -1;
  }

  private static boolean isOpening(char ch) {
    return ch == '(' || ch == '[' || ch == '{';
  }

  private static boolean isClosing(char ch) {
    return ch == ')' || ch == ']' || ch == '}';
  }

  private static boolean isMatchingPair(char open, char close) {
    return (open == '(' && close == ')')
        || (open == '[' && close == ']')
        || (open == '{' && close == '}');
  }
}
