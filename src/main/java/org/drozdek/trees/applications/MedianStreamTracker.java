package org.drozdek.trees.applications;

import org.drozdek.trees.MaximumHeap;
import org.drozdek.trees.MinimumHeap;

/// Streaming median tracker maintained with two heaps.
///
/// The lower half of the stream lives in a max-heap and the upper half in a
/// min-heap. Inserting balances the two heaps so the median is always either the
/// root of the larger heap or the average of both roots, giving O(log n)
/// insertion and O(1) median lookup.
///
/// **Real-world use case:** Real-time analytics, network latency monitoring, and
/// any dashboard that reports the running median of an ongoing measurement
/// stream.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) per insertion, O(1) to read the median
/// Auxiliary Space: O(n) for the two heaps
///
/// Bibliography:
///
/// - Median of medians / two-heap median. *Wikipedia*. https://en.wikipedia.org/wiki/Median
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
///
/// @see MinimumHeap
/// @see MaximumHeap
public class MedianStreamTracker {

    private final MaximumHeap<Integer> lower;
    private final MinimumHeap<Integer> upper;

    /// Creates an empty median tracker.
    public MedianStreamTracker() {
        this.lower = new MaximumHeap<>();
        this.upper = new MinimumHeap<>();
    }

    /// Adds a value to the stream and rebalances the two heaps.
    ///
    /// @param value measurement to add
    public void add(int value) {
        if (lower.isEmpty() || value <= lower.getMax()) {
            lower.insert(value);
        } else {
            upper.insert(value);
        }
        rebalance();
    }

    /// Returns the current median of the stream.
    ///
    /// @return median as a double
    public double median() {
        if (lower.size() > upper.size()) {
            return lower.getMax();
        }
        return (lower.getMax() + upper.getMin()) / 2.0;
    }

    /// Returns the number of measurements seen so far.
    ///
    /// @return stream size
    public int size() {
        return lower.size() + upper.size();
    }

    private void rebalance() {
        if (lower.size() > upper.size() + 1) {
            upper.insert(lower.extractMax());
        } else if (upper.size() > lower.size()) {
            lower.insert(upper.extractMin());
        }
    }
}
