package org.drozdek.trees;

import org.drozdek.trees.nodes.SplayTreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeRotationTest {

    @Test
    @DisplayName("Semi-splay zig-right when node is right child of root")
    void semiSplayZigRight() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.insert(10);
        tree.insert(30);

        SplayTreeNode<Integer> node = tree.root;
        while (node.getRight() != null) node = node.getRight();

        tree.semiSplay(node);
        assertEquals(30, tree.root.getKey());
    }

    @Test
    @DisplayName("Semi-splay zig-left when node is left child of root")
    void semiSplayZigLeft() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.insert(30);
        tree.insert(10);

        SplayTreeNode<Integer> node = tree.root;
        while (node.getLeft() != null) node = node.getLeft();

        tree.semiSplay(node);
        assertEquals(10, tree.root.getKey());
    }

    @Test
    @DisplayName("Semi-splay zig-zig left-left (parent becomes root)")
    void semiSplayZigZigLeft() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

        SplayTreeNode<Integer> node = tree.root;
        while (node.getLeft() != null) node = node.getLeft();

        tree.semiSplay(node);
        // semi-splay brings parent (20) to root, not the accessed node
        assertEquals(20, tree.root.getKey());
        assertEquals(3, tree.size());
    }

    @Test
    @DisplayName("Semi-splay zig-zig right-right (parent becomes root)")
    void semiSplayZigZigRight() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        SplayTreeNode<Integer> node = tree.root;
        while (node.getRight() != null) node = node.getRight();

        tree.semiSplay(node);
        // semi-splay brings parent (20) to root, not the accessed node
        assertEquals(20, tree.root.getKey());
        assertEquals(3, tree.size());
    }

    @Test
    @DisplayName("Semi-splay zig-zag left-right")
    void semiSplayZigZagLeftRight() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);

        SplayTreeNode<Integer> node = tree.root.getLeft().getRight();

        tree.semiSplay(node);
        assertEquals(20, tree.root.getKey());
        assertEquals(3, tree.size());
    }

    @Test
    @DisplayName("Semi-splay zig-zag right-left")
    void semiSplayZigZagRightLeft() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);

        SplayTreeNode<Integer> node = tree.root.getRight().getLeft();

        tree.semiSplay(node);
        assertEquals(20, tree.root.getKey());
        assertEquals(3, tree.size());
    }

    @Test
    @DisplayName("Semi-splay on node that is already root")
    void semiSplayAlreadyRoot() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.insert(42);

        tree.semiSplay(tree.root);
        assertEquals(42, tree.root.getKey());
        assertEquals(1, tree.size());
    }

    @Test
    @DisplayName("Semi-splay null node")
    void semiSplayNullNode() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.insert(10);

        assertThrows(NullPointerException.class, () -> tree.semiSplay(null));
    }
}
