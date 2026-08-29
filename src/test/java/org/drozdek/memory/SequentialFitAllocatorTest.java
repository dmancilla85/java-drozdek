package org.drozdek.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SequentialFitAllocatorTest {

    @Test
    @DisplayName("First fit allocates from the front of the heap")
    void allocate_firstFit() {
        SequentialFitAllocator allocator = new SequentialFitAllocator(100, SequentialFitAllocator.Strategy.FIRST_FIT);
        assertEquals(0, allocator.allocate(40));
        assertEquals(40, allocator.allocate(40));
    }

    @Test
    @DisplayName("Best fit selects the smallest sufficient free segment")
    void allocate_bestFit() {
        SequentialFitAllocator allocator = new SequentialFitAllocator(100, SequentialFitAllocator.Strategy.BEST_FIT);
        assertEquals(0, allocator.allocate(40));
        assertEquals(40, allocator.allocate(40));
        allocator.free(40);
        assertEquals(40, allocator.allocate(5));
        assertEquals(45, allocator.allocate(40));
    }

    @Test
    @DisplayName("Worst fit selects the largest free segment")
    void allocate_worstFit() {
        SequentialFitAllocator allocator = new SequentialFitAllocator(100, SequentialFitAllocator.Strategy.WORST_FIT);
        assertEquals(0, allocator.allocate(40));
        assertEquals(40, allocator.allocate(40));
        allocator.free(40);
        assertEquals(40, allocator.allocate(30));
    }

    @Test
    @DisplayName("Failing allocation returns -1")
    void allocate_returnsMinusOneWhenExhausted() {
        SequentialFitAllocator allocator = new SequentialFitAllocator(10, SequentialFitAllocator.Strategy.FIRST_FIT);
        assertEquals(-1, allocator.allocate(100));
        assertEquals(0, allocator.allocate(10));
        assertEquals(-1, allocator.allocate(1));
    }

    @Test
    @DisplayName("Free restores capacity and reduces segment count")
    void free_mergesAdjacentSegments() {
        SequentialFitAllocator allocator = new SequentialFitAllocator(100, SequentialFitAllocator.Strategy.FIRST_FIT);
        allocator.allocate(10);
        allocator.allocate(10);
        allocator.allocate(10);
        allocator.free(10);
        allocator.free(0);
        assertEquals(90, allocator.getTotalFree());
        assertEquals(2, allocator.getFreeSegmentCount());
    }

    @Test
    @DisplayName("Total free tracks allocation and release")
    void getTotalFree_reflectsState() {
        SequentialFitAllocator allocator = new SequentialFitAllocator(100, SequentialFitAllocator.Strategy.FIRST_FIT);
        assertEquals(100, allocator.getTotalFree());
        allocator.allocate(30);
        assertEquals(70, allocator.getTotalFree());
        allocator.free(0);
        assertEquals(100, allocator.getTotalFree());
    }

    @Test
    @DisplayName("Internal fragmentation leaves free memory behind")
    void allocate_leavesRemainderFree() {
        SequentialFitAllocator allocator = new SequentialFitAllocator(100, SequentialFitAllocator.Strategy.FIRST_FIT);
        assertEquals(0, allocator.allocate(35));
        assertTrue(allocator.getTotalFree() < 100);
    }
}
