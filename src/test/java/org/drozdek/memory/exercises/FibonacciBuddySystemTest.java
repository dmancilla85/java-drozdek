package org.drozdek.memory.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FibonacciBuddySystemTest {

    @Test
    @DisplayName("Capacity is a Fibonacci number at least the requested size")
    void getCapacity_roundsToFibonacci() {
        FibonacciBuddySystem allocator = new FibonacciBuddySystem(8);
        assertEquals(8, allocator.getCapacity());
    }

    @Test
    @DisplayName("Allocating the full capacity consumes everything")
    void allocate_fullCapacity() {
        FibonacciBuddySystem allocator = new FibonacciBuddySystem(8);
        long start = allocator.allocate(8);
        assertEquals(0, start);
        assertEquals(-1, allocator.allocate(1));
    }

    @Test
    @DisplayName("Freeing restores the total free count")
    void free_restoresCapacity() {
        FibonacciBuddySystem allocator = new FibonacciBuddySystem(8);
        long start = allocator.allocate(3);
        assertNotEquals(-1, start);
        allocator.free(start);
        assertEquals(allocator.getCapacity(), allocator.getTotalFree());
    }

    @Test
    @DisplayName("Two small allocations can both be served")
    void allocate_twoSmallBlocks() {
        FibonacciBuddySystem allocator = new FibonacciBuddySystem(8);
        long first = allocator.allocate(2);
        long second = allocator.allocate(2);
        assertNotEquals(-1, first);
        assertNotEquals(-1, second);
        assertNotEquals(first, second);
    }

    @Test
    @DisplayName("Merging buddies reduces the free block count")
    void free_mergesBuddies() {
        FibonacciBuddySystem allocator = new FibonacciBuddySystem(8);
        long first = allocator.allocate(1);
        allocator.free(first);
        int countAfterOne = allocator.getFreeBlockCount();
        assertEquals(1, countAfterOne);
        assertEquals(allocator.getCapacity(), allocator.getTotalFree());
    }
}
