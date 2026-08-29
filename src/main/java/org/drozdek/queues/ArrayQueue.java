package org.drozdek.queues;

import org.drozdek.queues.interfaces.QueueInterface;

import java.util.Arrays;

/// Array-based circular queue implementation.
///
/// **Abstract Data Type:** Queue (FIFO - First In, First Out)
///
/// **Real-world use case:** Ring buffers in audio processing,
/// bounded buffers in producer-consumer systems.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for enqueue and dequeue
/// Auxiliary Space: O(n) for the fixed-size storage array
///
/// Bibliography:
///
/// - Thomas H. Cormen et al. *Introduction to Algorithms*, 4th ed. MIT Press.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 4.
public class ArrayQueue implements QueueInterface<Object> {
    private final int capacity;
    private final Object[] storage;
    private int first;
    private int last;

    /// Constructs an array queue with the specified capacity.
    ///
    /// @param capacity the maximum number of elements this queue can hold
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        storage = new Object[capacity];
        first = last = -1;
    }

    /// Removes and returns the element at the front of the queue.
    ///
    /// @return the element at the front of the queue, or null if the queue is empty
    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }

        Object data = storage[first];

        if (first == last) {
            // Queue becomes empty
            last = first = -1;
        } else if (first == capacity - 1) {
            // Wrap around to beginning
            first = 0;
        } else {
            // Move to next position
            first++;
        }

        return data;
    }

    /// Adds an element to the rear of the queue.
    ///
    /// @param element the element to add to the queue
    /// @throws IllegalStateException if the queue is full
    public boolean enqueue(Object element) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }

        if (last == capacity - 1 || last == -1) {
            // Wrap around to beginning or first element
            storage[0] = element;
            last = 0;

            if (first == -1) {
                // First element being added
                first = 0;
            }
        } else {
            // Normal case: increment last and store
            storage[++last] = element;
        }
        return true;
    }

    /// Returns the element at the front of the queue without removing it.
    ///
    /// @return the element at the front of the queue, or null if the queue is empty
    public Object peek() {
        return firstElement();
    }

    /// Returns the element at the front of the queue without removing it.
    ///
    /// @return the element at the front of the queue, or null if the queue is empty
    public Object firstElement() {
        if (isEmpty()) {
            return null;
        }
        return storage[first];
    }

    /// Tests if this queue contains no elements.
    ///
    /// @return true if this queue contains no elements; false otherwise
    public boolean isEmpty() {
        return first == -1;
    }

    /// Tests if this queue is full.
    ///
    /// @return true if this queue is full; false otherwise
    public boolean isFull() {
        // Queue is full if:
        // 1. first is at index 0 and last is at the last index, OR
        // 2. first is exactly one position after last (in circular sense)
        return (first == 0 && last == capacity - 1) ||
                (first == (last + 1) % capacity);
    }

    /// Returns the number of elements in this queue.
    ///
    /// @return the number of elements in this queue
    public int size() {
        if (isEmpty()) {
            return 0;
        }

        if (first <= last) {
            // No wrap-around: elements are in [first, last]
            return last - first + 1;
        } else {
            // Wrap-around: elements are in [first, capacity-1] and [0, last]
            return capacity - first + last + 1;
        }
    }

    /// Removes all elements from this queue by resetting the position markers.
    ///
    /// Runs in O(1); the backing array itself is left untouched, so stale
    /// references are only released as slots get overwritten by later enqueues.
    public void clear() {
        first = last = -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return QueueInterface.boxedQueue("[ EMPTY ]");
        }
        StringBuilder sb = new StringBuilder("FRONT");
        for (int i = 0; i < size(); i++) {
            int index = (first + i) % capacity;
            if (storage[index] != null) {
                sb.append(" ➔ [").append(storage[index]).append("]");
            }
        }
        sb.append(" ➔ REAR");
        return QueueInterface.boxedQueue(sb.toString());
    }

    /// Returns the raw contents of the backing storage array in index order.
    ///
    /// Intended for debugging: unlike {@link #toString()}, it includes stale
    /// slots left behind by wrap-around operations, not only live elements.
    ///
    /// @return a string representation of the internal storage array
    public String showStorage() {
        return Arrays.toString(storage);
    }
}
