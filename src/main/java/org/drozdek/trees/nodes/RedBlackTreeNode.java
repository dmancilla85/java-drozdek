package org.drozdek.trees.nodes;

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

    @Override
    public String toString() {
        return (color == RED ? "R" : "B") + ":" + key;
    }
}
