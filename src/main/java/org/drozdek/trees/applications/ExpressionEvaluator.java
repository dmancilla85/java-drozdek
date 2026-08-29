package org.drozdek.trees.applications;

import org.drozdek.trees.ExpressionTree;

/// Arithmetic expression evaluator built on the `ExpressionTree` ADT.
///
/// A postfix (reverse Polish) expression is compiled into an expression tree
/// whose leaves are operands and internal nodes are operators. The same tree can
/// be rendered back in infix form, and arbitrary infix strings can be evaluated
/// with correct operator precedence and parentheses.
///
/// **Real-world use case:** Calculator engines, spreadsheet formula evaluation,
/// and compiler front-ends that lower expressions into syntax trees.
///
/// Complexity Analysis:
/// Time Complexity: O(n) to build and to evaluate
/// Auxiliary Space: O(n) for the tree
///
/// Bibliography:
///
/// - Binary expression tree. *Wikipedia*. https://en.wikipedia.org/wiki/Binary_expression_tree
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
///
/// @see ExpressionTree
public class ExpressionEvaluator {

    private final ExpressionTree tree;

    /// Compiles a postfix expression into an evaluable expression tree.
    ///
    /// @param postfix expression in postfix notation, e.g. {@code "23*4+"}
    public ExpressionEvaluator(String postfix) {
        this.tree = new ExpressionTree(postfix);
    }

    /// Evaluates an infix expression with precedence and parentheses.
    ///
    /// @param infix expression such as {@code "3*(4+2)"}
    /// @return the numeric result
    public static int evaluateInfix(String infix) {
        return ExpressionTree.evaluateExpression(infix);
    }

    /// Renders the compiled tree in infix (in-order) form.
    ///
    /// @return concatenated symbols visited in order
    public String infixForm() {
        return tree.inorder();
    }

    /// Returns the number of nodes in the compiled tree.
    ///
    /// @return node count
    public int nodeCount() {
        return tree.size();
    }

    /// Returns whether the compiled tree is empty.
    ///
    /// @return true when no expression was compiled
    public boolean isEmpty() {
        return tree.isEmpty();
    }
}
