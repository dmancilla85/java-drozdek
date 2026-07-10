package org.drozdek.trees;

import org.drozdek.trees.nodes.TrieLeaf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieLeafTest {

    @Test
    @DisplayName("Create leaf with suffix")
    void createLeaf() {
        TrieLeaf leaf = new TrieLeaf("hello");
        assertEquals("hello", leaf.getSuffix());
    }

    @Test
    @DisplayName("Leaf is marked as leaf")
    void isLeaf() {
        TrieLeaf leaf = new TrieLeaf("test");
        assertTrue(leaf.isLeaf());
    }

    @Test
    @DisplayName("ToString returns the suffix")
    void testToString() {
        TrieLeaf leaf = new TrieLeaf("world");
        assertEquals("world", leaf.toString());
    }

    @Test
    @DisplayName("Create with empty string suffix")
    void emptySuffix() {
        TrieLeaf leaf = new TrieLeaf("");
        assertEquals("", leaf.getSuffix());
        assertTrue(leaf.isLeaf());
    }
}
