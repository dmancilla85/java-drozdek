package org.drozdek.memory.applications;

import org.drozdek.memory.BuddySystemAllocator;
import org.drozdek.memory.SequentialFitAllocator;

/// Unified process-heap manager exposing both a buddy-system allocator and a
/// sequential-fit allocator over the same logical heap.
///
/// The buddy allocator fast-tracks power-of-two requests, while the
/// sequential-fit allocator handles arbitrary sizes using a best-fit policy.
/// Free-space totals from each scheme are exposed for capacity monitoring.
///
/// **Real-world use case:** Embedded and kernel heaps that combine a buddy
/// allocator for page frames with a general-purpose sequential allocator for
/// variable-sized objects.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) per buddy operation; O(n) worst case sequential
/// Auxiliary Space: O(n) for both free lists and segment tables
///
/// Bibliography:
///
/// - D.E. Knuth. *The Art of Computer Programming*, Vol. 1.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
///
/// @see BuddySystemAllocator
/// @see SequentialFitAllocator
public class HeapMemoryManager {

    private final BuddySystemAllocator buddy;
    private final SequentialFitAllocator sequential;

    /// Creates a heap manager over a heap of the given logical size.
    ///
    /// @param heapSize heap capacity in allocation units
    public HeapMemoryManager(int heapSize) {
        this.buddy = new BuddySystemAllocator(heapSize);
        this.sequential = new SequentialFitAllocator(heapSize, SequentialFitAllocator.Strategy.BEST_FIT);
    }

    /// Allocates a block via the buddy allocator.
    ///
    /// @param size number of units requested
    /// @return starting unit index, or -1 on failure
    public int buddyAllocate(int size) {
        return buddy.allocate(size);
    }

    /// Releases a buddy-allocated block.
    ///
    /// @param block starting unit index of the block
    public void buddyFree(int block) {
        buddy.free(block);
    }

    /// Returns the total free units in the buddy allocator.
    ///
    /// @return free unit count
    public int buddyFreeUnits() {
        return buddy.getTotalFree();
    }

    /// Allocates a block via the sequential-fit allocator.
    ///
    /// @param size number of units requested
    /// @return starting address, or -1 on failure
    public int sequentialAllocate(int size) {
        return sequential.allocate(size);
    }

    /// Releases a sequentially allocated block.
    ///
    /// @param address starting address of the block
    public void sequentialFree(int address) {
        sequential.free(address);
    }

    /// Returns the total free memory in the sequential-fit allocator.
    ///
    /// @return free byte count
    public int sequentialFreeUnits() {
        return sequential.getTotalFree();
    }
}
