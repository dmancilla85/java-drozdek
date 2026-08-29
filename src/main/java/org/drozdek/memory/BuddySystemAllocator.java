package org.drozdek.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/// Buddy-system memory allocator that splits memory into power-of-two blocks
/// and coalesces buddy blocks back together when both become free.
///
/// Each allocator order holds free blocks of {@code 2^order} units. Allocating
/// rounds the request up to the next power of two and, if necessary, splits a
/// larger block down to the required size. Freeing a block attempts to merge
/// it with its buddy of equal size and propagates the merge upward.
///
/// **Real-world use case:** Linux kernel buddy allocator, jemalloc arenas, and
/// fast, low-fragmentation embedded heaps.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) for both allocate and free (walks the order chain)
/// Auxiliary Space: O(n) for the free lists and bookkeeping maps
///
/// Bibliography:
///
/// - Donald Knuth, *The Art of Computer Programming*, Vol. 1, Section 2.5.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
public class BuddySystemAllocator {

    private final int maxOrder;
    private final int totalUnits;
    private final TreeSet<Integer>[] freeBlocks;
    private final Map<Integer, Integer> allocatedOrder = new HashMap<>();

    /// Creates a buddy allocator over a heap of the given power-of-two size.
    ///
    /// @param heapSize total heap size in allocation units, rounded up to a
    ///                 power of two
    public BuddySystemAllocator(int heapSize) {
        this.maxOrder = ceilOrder(heapSize);
        this.totalUnits = 1 << maxOrder;
        this.freeBlocks = new TreeSet[maxOrder + 1];
        for (int i = 0; i <= maxOrder; i++) {
            freeBlocks[i] = new TreeSet<>();
        }
        freeBlocks[maxOrder].add(0);
    }

    /// Allocates a block large enough for the requested size, returning its
    /// starting unit index, or {@code -1} when memory is exhausted.
    ///
    /// @param size number of units requested
    /// @return starting unit index, or -1 on failure
    public int allocate(int size) {
        if (size < 1) {
            return -1;
        }
        int requiredOrder = ceilOrder(size);
        if (requiredOrder > maxOrder) {
            return -1;
        }
        int current = requiredOrder;
        while (current <= maxOrder && freeBlocks[current].isEmpty()) {
            current++;
        }
        if (current > maxOrder) {
            return -1;
        }

        int block = freeBlocks[current].pollFirst();
        while (current > requiredOrder) {
            current--;
            int buddy = block + (1 << current);
            freeBlocks[current].add(buddy);
        }
        allocatedOrder.put(block, requiredOrder);
        return block;
    }

    /// Releases a block previously returned by {@link #allocate(int)}, merging
    /// it with its buddy when both are free. Unknown blocks are ignored.
    ///
    /// @param block starting unit index of the block to release
    public void free(int block) {
        Integer order = allocatedOrder.remove(block);
        if (order == null) {
            return;
        }
        int currentStart = block;
        int currentOrder = order;
        while (currentOrder < maxOrder) {
            int buddy = currentStart ^ (1 << currentOrder);
            if (!freeBlocks[currentOrder].remove(buddy)) {
                break;
            }
            currentStart = Math.min(currentStart, buddy);
            currentOrder++;
        }
        freeBlocks[currentOrder].add(currentStart);
    }

    /// Number of free blocks available at a given order.
    ///
    /// @param order block order (0 holds single units)
    /// @return count of free blocks at that order
    public int getFreeBlockCount(int order) {
        if (order < 0 || order > maxOrder) {
            return 0;
        }
        return freeBlocks[order].size();
    }

    /// Total number of free units across all order lists.
    ///
    /// @return total free unit count
    public int getTotalFree() {
        int total = 0;
        for (int i = 0; i <= maxOrder; i++) {
            total += freeBlocks[i].size() * (1 << i);
        }
        return total;
    }

    private static int ceilOrder(int value) {
        int units = 1;
        int order = 0;
        while (units < value) {
            units <<= 1;
            order++;
        }
        return order;
    }
}
