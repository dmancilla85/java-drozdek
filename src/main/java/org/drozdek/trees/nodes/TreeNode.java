package org.drozdek.trees.nodes;

/// Simple binary tree node. Stores an Object label and left/right child references.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1)
///
/// Source: Generic binary tree node
public class TreeNode {
    private Object label;
    private TreeNode leftChild;
    private TreeNode rightChild;

    /// Creates an empty node without a label or children.
    public TreeNode() {
        label = null;
        leftChild = rightChild = null;
    }

    /// Creates a leaf node carrying the given label.
    ///
    /// @param label value stored in the node
    public TreeNode(Object label) {
        this.label = label;
        leftChild = rightChild = null;
    }

    /// Creates a node with a label and both children.
    ///
    /// @param label value stored in the node
    /// @param left  left child reference
    /// @param right right child reference
    public TreeNode(Object label, TreeNode left, TreeNode right) {
        this.rightChild = right;
        this.leftChild = left;
        this.label = label;
    }

    /// Creates an unlabeled node linking two children.
    ///
    /// @param left  left child reference
    /// @param right right child reference
    public TreeNode(TreeNode left, TreeNode right) {
        this.rightChild = right;
        this.leftChild = left;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }
}
