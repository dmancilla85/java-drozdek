package org.drozdek.trees;

import java.util.ArrayList;
import java.util.List;

/// B-Tree of a fixed minimum degree.
///
/// A self-balancing multi-way search tree in which every node holds between
/// {@code t-1} and {@code 2t-1} keys and every non-root internal node has
/// between {@code t} and {@code 2t} children. All leaves reside at the same
/// depth, keeping every search/insert/delete operation logarithmic even for
/// large trees that span multiple disk pages.
///
/// **Real-world use case:** Database and file-system indexes (e.g. B-tree
/// indexes in relational databases) that must minimise disk accesses.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) search/insert
/// Auxiliary Space: O(n) storage, O(1) auxiliary per operation
///
/// Bibliography:
///
/// - B-tree. *Wikipedia*. https://en.wikipedia.org/wiki/B-tree
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
public class BTree {

    private static final int MIN_DEGREE = 2;

    private Node root;

    private static final class Node {
        private final List<Integer> keys = new ArrayList<>();
        private final List<Node> children = new ArrayList<>();
        private boolean leaf = true;

        private boolean isFull() {
            return keys.size() >= 2 * MIN_DEGREE - 1;
        }
    }

    /// Creates an empty B-tree with the default minimum degree.
    public BTree() {
        root = null;
    }

    /// Inserts a key, splitting nodes on the way down.
    ///
    /// @param key key to insert
    public void insert(int key) {
        if (root == null) {
            root = new Node();
            root.keys.add(key);
            return;
        }
        if (root.isFull()) {
            Node newRoot = new Node();
            newRoot.leaf = false;
            newRoot.children.add(root);
            splitChild(newRoot, 0);
            root = newRoot;
        }
        insertNonFull(root, key);
    }

    /// Returns `true` if the tree contains the given key.
    ///
    /// @param key key to look up
    /// @return `true` if present
    public boolean contains(int key) {
        return root != null && search(root, key);
    }

    private boolean search(Node node, int key) {
        int i = 0;
        while (i < node.keys.size() && key > node.keys.get(i)) {
            i++;
        }
        if (i < node.keys.size() && node.keys.get(i) == key) {
            return true;
        }
        if (node.leaf) {
            return false;
        }
        return search(node.children.get(i), key);
    }

    private void insertNonFull(Node node, int key) {
        int i = node.keys.size() - 1;
        if (node.leaf) {
            node.keys.add(-1);
            while (i >= 0 && key < node.keys.get(i)) {
                node.keys.set(i + 1, node.keys.get(i));
                i--;
            }
            node.keys.set(i + 1, key);
        } else {
            while (i >= 0 && key < node.keys.get(i)) {
                i--;
            }
            i++;
            Node child = node.children.get(i);
            if (child.isFull()) {
                splitChild(node, i);
                if (key > node.keys.get(i)) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key);
        }
    }

    private void splitChild(Node parent, int index) {
        Node full = parent.children.get(index);
        Node brother = new Node();
        brother.leaf = full.leaf;
        int median = full.keys.get(MIN_DEGREE - 1);
        for (int i = MIN_DEGREE; i < full.keys.size(); i++) {
            brother.keys.add(full.keys.get(i));
        }
        for (int i = 0; i < MIN_DEGREE; i++) {
            full.keys.remove(full.keys.size() - 1);
        }
        if (!full.leaf) {
            for (int i = MIN_DEGREE; i < full.children.size(); i++) {
                brother.children.add(full.children.get(i));
            }
            int removedChildren = full.children.size() - MIN_DEGREE;
            for (int i = 0; i < removedChildren; i++) {
                full.children.remove(full.children.size() - 1);
            }
        }
        parent.children.add(index + 1, brother);
        parent.keys.add(index, median);
    }

    /// Returns the height of the tree (number of levels above the leaves).
    ///
    /// @return height of the tree, or `-1` when the tree is empty
    public int height() {
        if (root == null) {
            return -1;
        }
        int depth = 0;
        Node node = root;
        while (!node.leaf) {
            node = node.children.get(0);
            depth++;
        }
        return depth;
    }
}
