package org.drozdek.trees.nodes;

import java.util.Iterator;
import java.util.LinkedList;

/// ADT node for AVL tree. Stores key, height, and left/right child references.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/introduction-to-avl-tree/)
public class AvlTreeNode {
    private final int key;
    private int height;
    private AvlTreeNode left;
    private AvlTreeNode right;

    public AvlTreeNode(int data) {
        this(data, null, null);
    }

    public AvlTreeNode(int data, AvlTreeNode left, AvlTreeNode right) {
        this.key = data;
        this.left = left;
        this.right = right;
        this.height = 1;
    }

    public int getKey() {
        return key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AvlTreeNode getLeft() {
        return left;
    }

    public void setLeft(AvlTreeNode left) {
        this.left = left;
    }

    public AvlTreeNode getRight() {
        return right;
    }

    public void setRight(AvlTreeNode right) {
        this.right = right;
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        LinkedList<AvlTreeNode> children = new LinkedList<>();
        children.add(this.left);
        children.add(this.right);

        buffer.append(prefix);
        buffer.append(this.key);
        buffer.append(System.lineSeparator());

        for (Iterator<AvlTreeNode> it = children.iterator();it.hasNext();) {
            AvlTreeNode next = it.next();

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
