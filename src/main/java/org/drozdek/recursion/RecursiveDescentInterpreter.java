package org.drozdek.recursion;

import java.util.HashMap;
import java.util.Map;

/// Recursive-descent interpreter for a small arithmetic expression language.
///
/// Parses expressions built from integers, the operators {@code + - * / %},
/// parentheses, and variables via mutually recursive grammar rules (expression
/// -> term -> factor). Each rule recurses down the input string and returns
/// the parsed value plus the next unconsumed position.
///
/// **Real-world use case:** The parse-evaluate pattern behind calculators,
/// expression evaluators, and config formula engines.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for an expression of n tokens
/// Auxiliary Space: O(n) for the recursion stack
///
/// Bibliography:
///
/// - Recursive descent parser. *Wikipedia*. https://en.wikipedia.org/wiki/Recursive_descent_parser
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
public final class RecursiveDescentInterpreter {

    private RecursiveDescentInterpreter() {
        // do nothing
    }

    /// Evaluates an arithmetic expression with the given variable bindings.
    ///
    /// @param expression expression to evaluate
    /// @param variables  map of variable names to values
    /// @return the computed value
    public static double evaluate(String expression, Map<String, Double> variables) {
        Parser parser = new Parser(expression, variables);
        double result = parser.parseExpression();
        return result;
    }

    private static final class Parser {
        private final String input;
        private final Map<String, Double> variables;
        private int position;

        private Parser(String input, Map<String, Double> variables) {
            this.input = input == null ? "" : input.replaceAll("\\s+", "");
            this.variables = variables == null ? new HashMap<>() : variables;
            this.position = 0;
        }

        private double parseExpression() {
            double value = parseTerm();
            while (position < input.length()) {
                char op = input.charAt(position);
                if (op == '+' || op == '-') {
                    position++;
                    double right = parseTerm();
                    value = op == '+' ? value + right : value - right;
                } else {
                    break;
                }
            }
            return value;
        }

        private double parseTerm() {
            double value = parseFactor();
            while (position < input.length()) {
                char op = input.charAt(position);
                if (op == '*' || op == '/' || op == '%') {
                    position++;
                    double right = parseFactor();
                    value = op == '*' ? value * right : op == '/' ? value / right : value % right;
                } else {
                    break;
                }
            }
            return value;
        }

        private double parseFactor() {
            if (position < input.length() && input.charAt(position) == '(') {
                position++;
                double value = parseExpression();
                if (position < input.length() && input.charAt(position) == ')') {
                    position++;
                }
                return value;
            }
            if (position < input.length() && (input.charAt(position) == '+' || input.charAt(position) == '-')) {
                char sign = input.charAt(position);
                position++;
                double value = parseFactor();
                return sign == '-' ? -value : value;
            }
            return parseNumberOrVariable();
        }

        private double parseNumberOrVariable() {
            int start = position;
            boolean isVariable = position < input.length() && Character.isLetter(input.charAt(position));
            while (position < input.length()) {
                char c = input.charAt(position);
                if (Character.isDigit(c) || c == '.' || (isVariable && Character.isLetterOrDigit(c))) {
                    position++;
                } else {
                    break;
                }
            }
            String token = input.substring(start, position);
            if (isVariable) {
                Double value = variables.get(token);
                return value == null ? 0.0 : value;
            }
            return token.isEmpty() ? 0.0 : Double.parseDouble(token);
        }

        private static boolean isOperator(char c) {
            return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
        }
    }
}
