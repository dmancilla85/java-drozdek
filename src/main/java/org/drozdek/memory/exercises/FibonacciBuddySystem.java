package org.drozdek.memory.exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/// Fibonacci buddy-system allocator that splits memory along Fibonacci sizes
/// rather than powers of two.
///
/// A block of index {@code i >= 2} and size {@code fib[i]} is split into a
/// larger buddy of index {@code i-1} and a smaller buddy of index {@code i-2}.
/// Parent and sibling relationships are recorded at split time, keyed by the
/// block's (start, index), so that freeing a block can unambiguously merge it
/// with its real buddy.
///
/// **Real-world use case:** Reclaims a wider range of object sizes with less
/// internal fragmentation than a binary buddy system for workloads whose sizes
/// are rarely powers of two.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) amortized for allocate and free
/// Auxiliary Space: O(n) for the free lists and bookkeeping maps
///
/// Bibliography:
///
/// - D.E. Knuth, *The Art of Computer Programming*, Vol. 1.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
public class FibonacciBuddySystem {

    private record Key(long start, int index) {
    }

    private record Sibling(int buddyIndex, long buddyStart) {
    }

    private final long[] fib;
    private final TreeMap<Integer, Long> freeByIndex = new TreeMap<>();
    private final Map<Long, Integer> allocatedStartIndex = new HashMap<>();
    private final Map<Key, Sibling> siblingOf = new HashMap<>();
    private final Map<Key, Key> parentOf = new HashMap<>();

    /// Creates a Fibonacci buddy allocator whose capacity is the smallest
    /// Fibonacci number at least as large as the requested size.
    ///
    /// @param size maximum addressable block size in units
    public FibonacciBuddySystem(int size) {
        List<Long> sequence = new ArrayList<>();
        sequence.add(1L);
        sequence.add(1L);
        long a = 1;
        long b = 1;
        while (b < size) {
            long next = a + b;
            a = b;
            b = next;
            sequence.add(b);
        }
        if (sequence.get(sequence.size() - 1) < size) {
            sequence.add(a + b);
        }
        fib = new long[sequence.size()];
        for (int i = 0; i < sequence.size(); i++) {
            fib[i] = sequence.get(i);
        }
        freeByIndex.put(fib.length - 1, 0L);
    }

    /// Largest allocatable size this allocator manages.
    ///
    /// @return the top Fibonacci block size
    public long getCapacity() {
        return fib[fib.length - 1];
    }

    /// Allocates the smallest Fibonacci block able to hold the request.
    ///
    /// @param size number of units requested
    /// @return starting unit index, or -1 on failure
    public long allocate(int size) {
        if (size < 1) {
            return -1;
        }
        int target = smallestIndexAtLeast(size);
        if (target < 0) {
            return -1;
        }
        Integer slot = freeByIndex.ceilingKey(target);
        if (slot == null) {
            return -1;
        }
        long start = freeByIndex.remove(slot);
        int current = slot;
        while (current > target && current >= 2) {
            int largeIndex = current - 1;
            int smallIndex = current - 2;
            long smallStart = start + fib[largeIndex];

            Key largeKey = new Key(start, largeIndex);
            Key smallKey = new Key(smallStart, smallIndex);
            siblingOf.put(largeKey, new Sibling(smallIndex, smallStart));
            siblingOf.put(smallKey, new Sibling(largeIndex, start));
            parentOf.put(largeKey, new Key(start, current));
            parentOf.put(smallKey, new Key(start, current));

            freeByIndex.put(smallIndex, smallStart);
            current = largeIndex;
        }
        allocatedStartIndex.put(start, current);
        return start;
    }

    /// Frees a block allocated at the given start, merging it with its buddy
    /// when that buddy is also free. Unknown starts are ignored.
    ///
    /// @param start starting unit index of the block
    public void free(long start) {
        Integer index = allocatedStartIndex.remove(start);
        if (index == null) {
            return;
        }
        long currentStart = start;
        int currentIndex = index;
        while (true) {
            Sibling sibling = siblingOf.get(new Key(currentStart, currentIndex));
            if (sibling == null) {
                break;
            }
            Long siblingFree = freeByIndex.get(sibling.buddyIndex());
            if (siblingFree == null || siblingFree != sibling.buddyStart()) {
                break;
            }
            freeByIndex.remove(sibling.buddyIndex());
            Key parent = parentOf.get(new Key(currentStart, currentIndex));
            currentStart = parent.start;
            currentIndex = parent.index;
        }
        freeByIndex.put(currentIndex, currentStart);
    }

    /// Total number of free units currently available.
    ///
    /// @return sum of all free block sizes
    public long getTotalFree() {
        long total = 0;
        for (var entry : freeByIndex.entrySet()) {
            total += fib[entry.getKey()];
        }
        return total;
    }

    /// Number of distinct free blocks currently tracked.
    ///
    /// @return free block count
    public int getFreeBlockCount() {
        return freeByIndex.size();
    }

    private int smallestIndexAtLeast(int size) {
        for (int i = 0; i < fib.length; i++) {
            if (fib[i] >= size) {
                return i;
            }
        }
        return -1;
    }
}
