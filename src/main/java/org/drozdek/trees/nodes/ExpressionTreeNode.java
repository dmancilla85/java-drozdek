package org.drozdek.trees.nodes;

import java.util.Iterator;
import java.util.LinkedList;

/// ADT node for expression tree. Stores a symbol and left/right child references.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/expression-tree/)
public class ExpressionTreeNode {
    private final char symbol;
    private ExpressionTreeNode left;
    private ExpressionTreeNode right;

    public ExpressionTreeNode(char data) {
        this(data, null, null);
    }

    public ExpressionTreeNode(char data, ExpressionTreeNode left, ExpressionTreeNode right) {
        this.symbol = data;
        this.left = left;
        this.right = right;
    }

    public char getSymbol() {
        return symbol;
    }

    public ExpressionTreeNode getLeft() {
        return left;
    }

    public void setLeft(ExpressionTreeNode left) {
        this.left = left;
    }

    public ExpressionTreeNode getRight() {
        return right;
    }

    public void setRight(ExpressionTreeNode right) {
        this.right = right;
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(this.symbol);
        buffer.append(System.lineSeparator());

        LinkedList<ExpressionTreeNode> children = new LinkedList<>();
        if (this.left != null) children.add(this.left);
        if (this.right != null) children.add(this.right);

        for (Iterator<ExpressionTreeNode> it = children.iterator(); it.hasNext();) {
            ExpressionTreeNode next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        buffer.append(System.lineSeparator());
        print(buffer, "", "");
        return buffer.toString();
    }
}
