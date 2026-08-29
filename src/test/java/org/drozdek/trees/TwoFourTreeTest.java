package org.drozdek.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TwoFourTreeTest {

    @Test
    @DisplayName("Single insert is found")
    void insert_single() {
        TwoFourTree tree = new TwoFourTree();
        tree.insert(5);
        assertTrue(tree.contains(5));
        assertEquals(0, tree.height());
    }

    @Test
    @DisplayName("Many inserts contain all keys")
    void insert_many() {
        TwoFourTree tree = new TwoFourTree();
        for (int i = 0; i < 60; i++) {
            tree.insert(i);
        }
        for (int i = 0; i < 60; i++) {
            assertTrue(tree.contains(i), "contains " + i);
        }
        assertFalse(tree.contains(999));
    }

    @Test
    @DisplayName("Duplicate inserts are ignored")
    void insert_duplicates() {
        TwoFourTree tree = new TwoFourTree();
        tree.insert(7);
        tree.insert(7);
        assertTrue(tree.contains(7));
        assertEquals(0, tree.height());
    }

    @Test
    @DisplayName("Sorted insertion stays shallow")
    void insert_sortedStaysBalanced() {
        TwoFourTree tree = new TwoFourTree();
        for (int i = 0; i < 500; i++) {
            tree.insert(i);
        }
        assertTrue(tree.height() < 10);
    }
}
