package org.drozdek.trees;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;

public class SplayTreeNode<T> {
    protected Comparable<T> key;
    protected SplayTreeNode<T> left;
    protected SplayTreeNode<T> right;
    protected SplayTreeNode<T> parent;


    public SplayTreeNode(Comparable<T> element, SplayTreeNode<T> left,
                         SplayTreeNode<T> right, SplayTreeNode<T> parent) {
        this.key = element;
        this.right = right;
        this.left = left;
        this.parent = parent;
    }

    public SplayTreeNode(Comparable<T> element) {
        this(element, null, null, null);
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        LinkedList<SplayTreeNode<T>> children = new LinkedList<>();
        children.add(this.left);
        children.add(this.right);
        buffer.append(prefix);
        buffer.append(this.key);
        buffer.append(System.lineSeparator());

        for (Iterator<SplayTreeNode<T>> it = children.iterator(); it.hasNext(); ) {
            SplayTreeNode<T> next = it.next();

            if(next== null)
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
