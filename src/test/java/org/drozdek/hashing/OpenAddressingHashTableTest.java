package org.drozdek.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OpenAddressingHashTableTest {

    @Test
    @DisplayName("Put and get round-trips a value")
    void put_get() {
        OpenAddressingHashTable<String, Integer> table = new OpenAddressingHashTable<>();
        table.put("one", 1);
        assertEquals(1, table.get("one"));
    }

    @Test
    @DisplayName("Put over an existing key replaces the value")
    void put_overwrites() {
        OpenAddressingHashTable<String, Integer> table = new OpenAddressingHashTable<>();
        table.put("one", 1);
        table.put("one", 11);
        assertEquals(11, table.get("one"));
        assertEquals(1, table.size());
    }

    @Test
    @DisplayName("Handles collisions via linear probing")
    void put_collisions() {
        OpenAddressingHashTable<Integer, String> table = new OpenAddressingHashTable<>(4);
        table.put(0, "a");
        table.put(4, "b");
        table.put(8, "c");
        assertEquals("a", table.get(0));
        assertEquals("b", table.get(4));
        assertEquals("c", table.get(8));
        assertEquals(3, table.size());
    }

    @Test
    @DisplayName("Get returns null for absent key")
    void get_absent() {
        OpenAddressingHashTable<String, Integer> table = new OpenAddressingHashTable<>();
        assertNull(table.get("missing"));
    }

    @Test
    @DisplayName("Remove deletes an entry and leaves tombstone probe chain intact")
    void remove_entry() {
        OpenAddressingHashTable<Integer, String> table = new OpenAddressingHashTable<>(4);
        table.put(0, "a");
        table.put(4, "b");
        assertEquals("a", table.remove(0));
        assertNull(table.get(0));
        assertEquals("b", table.get(4));
        assertEquals(1, table.size());
    }

    @Test
    @DisplayName("Re-inserting after removal reuses the slot")
    void put_afterRemove() {
        OpenAddressingHashTable<Integer, String> table = new OpenAddressingHashTable<>(4);
        table.put(0, "a");
        table.put(4, "b");
        table.remove(0);
        table.put(0, "z");
        assertEquals("z", table.get(0));
        assertEquals("b", table.get(4));
    }

    @Test
    @DisplayName("Empty and containsKey")
    void containsAndEmpty() {
        OpenAddressingHashTable<String, Integer> table = new OpenAddressingHashTable<>();
        assertTrue(table.isEmpty());
        table.put("k", 1);
        assertFalse(table.isEmpty());
        assertTrue(table.containsKey("k"));
        assertFalse(table.containsKey("n"));
    }

    @Test
    @DisplayName("Grows past the load factor threshold")
    void put_grows() {
        OpenAddressingHashTable<Integer, Integer> table = new OpenAddressingHashTable<>(2);
        for (int i = 0; i < 20; i++) {
            table.put(i, i);
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(i, table.get(i));
        }
        assertEquals(20, table.size());
    }
}
