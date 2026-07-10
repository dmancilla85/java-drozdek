package org.drozdek.trees;

import org.drozdek.trees.nodes.HeapNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapNodeTest {
    @Test
    @DisplayName("Default constructor initializes with zero")
    void defaultConstructor() {
        HeapNode node = new HeapNode();
        assertEquals(0, node.getValue());
    }

    @Test
    @DisplayName("Constructor with value")
    void constructorWithValue() {
        HeapNode node = new HeapNode(42);
        assertEquals(42, node.getValue());
    }

    @Test
    @DisplayName("Set and get value")
    void setAndGetValue() {
        HeapNode node = new HeapNode();
        node.setValue(99);
        assertEquals(99, node.getValue());
    }

    @Test
    @DisplayName("Copy constructor returns equal but different instance")
    void copyNode() {
        HeapNode original = new HeapNode(77);
        HeapNode cloned = new HeapNode(original.getValue());
        assertNotSame(original, cloned);
        assertEquals(original.getValue(), cloned.getValue());
    }

    @Test
    @DisplayName("CompareTo returns zero for equal values")
    void compareToEqual() {
        HeapNode a = new HeapNode(5);
        HeapNode b = new HeapNode(5);
        assertEquals(0, a.compareTo(b));
    }

    @Test
    @DisplayName("CompareTo returns negative when less")
    void compareToLess() {
        HeapNode a = new HeapNode(3);
        HeapNode b = new HeapNode(7);
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    @DisplayName("CompareTo returns positive when greater")
    void compareToGreater() {
        HeapNode a = new HeapNode(10);
        HeapNode b = new HeapNode(4);
        assertTrue(a.compareTo(b) > 0);
    }

    @Test
    @DisplayName("ToString returns value as string")
    void testToString() {
        HeapNode node = new HeapNode(55);
        assertEquals("55", node.toString());
    }

    @Test
    @DisplayName("Equals returns true for same value")
    void equalsSame() {
        HeapNode a = new HeapNode(42);
        HeapNode b = new HeapNode(42);
        assertEquals(a, b);
    }

    @Test
    @DisplayName("Equals returns false for different values")
    void equalsDifferent() {
        HeapNode a = new HeapNode(42);
        HeapNode b = new HeapNode(99);
        assertNotEquals(a, b);
    }

    @Test
    @DisplayName("Equals returns false for null")
    void equalsNull() {
        HeapNode a = new HeapNode(42);
        assertNotEquals(null, a);
    }

    @Test
    @DisplayName("Equals returns true for same reference")
    void equalsReflexive() {
        HeapNode a = new HeapNode(42);
        assertEquals(a, a);
    }

    @Test
    @DisplayName("Hash code is consistent with equals")
    void hashCodeConsistent() {
        HeapNode a = new HeapNode(42);
        HeapNode b = new HeapNode(42);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("Hash code differs for different values")
    void hashCodeDifferent() {
        HeapNode a = new HeapNode(42);
        HeapNode b = new HeapNode(99);
        assertNotEquals(a.hashCode(), b.hashCode());
    }
}
