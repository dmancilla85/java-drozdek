package org.drozdek.trees;

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
