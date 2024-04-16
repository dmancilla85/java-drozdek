package org.drozdek.trees;

import java.util.Iterator;
import java.util.LinkedList;

public class BinarySearchTreeNode<T> {
    protected Comparable<T> key;
    protected BinarySearchTreeNode<T> left;
    protected BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode() {
        this(null, null, null);
    }

    public BinarySearchTreeNode(Comparable<T> data) {
        this(data, null, null);
    }

    public BinarySearchTreeNode(Comparable<T> data, BinarySearchTreeNode<T> left, BinarySearchTreeNode<T> right) {
        this.key = data;
        this.left = left;
        this.right = right;
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        LinkedList<BinarySearchTreeNode<T>> children = new LinkedList<>();
        children.add(this.left);
        children.add(this.right);
        buffer.append(prefix);
        buffer.append(this.key);
        buffer.append(System.lineSeparator());

        for (Iterator<BinarySearchTreeNode<T>> it = children.iterator(); it.hasNext(); ) {
            BinarySearchTreeNode<T> next = it.next();

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
