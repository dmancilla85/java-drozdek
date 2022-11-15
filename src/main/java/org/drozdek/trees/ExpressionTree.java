package org.drozdek.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The structure of Expression Tree is a binary tree to evaluate certain expressions. All leaves of the Expression Tree
 * have a number string value. All non-leaves of the Expression Tree have an operator string value.
 * <p>
 * Source: <a href="https://wxx5433.gitbooks.io/interview-preparation/content/part_ii_leetcode_lintcode/stack/expression_tree_build.html">Here</a>
 */
public class ExpressionTree {
    ExpressionTreeNode root;

    public ExpressionTree() {
        root = null;
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    // generate expression tree and return the root node
    public static ExpressionTreeNode insertPostFix(String postfix) {
        // stack to hold character
        Deque<ExpressionTreeNode> st = new ArrayDeque<>();

        // to create a temp node with l and r as left and right subtree
        ExpressionTreeNode l;
        ExpressionTreeNode r;
        ExpressionTreeNode temp;

        // traversing the postfix expression
        for (int i = 0; i < postfix.length(); i++) {
            // a character is found
            // push it into the stack
            if (!isOperator(postfix.charAt(i))) {
                temp = new ExpressionTreeNode(postfix.charAt(i));
                st.push(temp);
            } else {
                // creating new temp node
                temp = new ExpressionTreeNode(postfix.charAt(i));

                // getting two items from our stack
                l = st.pop();
                r = st.pop();

                // attaching the above two items as the left and right child of our
                // temp node
                temp.left = r;
                temp.right = l;

                // pushing the temp node into our stack
                st.push(temp);
            }
        }

        // getting the root node
        temp = st.pop();

        // returning the root node
        return temp;
    }

    // print the inorder traversal of tree
    public static void inorder(ExpressionTreeNode root) {
        // return if root is null
        if (root == null) return;

        // inorder traversal
        inorder(root.left);
        System.out.print(root.symbol);
        inorder(root.right);
    }

    // main function
    public static void main(String[] args) {
        // postfix expression
        String expression = "AB*CD/+";
        // is A*B+C/D

        // calling our algorithm
        ExpressionTreeNode root = insertPostFix(expression);

        System.out.print("Inorder Expression: ");
        // printing the expression tree inorder
        inorder(root);
        System.out.println();
    }

    private int compute(int num2, int num1, char token) {
        return switch (token) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            default -> num1 / num2;
        };
    }

    public int evaluateExpression(String expression) {
        if (expression == null || expression.length() == 0) {
            return 0;
        }
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();
        for (char token : expression.toCharArray()) {
            if (isNumber(token)) {
                numStack.push((int) token);
            } else if (token == '(') {
                opStack.push(token);
            } else if (token == ')') {
                while (opStack.peek() != '(') {
                    numStack.push(compute(numStack.pop(), numStack.pop(), opStack.pop()));
                }
                opStack.pop();  // pop out "("
            } else {
                while (!opStack.isEmpty() && getPriority(opStack.peek()) >= getPriority(token)) {
                    numStack.push(compute(numStack.pop(), numStack.pop(), opStack.pop()));
                }
                opStack.push(token);
            }
        }
        while (!opStack.isEmpty()) {
            numStack.push(compute(numStack.pop(), numStack.pop(), opStack.pop()));
        }
        return numStack.isEmpty() ? 0 : numStack.pop();
    }

    private int getPriority(char op) {
        if (op == '(') {
            return 0;
        } else if (op == '+' || op == '-') {
            return 1;
        } else {
            return 2;
        }
    }

    private boolean isNumber(char token) {
        return Character.isDigit(token);
    }
}
