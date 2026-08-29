package org.drozdek.memory.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeapMemoryManagerTest {

    @Test
    @DisplayName("Buddy allocator hands out and reclaims power-of-two blocks")
    void buddyAllocator_allocFree() {
        HeapMemoryManager manager = new HeapMemoryManager(16);
        assertEquals(16, manager.buddyFreeUnits());
        int block = manager.buddyAllocate(8);
        assertEquals(0, block);
        assertEquals(8, manager.buddyFreeUnits());
        manager.buddyFree(block);
        assertEquals(16, manager.buddyFreeUnits());
    }

    @Test
    @DisplayName("Sequential allocator hands out and reclaims arbitrary sizes")
    void sequentialAllocator_allocFree() {
        HeapMemoryManager manager = new HeapMemoryManager(16);
        int address = manager.sequentialAllocate(5);
        assertEquals(0, address);
        assertEquals(11, manager.sequentialFreeUnits());
        manager.sequentialFree(address);
        assertEquals(16, manager.sequentialFreeUnits());
    }
}
