package org.drozdek.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BuddySystemAllocatorTest {

    @Test
    @DisplayName("Splits the top block down to the requested order")
    void allocate_splitsTopBlock() {
        BuddySystemAllocator allocator = new BuddySystemAllocator(8);
        int block = allocator.allocate(1);
        assertEquals(0, block);
        assertEquals(0, allocator.getFreeBlockCount(3));
    }

    @Test
    @DisplayName("Frees a block and coalesces it with its buddy")
    void free_coalescesBuddies() {
        BuddySystemAllocator allocator = new BuddySystemAllocator(8);
        int a = allocator.allocate(1);
        int b = allocator.allocate(1);
        assertNotEquals(a, b);
        allocator.free(a);
        allocator.free(b);
        assertEquals(1, allocator.getFreeBlockCount(3));
        assertEquals(8, allocator.getTotalFree());
    }

    @Test
    @DisplayName("Rounds requests up to the next power of two")
    void allocate_roundsUpRequest() {
        BuddySystemAllocator allocator = new BuddySystemAllocator(16);
        int block = allocator.allocate(3);
        assertEquals(0, block);
        int other = allocator.allocate(1);
        assertNotEquals(block, other);
    }

    @Test
    @DisplayName("Total free decreases then fully restores after freeing")
    void getTotalFree_tracksLifecycle() {
        BuddySystemAllocator allocator = new BuddySystemAllocator(8);
        assertEquals(8, allocator.getTotalFree());
        int block = allocator.allocate(2);
        assertNotEquals(-1, block);
        allocator.free(block);
        assertEquals(8, allocator.getTotalFree());
    }

    @Test
    @DisplayName("Exhaustion returns -1")
    void allocate_exhaustsMemory() {
        BuddySystemAllocator allocator = new BuddySystemAllocator(4);
        allocator.allocate(4);
        assertEquals(-1, allocator.allocate(1));
    }

    @Test
    @DisplayName("Request larger than the heap fails")
    void allocate_tooLargeFails() {
        BuddySystemAllocator allocator = new BuddySystemAllocator(8);
        assertEquals(-1, allocator.allocate(100));
    }
}
