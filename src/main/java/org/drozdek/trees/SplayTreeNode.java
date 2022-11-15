package org.drozdek.trees;

public class SplayTreeNode<T> extends BinarySearchTreeNode<T> {
    protected BinarySearchTreeNode<T> parent;

    public SplayTreeNode() {
        left = right = parent = null;
    }

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
}
