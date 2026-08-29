package org.drozdek.queues.unlam;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.interfaces.QueueInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/// Binary heap priority queue implementation.
///
/// **Real-world use case:** Dijkstra's shortest-path algorithm,
/// Huffman coding, and real-time task scheduling by priority.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) for insert and extract, O(1) for peek
/// Auxiliary Space: O(n) for storing n elements
///
/// Bibliography:
///
/// - J. W. J. Williams. *Algorithm 232: Heapsort*. Communications of the ACM, 1964. https://doi.org/10.1145/512274.3734138
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
public class Heap<E extends Comparable<? super E>> implements QueueInterface<E> {
    protected final Comparator<? super E> cmp;
    protected final List<E> nodes;
    protected int count;

    /// Constructs an empty min-heap with the given capacity hint.
    ///
    /// Elements are ordered by the supplied comparator or, when it is null,
    /// by their natural ordering.
    ///
    /// @param capacity initial capacity hint; must be greater than zero
    /// @param cmp comparator defining the priority order, or null for natural ordering
    /// @throws IllegalArgumentException if capacity is not greater than zero
    public Heap(int capacity, Comparator<? super E> cmp) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        nodes = new ArrayList<>(capacity);
        this.cmp = cmp;
    }

    /// Constructs an empty min-heap ordered by the natural ordering of its elements.
    ///
    /// @param capacity initial capacity hint; must be greater than zero
    /// @throws IllegalArgumentException if capacity is not greater than zero
    public Heap(int capacity) {
        this(capacity, null);
    }

    /// Removes all elements from this heap, leaving it empty.
    public synchronized void clear() {
        nodes.clear();
        count = 0;
    }

    protected int compare(E a, E b) {
        if (cmp != null)
            return cmp.compare(a, b);
        return a.compareTo(b);
    }

    /// Removes and returns the smallest element of this heap.
    ///
    /// The last element is sifted down to restore the heap invariant.
    /// Runs in O(log n).
    ///
    /// @return the smallest element according to the heap ordering,
    ///         or null if this heap is empty
    public synchronized E extract() {
        if (count < 1)
            return null;

        int k = 0;
        E least = nodes.get(k);
        --count;
        E x = nodes.get(count);
        nodes.set(count, null);

        boolean shouldContinue = true;
        while (shouldContinue) {
            int l = left(k);
            if (l >= count)
                shouldContinue = false;
            else {
                int r = right(k);
                int child = (r >= count || compare(nodes.get(l), nodes.get(r)) < 0) ? l : r;
                if (compare(x, nodes.get(child)) > 0) {
                    nodes.set(k, nodes.get(child));
                    k = child;
                } else
                    shouldContinue = false;
            }
        }
        nodes.set(k, x);
        return least;
    }

    /// Inserts an element into this heap, sifting it up to restore the heap invariant.
    ///
    /// Runs in O(log n).
    ///
    /// @param x the element to insert
    public synchronized void insert(E x) {
        nodes.add(x);
        int k = count;
        ++count;
        while (k > 0) {
            int par = parent(k);
            if (compare(x, nodes.get(par)) < 0) {
                nodes.set(k, nodes.get(par));
                k = par;
            } else
                break;
        }
        nodes.set(k, x);
    }

    protected final int left(int k) {
        return 2 * k + 1;
    }

    protected final int parent(int k) {
        return (k - 1) / 2;
    }

    /// Returns the smallest element of this heap without removing it.
    ///
    /// Runs in O(1).
    ///
    /// @return the smallest element according to the heap ordering,
    ///         or null if this heap is empty
    public synchronized E peek() {
        if (count > 0)
            return nodes.get(0);
        else
            return null;
    }

    protected final int right(int k) {
        return 2 * (k + 1);
    }

    /// Inserts an element into this heap; equivalent to calling insert(x).
    ///
    /// @param x the element to add
    /// @return true if the element was added
    public synchronized boolean enqueue(E x) {
        insert(x);
        return true;
    }

    /// Removes and returns the smallest element of this heap;
    /// equivalent to calling extract().
    ///
    /// @return the smallest element according to the heap ordering,
    ///         or null if this heap is empty
    public synchronized E dequeue() {
        return extract();
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    @Override
    public synchronized String toString() {
        if (isEmpty()) {
            return QueueInterface.boxedQueue("[ EMPTY ]");
        }
        StringBuilder sb = new StringBuilder("FRONT");
        for (int i = 0; i < count; i++) {
            sb.append(" \u2794 [").append(nodes.get(i)).append("]");
        }
        sb.append(" \u2794 REAR");
        return QueueInterface.boxedQueue(sb.toString());
    }

    @Override
    public synchronized void print() {
        LoggerService.logInfo(this.showId() +
                System.lineSeparator() +
                toString());
    }

    public synchronized int size() {
        return count;
    }
}
