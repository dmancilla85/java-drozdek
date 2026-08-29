package org.drozdek.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExtendibleHashingTest {

    @Test
    @DisplayName("Stores and finds keys in the same bucket")
    void insertAndContains_singleBucket() {
        ExtendibleHashing table = new ExtendibleHashing(2);
        table.insert(4);
        table.insert(5);
        assertTrue(table.contains(4));
        assertTrue(table.contains(5));
        assertFalse(table.contains(9));
    }

    @Test
    @DisplayName("Directory doubles and buckets split on overflow")
    void insert_splitsBuckets() {
        ExtendibleHashing table = new ExtendibleHashing(2);
        for (int i = 0; i < 12; i++) {
            table.insert(i);
        }
        for (int i = 0; i < 12; i++) {
            assertTrue(table.contains(i), "contains " + i);
        }
        assertTrue(table.getGlobalDepth() > 1);
        assertTrue(table.getBucketCount() >= 2);
    }

    @Test
    @DisplayName("Duplicate inserts are ignored")
    void insert_duplicatesIgnored() {
        ExtendibleHashing table = new ExtendibleHashing(2);
        table.insert(3);
        table.insert(3);
        assertTrue(table.contains(3));
        assertEquals(1, table.getBucketCount());
    }

    @Test
    @DisplayName("Absent keys are not found")
    void contains_absent() {
        ExtendibleHashing table = new ExtendibleHashing(2);
        table.insert(1);
        assertFalse(table.contains(2));
    }
}
