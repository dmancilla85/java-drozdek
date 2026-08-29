package org.drozdek.memory;

import java.util.ArrayList;
import java.util.List;

/// Sequential-fit dynamic storage allocator using first-fit, best-fit, or
/// worst-fit placement policies over a contiguous block list.
///
/// Memory is modelled as a list of free and allocated segments. When a block
/// is requested the allocator searches the free segments according to the
/// configured strategy, splits an oversized segment, and records the new
/// allocated region. Freeing a block merges it with any adjacent free
/// segments to reduce external fragmentation.
///
/// **Real-world use case:** Basis of malloc-style heaps in C runtimes and
/// operating-system kernel heaps.
///
/// Complexity Analysis:
/// Time Complexity: O(n) worst case to locate or merge a segment
/// Auxiliary Space: O(n) for the segment table
///
/// Bibliography:
///
/// - D.E. Knuth. *The Art of Computer Programming*, Vol. 1.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
public class SequentialFitAllocator {

    /// Placement policy that selects which free segment to satisfy a request.
    public enum Strategy {
        /// Smallest free segment that still fits the request.
        BEST_FIT,
        /// First free segment encountered that fits the request.
        FIRST_FIT,
        /// Largest free segment regardless of fit.
        WORST_FIT
    }

    private static final class Segment {
        private int start;
        private int size;
        private boolean free;

        private Segment(int start, int size, boolean free) {
            this.start = start;
            this.size = size;
            this.free = free;
        }
    }

    private final List<Segment> segments = new ArrayList<>();
    private final Strategy strategy;

    /// Creates an allocator over a heap of the given total size.
    ///
    /// @param totalSize total addressable memory size
    /// @param strategy  placement policy to use
    public SequentialFitAllocator(int totalSize, Strategy strategy) {
        this.strategy = strategy;
        this.segments.add(new Segment(0, totalSize, true));
    }

    /// Total amount of memory still free (sum of all free segment sizes).
    ///
    /// @return total free bytes
    public int getTotalFree() {
        int total = 0;
        for (Segment segment : segments) {
            if (segment.free) {
                total += segment.size;
            }
        }
        return total;
    }

    /// Number of free segments currently present (fragmentation indicator).
    ///
    /// @return count of free, non-merged segments
    public int getFreeSegmentCount() {
        int count = 0;
        for (Segment segment : segments) {
            if (segment.free) {
                count++;
            }
        }
        return count;
    }

    /// Allocates a block of the given size, returning its start address, or
    /// {@code -1} when no free segment can satisfy the request.
    ///
    /// @param size number of bytes to allocate
    /// @return start address of the block, or -1 on failure
    public int allocate(int size) {
        if (size < 1) {
            return -1;
        }
        int target = -1;
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            if (segment.free && segment.size >= size) {
                if (target == -1 || betterFit(segment.size, segments.get(target).size)) {
                    target = i;
                }
            }
        }
        if (target == -1) {
            return -1;
        }

        Segment chosen = segments.get(target);
        int address = chosen.start;
        if (chosen.size == size) {
            chosen.free = false;
        } else {
            Segment remainder = new Segment(chosen.start + size, chosen.size - size, true);
            chosen.size = size;
            chosen.free = false;
            segments.add(target + 1, remainder);
        }
        return address;
    }

    private boolean betterFit(int candidateSize, int currentSize) {
        return switch (strategy) {
            case BEST_FIT -> candidateSize < currentSize;
            case WORST_FIT -> candidateSize > currentSize;
            case FIRST_FIT -> false;
        };
    }

    /// Releases an allocated block starting at the given address, merging with
    /// adjacent free segments. Silently does nothing for unknown addresses.
    ///
    /// @param address start address of the block to free
    public void free(int address) {
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            if (!segment.free && segment.start == address) {
                segment.free = true;
                merge(i);
                return;
            }
        }
    }

    private void merge(int index) {
        int i = index;
        while (i + 1 < segments.size() && segments.get(i).free && segments.get(i + 1).free) {
            Segment current = segments.get(i);
            Segment next = segments.remove(i + 1);
            current.size += next.size;
        }
        while (i - 1 >= 0 && segments.get(i).free && segments.get(i - 1).free) {
            Segment prev = segments.remove(i - 1);
            i--;
            segments.get(i).size += prev.size;
        }
    }
}
