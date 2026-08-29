package org.drozdek.trees;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.interfaces.TreeInterface;
import org.drozdek.trees.nodes.HeapNode;

/// Array-based static binary tree. Uses an array to store HeapNode elements with implicit parent-child
/// indexing (left child at 2*parent, right child at 2*parent+1).
///
/// **Real-world use case:** Segment trees for range queries and binary-heap
/// storage where node positions are computed arithmetically rather than via pointers.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for get/set operations
/// Auxiliary Space: O(n) for storage
///
/// Bibliography:
///
/// - Thomas H. Cormen et al. *Introduction to Algorithms*, 4th ed. MIT Press.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
public class StaticBinaryTree implements TreeInterface {

    private static final int CAPACITY = 10;
    private final HeapNode[] tree;
    private int size;

    public StaticBinaryTree() {
        tree = new HeapNode[CAPACITY];
        size = 0;
    }

    /// Creates the tree and places the given node at the root position.
    ///
    /// @param root node stored at index 0
    public StaticBinaryTree(HeapNode root) {
        tree = new HeapNode[CAPACITY];
        tree[0] = root;
        size = 1;
    }

    /// Stores a node in the right-child slot of the given parent position.
    ///
    /// Fails when the computed index exceeds capacity or the tree is already full.
    ///
    /// @param parent index of the parent position
    /// @param node   node to store
    /// @return true when the node was placed, false otherwise
    public boolean setRightChild(int parent, HeapNode node) {
        try {
            if (((parent * 2) + 1) <= CAPACITY - 1 && !isFull()) {
                tree[(parent * 2) + 1] = node;
                size++;
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LoggerService.logError(e.getMessage());
            return false;
        }

        return false;
    }

    /// Stores a node in the left-child slot of the given parent position.
    ///
    /// Fails when the computed index exceeds capacity or the tree is already full.
    ///
    /// @param parent index of the parent position
    /// @param node   node to store
    /// @return true when the node was placed, false otherwise
    public boolean setLeftChild(int parent, HeapNode node) {
        try {
            if ((parent * 2) <= CAPACITY - 1 && !isFull()) {
                tree[parent * 2] = node;
                size++;
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LoggerService.logError(e.getMessage());
            return false;
        }

        return false;
    }

    /// Places a node at the rightmost slot of the last occupied level.
    ///
    /// The slot is derived from the current size parity and overwritten directly, without
    /// increasing the node count.
    ///
    /// @param node node to place
    /// @return true when the computed index was valid, false otherwise
    public boolean setRightmostChild(HeapNode node) {
        int index = (size - 1) % 2 != 0 ? size - 1 : size - 2;
        if (index < 0 || index >= CAPACITY) {
            return false;
        }
        tree[index] = node;
        return true;
    }

    /// Places a node at the leftmost slot of the last occupied level.
    ///
    /// The slot is derived from the current size parity and overwritten directly, without
    /// increasing the node count.
    ///
    /// @param node node to place
    /// @return true when the computed index was valid, false otherwise
    public boolean setLeftmostChild(HeapNode node) {
        int index = (size - 1) % 2 == 0 ? size - 1 : size - 2;
        if (index < 0 || index >= CAPACITY) {
            return false;
        }
        tree[index] = node;
        return true;
    }

    /// Installs the given node as root when the root position is still empty.
    ///
    /// Calls with a non-empty root or a null node are ignored.
    ///
    /// @param node node to place at index 0
    public void setRoot(HeapNode node) {
        if (tree[0] == null && node != null) {
            tree[0] = node;
            size++;
        }
    }

    public boolean isFull() {
        return size == (CAPACITY - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /// Reads the backing array at the slot adjacent to the given parent position.
    ///
    /// Accesses index `2 * parent + 1`; out-of-range accesses are logged and yield null.
    ///
    /// @param parent index of the parent position
    /// @return node at the computed slot, or null when the access falls outside the array
    public HeapNode leftChild(int parent) {
        try {
            return tree[(parent * 2) + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            LoggerService.logError(e.getMessage());
        }

        return null;
    }

    /// Returns the node stored at the rightmost occupied slot of the last level.
    ///
    /// @return node at the computed slot, which may be null
    public Object rightmostChild() {
        if ((size - 1) % 2 != 0)
            return tree[size - 1];
        else
            return tree[size - 2];
    }

    /// Returns the node stored at the leftmost occupied slot of the last level.
    ///
    /// @return node at the computed slot, which may be null
    public Object leftmostChild() {
        if ((size - 1) % 2 == 0)
            return tree[size - 1];
        else
            return tree[size - 2];
    }

    @SuppressWarnings("java:S4144")
    /// Compatibility overload that ignores its argument and behaves like leftmostChild().
    ///
    /// @param node unused parameter kept for signature compatibility
    /// @return node at the leftmost occupied slot, which may be null
    public Object leftmostChild(@SuppressWarnings("unused") int node) {
        if ((size - 1) % 2 == 0)
            return tree[size - 1];
        else
            return tree[size - 2];
    }

    @Override
    public String toString() {
        if (size == 0) return System.lineSeparator() + "<EMPTY>" + System.lineSeparator();
        StringBuilder buffer = new StringBuilder(50);
        buffer.append(System.lineSeparator());
        printNode(buffer, "", "", 0);
        return buffer.toString();
    }

    private void printNode(StringBuilder buffer, String prefix, String childrenPrefix, int index) {
        if (index >= CAPACITY || tree[index] == null) return;
        buffer.append(prefix);
        buffer.append(tree[index]);
        buffer.append(System.lineSeparator());

        int left = 2 * index + 1;
        int right = 2 * index + 2;
        boolean hasLeft = left < CAPACITY && tree[left] != null;
        boolean hasRight = right < CAPACITY && tree[right] != null;

        if (hasRight) {
            printNode(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", left);
            printNode(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", right);
        } else if (hasLeft) {
            printNode(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", left);
        }
    }
}
