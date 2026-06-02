package org.drozdek.trees;

/// Node for in-threaded binary tree. Stores key, left/right child references, and a successor flag.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/threaded-binary-tree/)
public class IntThreadedTreeNode {
    protected final int key;
    protected boolean successor;
    protected IntThreadedTreeNode left;
    protected IntThreadedTreeNode right;

    public IntThreadedTreeNode(int value) {
        this(value, null, null);
    }

    public IntThreadedTreeNode(int value, IntThreadedTreeNode left, IntThreadedTreeNode right) {
        this.key = value;
        this.left = left;
        this.right = right;
        successor = false;
    }
}
