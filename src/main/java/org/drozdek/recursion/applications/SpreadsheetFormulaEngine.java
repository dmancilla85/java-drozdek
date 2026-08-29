package org.drozdek.recursion.applications;

import java.util.Map;
import org.drozdek.recursion.RecursiveDescentInterpreter;

/// Spreadsheet formula engine that evaluates arithmetic expressions.
///
/// Formulas using integers, the operators {@code + - * / %} and parentheses,
/// optionally referencing named cells, are evaluated by a recursive-descent
/// parser over the grammar expression -> term -> factor.
///
/// **Real-world use case:** In-cell formula evaluation in spreadsheets,
/// calculator engines, and configurable pricing/formula subsystems.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for an expression of n tokens
/// Auxiliary Space: O(n) for the recursion stack
///
/// Bibliography:
///
/// - Recursive descent parser. *Wikipedia*. https://en.wikipedia.org/wiki/Recursive_descent_parser
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
///
/// @see RecursiveDescentInterpreter
public final class SpreadsheetFormulaEngine {

    private SpreadsheetFormulaEngine() {
        // do nothing
    }

    /// Evaluates an arithmetic formula against the supplied variable bindings.
    ///
    /// @param formula   expression to evaluate
    /// @param variables map of cell names to numeric values
    /// @return the computed value
    public static double evaluate(String formula, Map<String, Double> variables) {
        return RecursiveDescentInterpreter.evaluate(formula, variables);
    }
}
