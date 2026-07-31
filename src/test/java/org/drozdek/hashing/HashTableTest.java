package org.drozdek.hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HashTable Tests")
class HashTableTest {
    private HashTable<String, Integer> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
    }

    @Test
    @DisplayName("New table is empty")
    void newTableIsEmpty() {
        assertTrue(table.isEmpty());
        assertEquals(0, table.size());
    }

    @Test
    @DisplayName("Put and get a single entry")
    void putAndGet() {
        table.put("one", 1);
        assertEquals(1, table.size());
        assertEquals(Integer.valueOf(1), table.get("one"));
    }

    @Test
    @DisplayName("Get returns null for missing key")
    void getMissing() {
        assertNull(table.get("nonexistent"));
    }

    @Test
    @DisplayName("Put updates existing key")
    void putUpdates() {
        table.put("key", 1);
        table.put("key", 2);
        assertEquals(Integer.valueOf(2), table.get("key"));
        assertEquals(1, table.size());
    }

    @Test
    @DisplayName("Remove deletes entry and returns value")
    void removeEntry() {
        table.put("a", 10);
        assertEquals(Integer.valueOf(10), table.remove("a"));
        assertNull(table.get("a"));
        assertTrue(table.isEmpty());
    }

    @Test
    @DisplayName("Remove missing returns null")
    void removeMissing() {
        assertNull(table.remove("nothing"));
    }

    @Test
    @DisplayName("ContainsKey works correctly")
    void containsKey() {
        table.put("x", 42);
        assertTrue(table.containsKey("x"));
        assertFalse(table.containsKey("y"));
    }

    @Test
    @DisplayName("Handles null key")
    void nullKey() {
        table.put(null, 0);
        assertEquals(Integer.valueOf(0), table.get(null));
        assertTrue(table.containsKey(null));
        table.remove(null);
        assertNull(table.get(null));
    }

    @Test
    @DisplayName("Many insertions trigger resize without losing data")
    void manyInsertions() {
        for (int i = 0; i < 100; i++) {
            table.put("key" + i, i);
        }
        assertEquals(100, table.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(Integer.valueOf(i), table.get("key" + i));
        }
    }
}
