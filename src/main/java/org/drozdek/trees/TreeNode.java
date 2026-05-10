package org.drozdek.trees;

public class TreeNode {
    private Object label;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode() {
        label = null;
        leftChild = rightChild = null;
    }

    public TreeNode(Object label) {
        this.label = label;
        leftChild = rightChild = null;
    }

    public TreeNode(Object label, TreeNode left, TreeNode right) {
        this.rightChild = right;
        this.leftChild = left;
        this.label = label;
    }

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
