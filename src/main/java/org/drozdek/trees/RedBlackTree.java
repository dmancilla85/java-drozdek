package org.drozdek.trees;

import org.drozdek.trees.interfaces.TreeInterface;
import org.drozdek.trees.nodes.RedBlackTreeNode;

/// Red-black tree — a self-balancing binary search tree that guarantees
/// O(log n) operations by enforcing five colour invariants.
///
/// <p><b>Real-world use case:</b> Backbone of Java's {@link java.util.TreeMap}
/// and {@link java.util.TreeSet}, Linux kernel Completely Fair Scheduler,
/// and many in-memory associative containers.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) for search, insert, and delete
/// Auxiliary Space: O(log n) for recursion stack; O(n) for storage
///
/// @see <a href="https://en.wikipedia.org/wiki/Red-black_tree">Red-black tree (Wikipedia)</a>
public class RedBlackTree<T extends Comparable<T>> implements TreeInterface {
    private RedBlackTreeNode<T> root;
    private int nodeCount;

    public RedBlackTree() {
        root = null;
        nodeCount = 0;
    }

    // ---- Public operations ----

    /// Inserts a key into the tree and restores red-black invariants.
    ///
    /// @param key Value to insert
    public void insert(T key) {
        RedBlackTreeNode<T> newNode = new RedBlackTreeNode<>(key);
        insertBst(newNode);
        fixInsert(newNode);
        nodeCount++;
    }

    /// Searches for a key in the tree.
    ///
    /// @param key Value to find
    /// @return The key if found, or {@code null}
    public T search(T key) {
        RedBlackTreeNode<T> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.getKey());
            if (cmp == 0) {
                return node.getKey();
            }
            node = (cmp < 0) ? node.getLeft() : node.getRight();
        }
        return null;
    }

    /// Returns the minimum key in the tree.
    ///
    /// @return The smallest key, or {@code null} if empty
    public T minimum() {
        if (root == null) {
            return null;
        }
        RedBlackTreeNode<T> node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getKey();
    }

    /// Returns the maximum key in the tree.
    ///
    /// @return The largest key, or {@code null} if empty
    public T maximum() {
        if (root == null) {
            return null;
        }
        RedBlackTreeNode<T> node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getKey();
    }

    /// In-order traversal as a space-separated string.
    ///
    /// @return Ordered key representation
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString().trim();
    }

    // ---- TreeInterface ----

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return nodeCount;
    }

    @Override
    public String toString() {
        return inOrder();
    }

    // ---- Private helpers ----

    private void inOrder(RedBlackTreeNode<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), sb);
        if (!sb.isEmpty()) {
            sb.append(" ");
        }
        sb.append(node.getKey());
        inOrder(node.getRight(), sb);
    }

    private void insertBst(RedBlackTreeNode<T> z) {
        RedBlackTreeNode<T> y = null;
        RedBlackTreeNode<T> x = root;

        while (x != null) {
            y = x;
            if (z.getKey().compareTo(x.getKey()) < 0) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        z.setParent(y);
        if (y == null) {
            root = z;
        } else if (z.getKey().compareTo(y.getKey()) < 0) {
            y.setLeft(z);
        } else {
            y.setRight(z);
        }
    }

    private void fixInsert(RedBlackTreeNode<T> z) {
        while (z.getParent() != null && z.getParent().isRed()) {
            RedBlackTreeNode<T> uncle = z.getUncle();

            if (uncle != null && uncle.isRed()) {
                // Case 1: recolour
                z.getParent().setColor(RedBlackTreeNode.BLACK);
                uncle.setColor(RedBlackTreeNode.BLACK);
                RedBlackTreeNode<T> g = z.getParent().getParent();
                g.setColor(RedBlackTreeNode.RED);
                z = g;
            } else {
                // Cases 2 & 3: rotations
                if (z.getParent() == z.getParent().getParent().getLeft()) {
                    if (z == z.getParent().getRight()) {
                        z = z.getParent();
                        rotateLeft(z);
                    }
                    z.getParent().setColor(RedBlackTreeNode.BLACK);
                    z.getParent().getParent().setColor(RedBlackTreeNode.RED);
                    rotateRight(z.getParent().getParent());
                } else {
                    if (z == z.getParent().getLeft()) {
                        z = z.getParent();
                        rotateRight(z);
                    }
                    z.getParent().setColor(RedBlackTreeNode.BLACK);
                    z.getParent().getParent().setColor(RedBlackTreeNode.RED);
                    rotateLeft(z.getParent().getParent());
                }
            }
        }
        root.setColor(RedBlackTreeNode.BLACK);
    }

    private void rotateLeft(RedBlackTreeNode<T> x) {
        RedBlackTreeNode<T> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    private void rotateRight(RedBlackTreeNode<T> y) {
        RedBlackTreeNode<T> x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != null) {
            x.getRight().setParent(y);
        }
        x.setParent(y.getParent());
        if (y.getParent() == null) {
            root = x;
        } else if (y == y.getParent().getLeft()) {
            y.getParent().setLeft(x);
        } else {
            y.getParent().setRight(x);
        }
        x.setRight(y);
        y.setParent(x);
    }
}
