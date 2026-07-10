package org.drozdek.lists.nodes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntSkipListNodeTest {

    @Test
    @DisplayName("Create node with key and level count")
    void createNode() {
        IntSkipListNode node = new IntSkipListNode(42, 3);
        assertEquals(42, node.key());
        assertEquals(3, node.next().length);
    }

    @Test
    @DisplayName("Create node with explicit next array")
    void createNodeWithArray() {
        IntSkipListNode[] next = new IntSkipListNode[2];
        IntSkipListNode node = new IntSkipListNode(7, next);
        assertEquals(7, node.key());
        assertSame(next, node.next());
    }

    @Test
    @DisplayName("Equals returns true for same key and same next")
    void equalsSame() {
        IntSkipListNode a = new IntSkipListNode(5, 2);
        IntSkipListNode b = new IntSkipListNode(5, 2);
        assertEquals(a, b);
    }

    @Test
    @DisplayName("Equals returns false for different key")
    void equalsDifferentKey() {
        IntSkipListNode a = new IntSkipListNode(5, 2);
        IntSkipListNode b = new IntSkipListNode(3, 2);
        assertNotEquals(a, b);
    }

    @Test
    @DisplayName("Equals returns true for same reference")
    void equalsSameRef() {
        IntSkipListNode node = new IntSkipListNode(1, 1);
        assertEquals(node, node);
    }

    @Test
    @DisplayName("Equals returns false for null")
    void equalsNull() {
        IntSkipListNode node = new IntSkipListNode(1, 1);
        assertNotEquals(null, node);
    }

    @Test
    @DisplayName("HashCode is consistent with equals")
    void hashCodeConsistent() {
        IntSkipListNode a = new IntSkipListNode(10, 4);
        IntSkipListNode b = new IntSkipListNode(10, 4);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("ToString returns formatted string")
    void testToString() {
        IntSkipListNode node = new IntSkipListNode(99, 2);
        assertEquals("{data: 99}", node.toString());
    }
}
