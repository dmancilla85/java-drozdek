package org.drozdek.hashing.applications;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VirtualDiskHashIndexTest {

    @Test
    @DisplayName("Tracks allocated disk pages")
    void allocate_findsPages() {
        VirtualDiskHashIndex index = new VirtualDiskHashIndex();
        index.allocatePage(5);
        index.allocatePage(9);
        index.allocatePage(26);
        assertTrue(index.isAllocated(5));
        assertTrue(index.isAllocated(26));
        assertFalse(index.isAllocated(7));
    }

    @Test
    @DisplayName("Directory depth and bucket count grow with inserts")
    void allocate_directoryRegrows() {
        VirtualDiskHashIndex index = new VirtualDiskHashIndex(2);
        for (int i = 0; i < 12; i++) {
            index.allocatePage(i);
        }
        assertTrue(index.globalDepth() > 0);
        assertTrue(index.bucketCount() >= 1);
    }
}
