package org.drozdek.hashing.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoubleHashingTableTest {

    @Test
    @DisplayName("Put and get round-trips a value")
    void put_get() {
        DoubleHashingTable table = new DoubleHashingTable();
        table.put(5, 50);
        assertEquals(50, table.get(5));
    }

    @Test
    @DisplayName("Handles keys that collide on the primary hash")
    void put_primaryCollision() {
        DoubleHashingTable table = new DoubleHashingTable(11);
        table.put(0, 10);
        table.put(11, 20);
        assertEquals(10, table.get(0));
        assertEquals(20, table.get(11));
    }

    @Test
    @DisplayName("Returns null for an absent key")
    void get_absent() {
        DoubleHashingTable table = new DoubleHashingTable();
        table.put(1, 2);
        assertNull(table.get(999));
    }

    @Test
    @DisplayName("Overwrites an existing key")
    void put_overwrite() {
        DoubleHashingTable table = new DoubleHashingTable();
        table.put(7, 1);
        table.put(7, 2);
        assertEquals(2, table.get(7));
    }

    @Test
    @DisplayName("Tracks size")
    void size_tracks() {
        DoubleHashingTable table = new DoubleHashingTable();
        assertEquals(0, table.size());
        table.put(1, 1);
        table.put(2, 2);
        assertEquals(2, table.size());
    }
}
