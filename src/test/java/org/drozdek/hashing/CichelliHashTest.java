package org.drozdek.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CichelliHashTest {

    @Test
    @DisplayName("Produces distinct slots for a small known key set")
    void perfectHash_distinct() {
        List<String> keys = Arrays.asList("and", "for", "the", "you");
        int[] slots = new int[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            slots[i] = CichelliHash.perfectHash(keys, keys.get(i));
        }
        for (int i = 0; i < slots.length; i++) {
            assertTrue(slots[i] >= 0 && slots[i] < keys.size() * 2, "slot in range");
            for (int j = i + 1; j < slots.length; j++) {
                assertNotEquals(slots[i], slots[j], "slots distinct");
            }
        }
    }

    @Test
    @DisplayName("Same key set yields a consistent slot for a key")
    void perfectHash_consistent() {
        List<String> keys = Arrays.asList("one", "two", "six");
        int first = CichelliHash.perfectHash(keys, "two");
        int second = CichelliHash.perfectHash(keys, "two");
        assertEquals(first, second);
    }

    @Test
    @DisplayName("Returns -1 for a key not in the set")
    void perfectHash_unknownKey() {
        List<String> keys = Arrays.asList("alpha", "beta");
        assertEquals(-1, CichelliHash.perfectHash(keys, "gamma"));
    }
}
