package org.drozdek.stacks.applications;

import org.drozdek.stacks.LinkedStack;

/// Evaluates Reverse Polish Notation (postfix) arithmetic expressions using a
/// linked-list stack.
///
/// Tokens are scanned left to right: operands are pushed onto the stack, and an
/// operator pops the top two operands, applies the operator, and pushes the
/// result. The final value is left on top of the stack. The linked-list
/// representation grows without a fixed capacity, matching the unbounded nature
/// of a deeply nested expression.
///
/// **Real-world use case:** The evaluation engine of RPN calculators, JVM
/// bytecode execution, and compiler back-ends that produce postfix code.
///
/// Complexity Analysis:
/// Time Complexity: O(n) over the token stream
/// Auxiliary Space: O(n) for the stack
///
/// Bibliography:
///
/// - Reverse Polish notation. *Wikipedia*. https://en.wikipedia.org/wiki/Reverse_Polish_notation
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 4.
///
/// @see LinkedStack
public final class RpnCalculator {

    private static final String OPERATORS = "+-*/";

    private RpnCalculator() {
        // do nothing
    }

    /// Evaluates a space-separated postfix expression.
    ///
    /// @param expression tokens such as {@code "3 4 + 2 *"}
    /// @return the computed value
    /// @throws IllegalArgumentException if the expression is malformed
    public static int evaluate(String expression) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        String[] tokens = expression.trim().split("\\s+");
        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }
            if (OPERATORS.indexOf(token.charAt(0)) >= 0 && token.length() == 1) {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Missing operand: " + token);
                }
                int b = stack.pop();
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Missing operand: " + token);
                }
                int a = stack.pop();
                stack.push(apply(token.charAt(0), a, b));
            } else {
                try {
                    stack.push(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid token: " + token);
                }
            }
        }
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");
        }
        int result = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Too many operands");
        }
        return result;
    }

    private static int apply(char operator, int a, int b) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }
}
