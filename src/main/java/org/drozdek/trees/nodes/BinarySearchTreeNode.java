package org.drozdek.trees.nodes;

import java.util.Iterator;
import java.util.LinkedList;

/// ADT node for binary search tree. Stores a comparable key and left/right child references.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/binary-search-tree-data-structure/)
public class BinarySearchTreeNode<T> {
    private Comparable<T> key;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;

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

    public Comparable<T> getKey() {
        return key;
    }

    public void setKey(Comparable<T> key) {
        this.key = key;
    }

    public BinarySearchTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinarySearchTreeNode<T> left) {
        this.left = left;
    }

    public BinarySearchTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinarySearchTreeNode<T> right) {
        this.right = right;
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        LinkedList<BinarySearchTreeNode<T>> children = new LinkedList<>();
        children.add(this.left);
        children.add(this.right);
        buffer.append(prefix);
        buffer.append(this.key);
        buffer.append(System.lineSeparator());

        for (Iterator<BinarySearchTreeNode<T>> it = children.iterator();it.hasNext();) {
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
