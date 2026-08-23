package org.drozdek.trees;

import org.drozdek.trees.interfaces.TreeInterface;

import java.util.ArrayList;

/// Simple n-ary tree data structure. Each node can have an arbitrary number of children stored in an ArrayList.
///
/// **Real-world use case:** File-system directory trees, DOM/XML document
/// representation, and organizational hierarchy charts.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for traversal, O(n) for search
/// Auxiliary Space: O(n) for storage
///
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
public class SimpleTree<T> implements TreeInterface {

    protected TreeNode<T> root;
    private int nodeCount;

    public SimpleTree() {
        root = null;
        nodeCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return nodeCount;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    /// Attaches a new node with the given label under the parent node.
    ///
    /// Passing a null parent sets the new node as the tree root.
    ///
    /// @param parent node receiving the child, or null to define the root
    /// @param label  value stored in the created node
    public void insertElement(TreeNode<T> parent, T label) {
        TreeNode<T> newNode = new TreeNode<>(label);
        if (parent == null) {
            root = newNode;
        } else {
            parent.children.add(newNode);
        }
        nodeCount++;
    }

    /// Finds the first node carrying the given label using a depth-first search.
    ///
    /// Compares labels with equals(); runs in O(n).
    ///
    /// @param label value to look for
    /// @return matching node, or null when no node carries the label
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
