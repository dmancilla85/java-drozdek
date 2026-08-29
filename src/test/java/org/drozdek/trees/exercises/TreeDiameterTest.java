package org.drozdek.trees.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.drozdek.trees.nodes.BinarySearchTreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TreeDiameterTest {

    @Test
    @DisplayName("Diameter of a single node is one")
    void diameter_single() {
        assertEquals(1, TreeDiameter.diameter(new BinarySearchTreeNode<>(1)));
    }

    @Test
    @DisplayName("Diameter of a two-node chain is two")
    void diameter_twoNodes() {
        BinarySearchTreeNode<Integer> root = new BinarySearchTreeNode<>(2);
        root.setLeft(new BinarySearchTreeNode<>(1));
        assertEquals(2, TreeDiameter.diameter(root));
    }

    @Test
    @DisplayName("Diameter of a null tree is zero")
    void diameter_null() {
        assertEquals(0, TreeDiameter.diameter(null));
    }

    @Test
    @DisplayName("Diameter spans the two deepest leaves")
    void diameter_wide() {
        // root -> left subtree of depth 3, right subtree of depth 2
        BinarySearchTreeNode<Integer> n4 = new BinarySearchTreeNode<>(4);
        BinarySearchTreeNode<Integer> n5 = new BinarySearchTreeNode<>(5);
        BinarySearchTreeNode<Integer> n2 = new BinarySearchTreeNode<>(2, n4, n5);
        BinarySearchTreeNode<Integer> n6 = new BinarySearchTreeNode<>(6);
        BinarySearchTreeNode<Integer> n7 = new BinarySearchTreeNode<>(7);
        BinarySearchTreeNode<Integer> n3 = new BinarySearchTreeNode<>(3, n6, n7);
        BinarySearchTreeNode<Integer> root = new BinarySearchTreeNode<>(1, n2, n3);
        assertEquals(5, TreeDiameter.diameter(root));
    }
}
