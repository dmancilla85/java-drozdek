package org.drozdek.graphs;

import org.drozdek.graphs.unlam.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {
    Set<String> set;

    @BeforeEach
    void setUp() {
        set = new Set<>();
    }

    @Test
    @DisplayName("New set is empty")
    void isEmpty() {
        assertEquals(0, set.size());
    }

    @Test
    @DisplayName("Add elements to set")
    void add() {
        set.add("hello");
        set.add("world");
        assertEquals(2, set.size());
    }

    @Test
    @DisplayName("Add null does nothing")
    void addNull() {
        set.add(null);
        assertEquals(0, set.size());
    }

    @Test
    @DisplayName("Contains returns true for added element")
    void contains() {
        set.add("test");
        assertTrue(set.contains("test"));
    }

    @Test
    @DisplayName("Contains returns false for missing element")
    void containsNotFound() {
        assertFalse(set.contains("missing"));
    }

    @Test
    @DisplayName("Contains null returns false")
    void containsNull() {
        assertFalse(set.contains(null));
    }

    @Test
    @DisplayName("Remove element")
    void remove() {
        set.add("a");
        set.add("b");
        set.remove("a");
        assertEquals(1, set.size());
        assertFalse(set.contains("a"));
    }

    @Test
    @DisplayName("Remove null does nothing")
    void removeNull() {
        set.remove(null);
        assertEquals(0, set.size());
    }

    @Test
    @DisplayName("Remove non-existent element")
    void removeNonExistent() {
        set.add("a");
        set.remove("b");
        assertEquals(1, set.size());
    }

    @Test
    @DisplayName("Subset check")
    void isSubsetOf() {
        Set<String> other = new Set<>();
        other.add("a");
        other.add("b");
        set.add("a");
        set.add("b");
        set.add("c");
        assertTrue(set.isSubsetOf(other));
    }

    @Test
    @DisplayName("isSubsetOf checks if this contains all of B's elements")
    void isSubsetOfEmpty() {
        set.add("a");
        Set<String> other = new Set<>();
        assertTrue(set.isSubsetOf(other));
    }

    @Test
    @DisplayName("Remove all elements from another set")
    void removeAll() {
        set.add("a");
        set.add("b");
        set.add("c");
        Set<String> other = new Set<>();
        other.add("a");
        other.add("c");
        set.removeAll(other);
        assertEquals(1, set.size());
        assertTrue(set.contains("b"));
    }

    @Test
    @DisplayName("Union with another set")
    void union() {
        set.add("a");
        set.add("b");
        Set<String> other = new Set<>();
        other.add("c");
        other.add("d");
        set.union(other);
        assertEquals(4, set.size());
    }

    @Test
    @DisplayName("Constructor with initial capacity")
    void constructorWithCapacity() {
        Set<Integer> s = new Set<>(10);
        assertEquals(0, s.size());
    }

    @Test
    @DisplayName("Constructor with zero capacity defaults to null elements")
    void constructorZeroCapacity() {
        Set<Integer> s = new Set<>(0);
        assertThrows(NullPointerException.class, s::size);
    }
}
