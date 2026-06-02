package org.drozdek.trees;

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
    protected final char symbol;
    protected ExpressionTreeNode left;
    protected ExpressionTreeNode right;

    public ExpressionTreeNode(char data) {
        this(data, null, null);
    }

    public ExpressionTreeNode(char data, ExpressionTreeNode left, ExpressionTreeNode right) {
        this.symbol = data;
        this.left = left;
        this.right = right;
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        LinkedList<ExpressionTreeNode> children = new LinkedList<>();
        children.add(this.left);
        children.add(this.right);

        buffer.append(prefix);
        buffer.append(this.symbol);
        buffer.append(System.lineSeparator());

        for (Iterator<ExpressionTreeNode> it = children.iterator();it.hasNext();) {
            ExpressionTreeNode next = it.next();

            if (next == null)
                continue;

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
