package org.drozdek.trees.nodes;

import java.util.Iterator;
import java.util.LinkedList;

/// Node for a red-black tree. Stores a comparable key, colour flag,
/// and references to left, right, and parent nodes.
public class RedBlackTreeNode<T extends Comparable<T>> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private T key;
    private boolean color;
    private RedBlackTreeNode<T> left;
    private RedBlackTreeNode<T> right;
    private RedBlackTreeNode<T> parent;

    public RedBlackTreeNode(T key) {
        this.key = key;
        this.color = RED;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    // ---- Getters / Setters ----

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public RedBlackTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(RedBlackTreeNode<T> left) {
        this.left = left;
    }

    public RedBlackTreeNode<T> getRight() {
        return right;
    }

    public void setRight(RedBlackTreeNode<T> right) {
        this.right = right;
    }

    public RedBlackTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(RedBlackTreeNode<T> parent) {
        this.parent = parent;
    }

    // ---- Helpers ----

    public boolean isRed() {
        return color == RED;
    }

    public boolean isBlack() {
        return color == BLACK;
    }

    public RedBlackTreeNode<T> getUncle() {
        if (parent == null || parent.getParent() == null) {
            return null;
        }
        if (parent == parent.getParent().getLeft()) {
            return parent.getParent().getRight();
        } else {
            return parent.getParent().getLeft();
        }
    }

    /// Renders the subtree rooted at this node as an indented tree diagram,
    /// annotating every line with the node colour (`[R]` or `[B]`).
    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(this.key).append(" [").append(isRed() ? 'R' : 'B').append(']');
        buffer.append(System.lineSeparator());

        LinkedList<RedBlackTreeNode<T>> children = new LinkedList<>();
        if (this.left != null) children.add(this.left);
        if (this.right != null) children.add(this.right);

        for (Iterator<RedBlackTreeNode<T>> it = children.iterator(); it.hasNext();) {
            RedBlackTreeNode<T> next = it.next();
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
