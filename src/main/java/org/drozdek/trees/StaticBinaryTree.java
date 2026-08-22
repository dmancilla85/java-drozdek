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
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
public class StaticBinaryTree implements TreeInterface {

    private static final int CAPACITY = 10;
    private final HeapNode[] tree;
    private int size;

    public StaticBinaryTree() {
        tree = new HeapNode[CAPACITY];
        size = 0;
    }

    public StaticBinaryTree(HeapNode root) {
        tree = new HeapNode[CAPACITY];
        tree[0] = root;
        size = 1;
    }

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

    public boolean setRightmostChild(HeapNode node) {
        int index = (size - 1) % 2 != 0 ? size - 1 : size - 2;
        if (index < 0 || index >= CAPACITY) {
            return false;
        }
        tree[index] = node;
        return true;
    }

    public boolean setLeftmostChild(HeapNode node) {
        int index = (size - 1) % 2 == 0 ? size - 1 : size - 2;
        if (index < 0 || index >= CAPACITY) {
            return false;
        }
        tree[index] = node;
        return true;
    }

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

    public HeapNode leftChild(int parent) {
        try {
            return tree[(parent * 2) + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            LoggerService.logError(e.getMessage());
        }

        return null;
    }

    public Object rightmostChild() {
        if ((size - 1) % 2 != 0)
            return tree[size - 1];
        else
            return tree[size - 2];
    }

    public Object leftmostChild() {
        if ((size - 1) % 2 == 0)
            return tree[size - 1];
        else
            return tree[size - 2];
    }

    @SuppressWarnings("java:S4144")
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
