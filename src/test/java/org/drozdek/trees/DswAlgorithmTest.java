package org.drozdek.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.drozdek.trees.nodes.BinarySearchTreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DswAlgorithmTest {

    @Test
    @DisplayName("Balancing a skewed tree reduces its height")
    void balance_reducesHeight() {
        BinarySearchTreeNode<Integer> root = skewedTree(15);
        int skewedHeight = height(root);
        BinarySearchTreeNode<Integer> balanced = DswAlgorithm.balance(root);
        int balancedHeight = height(balanced);
        assertTrue(balancedHeight < skewedHeight, "height reduced");
        assertTrue(balancedHeight <= 4, "near-optimal height for 15 nodes");
    }

    @Test
    @DisplayName("Balancing preserves the in-order key sequence")
    void balance_preservesOrder() {
        BinarySearchTreeNode<Integer> root = skewedTree(10);
        List<Integer> before = inOrder(root);
        BinarySearchTreeNode<Integer> balanced = DswAlgorithm.balance(root);
        assertEquals(before, inOrder(balanced));
    }

    @Test
    @DisplayName("Balancing a null root returns null")
    void balance_null() {
        assertEquals(null, DswAlgorithm.balance(null));
    }

    @Test
    @DisplayName("A larger tree balances to logarithmic height")
    void balance_largeTree() {
        BinarySearchTreeNode<Integer> root = skewedTree(1023);
        BinarySearchTreeNode<Integer> balanced = DswAlgorithm.balance(root);
        assertTrue(height(balanced) <= 10, "log height for 1023 nodes");
    }

    private static BinarySearchTreeNode<Integer> skewedTree(int nodes) {
        BinarySearchTreeNode<Integer> root = null;
        for (int i = 0; i < nodes; i++) {
            root = insert(root, i);
        }
        return root;
    }

    private static BinarySearchTreeNode<Integer> insert(BinarySearchTreeNode<Integer> node, int key) {
        if (node == null) {
            return new BinarySearchTreeNode<>(key);
        }
        if (key < node.getKey()) {
            node.setLeft(insert(node.getLeft(), key));
        } else {
            node.setRight(insert(node.getRight(), key));
        }
        return node;
    }

    private static int height(BinarySearchTreeNode<Integer> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    private static List<Integer> inOrder(BinarySearchTreeNode<Integer> node) {
        List<Integer> result = new ArrayList<>();
        inOrder(node, result);
        return result;
    }

    private static void inOrder(BinarySearchTreeNode<Integer> node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), result);
        result.add(node.getKey());
        inOrder(node.getRight(), result);
    }
}
