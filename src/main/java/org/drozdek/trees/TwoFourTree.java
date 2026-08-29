package org.drozdek.trees;

import java.util.ArrayList;
import java.util.List;

/// 2-3-4 tree (order-4 B-tree).
///
/// A self-balancing multi-way search tree in which every node holds one, two,
/// or three keys and has two, three, or four children respectively. All leaves
/// are at the same depth. Insertion splits 4-nodes pre-emptively on the way
/// down, which keeps every node at or below its maximum occupancy.
///
/// **Real-world use case:** An efficient search structure that maps directly
/// to red-black trees and is used as the conceptual basis for many balanced
/// tree implementations.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) search/insert
/// Auxiliary Space: O(n) storage, O(1) auxiliary per operation
///
/// Bibliography:
///
/// - 2–3–4 tree. *Wikipedia*. https://en.wikipedia.org/wiki/2%E2%80%933%E2%80%934_tree
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
public class TwoFourTree {

    private Node root;

    private static final class Node {
        private final List<Integer> keys = new ArrayList<>();
        private final List<Node> children = new ArrayList<>();
        private boolean leaf = true;

        private boolean is4Node() {
            return keys.size() == 3;
        }
    }

    /// Creates an empty 2-3-4 tree.
    public TwoFourTree() {
        root = null;
    }

    /// Inserts a key into the tree, splitting 4-nodes on the way down.
    ///
    /// @param key key to insert
    public void insert(int key) {
        if (root == null) {
            root = new Node();
            root.keys.add(key);
            return;
        }
        if (root.is4Node()) {
            root = splitRoot(root);
        }
        insertInto(root, key);
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

    private void insertInto(Node node, int key) {
        int i = 0;
        while (i < node.keys.size() && key > node.keys.get(i)) {
            i++;
        }
        if (i < node.keys.size() && node.keys.get(i) == key) {
            return;
        }
        if (node.leaf) {
            node.keys.add(i, key);
            return;
        }
        Node child = node.children.get(i);
        if (child.is4Node()) {
            int median = child.keys.get(1);
            Node left = child;
            Node right = new Node();
            right.leaf = child.leaf;
            right.keys.add(child.keys.get(2));
            left.keys.remove(2);
            left.keys.remove(1);
            if (!child.leaf) {
                right.children.add(child.children.get(2));
                right.children.add(child.children.get(3));
                child.children.remove(3);
                child.children.remove(2);
            }
            node.keys.add(i, median);
            node.children.add(i + 1, right);
            if (key > median) {
                insertInto(right, key);
            } else {
                insertInto(left, key);
            }
        } else {
            insertInto(child, key);
        }
    }

    private Node splitRoot(Node fourNode) {
        Node newRoot = new Node();
        newRoot.leaf = false;
        newRoot.keys.add(fourNode.keys.get(1));
        Node left = new Node();
        left.leaf = fourNode.leaf;
        left.keys.add(fourNode.keys.get(0));
        Node right = new Node();
        right.leaf = fourNode.leaf;
        right.keys.add(fourNode.keys.get(2));
        if (!fourNode.leaf) {
            left.children.add(fourNode.children.get(0));
            left.children.add(fourNode.children.get(1));
            right.children.add(fourNode.children.get(2));
            right.children.add(fourNode.children.get(3));
        }
        newRoot.children.add(left);
        newRoot.children.add(right);
        return newRoot;
    }

    /// Returns the height of the tree.
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
