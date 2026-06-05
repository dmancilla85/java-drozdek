package org.drozdek.trees;

import org.drozdek.trees.interfaces.TreeInterface;

import java.util.ArrayList;

/// Simple n-ary tree data structure. Each node can have an arbitrary number of children stored in an ArrayList.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for traversal, O(n) for search
/// Auxiliary Space: O(n) for storage
///
/// Source: Generic n-ary tree
public class SimpleTree<T> implements TreeInterface {

    protected TreeNode<T> root;
    private int nodeCount;

    public SimpleTree() {
        root = null;
        nodeCount = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return nodeCount;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void insertElement(TreeNode<T> parent, T label) {
        TreeNode<T> newNode = new TreeNode<>(label);
        if (parent == null) {
            root = newNode;
        } else {
            parent.children.add(newNode);
        }
        nodeCount++;
    }

    public TreeNode<T> findNode(T label) {
        return findNodeRecursive(root, label);
    }

    private TreeNode<T> findNodeRecursive(TreeNode<T> node, T label) {
        if (node == null) return null;
        if (node.label.equals(label)) return node;
        for (TreeNode<T> child : node.children) {
            TreeNode<T> found = findNodeRecursive(child, label);
            if (found != null) return found;
        }
        return null;
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix, TreeNode<T> node) {
        if (node == null) return;
        buffer.append(prefix);
        buffer.append(node.label);
        buffer.append(System.lineSeparator());

        int childCount = node.children.size();
        for (int i = 0; i < childCount; i++) {
            TreeNode<T> child = node.children.get(i);
            boolean isLast = (i == childCount - 1);
            if (isLast) {
                print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", child);
            } else {
                print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", child);
            }
        }
    }

    @Override
    public String toString() {
        if (root == null) return System.lineSeparator() + "<EMPTY>" + System.lineSeparator();
        StringBuilder buffer = new StringBuilder(50);
        buffer.append(System.lineSeparator());
        print(buffer, "", "", root);
        return buffer.toString();
    }

    public static class TreeNode<T> {
        protected T label;
        protected ArrayList<TreeNode<T>> children;

        TreeNode(T label) {
            this.label = label;
            children = new ArrayList<>();
        }

        public T getLabel() {
            return label;
        }

        public void setLabel(T label) {
            this.label = label;
        }
    }
}
