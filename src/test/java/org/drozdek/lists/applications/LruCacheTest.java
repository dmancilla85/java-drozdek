package org.drozdek.lists.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LruCacheTest {

    @Test
    @DisplayName("Stores and retrieves a value")
    void get_afterPut() {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.put(1, "a");
        assertEquals("a", cache.get(1));
    }

    @Test
    @DisplayName("Returns null for a missing key")
    void get_missing() {
        assertNull(new LruCache<Integer, String>(2).get(99));
    }

    @Test
    @DisplayName("Evicts the least recently used entry when full")
    void put_evictsLru() {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.get(1);
        cache.put(3, "c");
        assertNull(cache.get(2));
        assertEquals("a", cache.get(1));
        assertEquals(2, cache.size());
    }
}
