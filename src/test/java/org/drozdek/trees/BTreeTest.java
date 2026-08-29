package org.drozdek.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BTreeTest {

    @Test
    @DisplayName("Single insert is found")
    void insert_single() {
        BTree tree = new BTree();
        tree.insert(10);
        assertTrue(tree.contains(10));
        assertEquals(0, tree.height());
    }

    @Test
    @DisplayName("Many inserts builds a balanced tree containing all keys")
    void insert_many() {
        BTree tree = new BTree();
        for (int i = 0; i < 50; i++) {
            tree.insert(i);
        }
        for (int i = 0; i < 50; i++) {
            assertTrue(tree.contains(i), "contains " + i);
        }
        assertFalse(tree.contains(100));
    }

    @Test
    @DisplayName("Inserting in sorted order keeps the tree shallow")
    void insert_sortedStaysBalanced() {
        BTree tree = new BTree();
        for (int i = 0; i < 1000; i++) {
            tree.insert(i);
        }
        assertTrue(tree.height() < 10);
    }

    @Test
    @DisplayName("Empty tree reports no keys")
    void contains_empty() {
        assertFalse(new BTree().contains(1));
    }
}
