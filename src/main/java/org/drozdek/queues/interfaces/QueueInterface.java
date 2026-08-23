package org.drozdek.queues.interfaces;

import org.drozdek.commons.DataTypeInterface;

/// Interface defining First-In-First-Out (FIFO) queue operations.
///
/// **Real-world use case:** Task scheduling, breadth-first search,
/// and print job spooling.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for enqueue and dequeue (per implementation)
/// Auxiliary Space: O(n) for storing n elements
///
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
public interface QueueInterface<T> extends DataTypeInterface {

    /// Adds an element to the rear of this queue.
    ///
    /// @param element the element to add
    /// @return true if the element was added; false if the operation failed
    boolean enqueue(T element);

    /// Removes and returns the element at the front of this queue.
    ///
    /// @return the element removed from the front, or null if this queue is empty
    T dequeue();

    /// Returns the element at the front of this queue without removing it.
    ///
    /// @return the element at the front of this queue, or null if this queue is empty
    T peek();

    /// Tests if this queue contains no elements.
    ///
    /// @return true if this queue contains no elements; false otherwise
    boolean isEmpty();

    /// Returns the number of elements currently stored in this queue.
    ///
    /// @return the number of elements in this queue
    int size();

    /// Removes all elements from this queue, leaving it empty.
    void clear();

    /// Wraps the given text in a unicode double-line box.
    ///
    /// Used by queue implementations to render pretty-printed state.
    ///
    /// @param content text to place inside the box
    /// @return a boxed multi-line representation of the content
    static String boxedQueue(String content) {
        String line = "║ " + content + " ║";
        String border = "═".repeat(line.length() - 2);
        return "╔" + border + "╗" + System.lineSeparator() +
                line + System.lineSeparator() +
                "╚" + border + "╝";
    }
}
