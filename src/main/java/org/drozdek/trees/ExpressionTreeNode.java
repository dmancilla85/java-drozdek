package org.drozdek.trees;

import java.util.Iterator;
import java.util.LinkedList;

public class ExpressionTreeNode {
    protected char symbol;
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
        buffer.append("< ");
        buffer.append(this.symbol);
        buffer.append(" >");
        buffer.append('\n');

        for (Iterator<ExpressionTreeNode> it = children.iterator(); it.hasNext(); ) {
            ExpressionTreeNode next = it.next();

            if(next==null)
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
        print(buffer, "", "");
        return buffer.toString();
    }
}
