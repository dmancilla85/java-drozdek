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

    boolean enqueue(T element);

    T dequeue();

    T peek();

    boolean isEmpty();

    int size();

    void clear();

    static String boxedQueue(String content) {
        String line = "║ " + content + " ║";
        String border = "═".repeat(line.length() - 2);
        return "╔" + border + "╗" + System.lineSeparator() +
                line + System.lineSeparator() +
                "╚" + border + "╝";
    }
}
