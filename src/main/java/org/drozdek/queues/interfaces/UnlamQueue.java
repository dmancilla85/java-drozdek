package org.drozdek.queues.interfaces;


/// Queue interface defining standard queue operations.
///
/// Abstract Data Type: Queue (FIFO - First In, First Out)
public interface UnlamQueue {

    /// Removes and returns the element at the front of the queue.
    ///
    /// @return the element at the front of the queue, or null if the queue is empty
    Object dequeue();

    /// Adds an element to the rear of the queue.
    ///
    /// @param obj the element to add to the queue
    /// @return true if the element was added, false if the operation failed
    /// @throws FullQueueException if the queue is full
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
