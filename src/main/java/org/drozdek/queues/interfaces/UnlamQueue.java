package org.drozdek.queues.interfaces;


/// Interface defining standard queue operations (FIFO).
///
/// **Real-world use case:** Request queuing in web servers,
/// message buffering in producer-consumer patterns.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for enqueue and dequeue (per implementation)
/// Auxiliary Space: O(n) for storing n elements
///
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
public interface UnlamQueue {

    /// Removes and returns the element at the front of the queue.
    ///
    /// @return the element at the front of the queue, or null if the queue is empty
    Object dequeue();

    /// Adds an element to the rear of the queue.
    ///
    /// @param obj the element to add to the queue
    /// @return true if the element was added, false if the operation failed
    /// @throws org.drozdek.queues.unlam.FullQueueException if the queue is full
    boolean enqueue(Object obj);

    /// Tests if this queue contains no elements.
    ///
    /// @return true if this queue contains no elements; false otherwise
    boolean isEmpty();

    /// Returns the element at the front of the queue without removing it.
    ///
    /// @return the element at the front of the queue, or null if the queue is empty
    Object peek();

    /// Removes all elements from this queue.
    void clear();
}
