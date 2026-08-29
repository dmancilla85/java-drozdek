package org.drozdek.searching.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JumpLookupTableTest {

    @Test
    @DisplayName("Jump search resolves a subscriber name")
    void findSubscriber_found() {
        int[] phone = {10, 25, 40, 55, 70, 85, 100};
        String[] names = {"a", "b", "c", "d", "e", "f", "g"};
        assertEquals("d", JumpLookupTable.findSubscriber(phone, names, 55));
    }

    @Test
    @DisplayName("Returns null for an absent number")
    void findSubscriber_absent() {
        int[] phone = {1, 2, 3};
        String[] names = {"a", "b", "c"};
        assertNull(JumpLookupTable.findSubscriber(phone, names, 9));
    }

    @Test
    @DisplayName("Linear scan finds an index")
    void scanFor_found() {
        int[] phone = {4, 1, 7, 3};
        assertEquals(2, JumpLookupTable.scanFor(phone, 7));
    }
}
