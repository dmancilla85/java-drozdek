package org.drozdek.trees.nodes;

/// Node for in-threaded binary tree. Stores key, left/right child references, and a successor flag.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/threaded-binary-tree/)
public class IntThreadedTreeNode {
    private final int key;
    private boolean successor;
    private IntThreadedTreeNode left;
    private IntThreadedTreeNode right;

    public IntThreadedTreeNode(int value) {
        this(value, null, null);
    }

    public IntThreadedTreeNode(int value, IntThreadedTreeNode left, IntThreadedTreeNode right) {
        this.key = value;
        this.left = left;
        this.right = right;
        successor = false;
    }

    public int getKey() {
        return key;
    }

    public boolean isSuccessor() {
        return successor;
    }

    public void setSuccessor(boolean successor) {
        this.successor = successor;
    }

    public IntThreadedTreeNode getLeft() {
        return left;
    }

    public void setLeft(IntThreadedTreeNode left) {
        this.left = left;
    }

    public IntThreadedTreeNode getRight() {
        return right;
    }

    public void setRight(IntThreadedTreeNode right) {
        this.right = right;
    }
}
