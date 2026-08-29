package org.drozdek.queues;

import org.drozdek.queues.interfaces.QueueInterface;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/// Queue backed by Java's LinkedList, providing a general-purpose FIFO
/// implementation.
///
/// **Real-world use case:** Breadth-first search graph traversal,
/// job scheduling in operating systems.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for enqueue and dequeue
/// Auxiliary Space: O(n) for storing n elements
///
/// Bibliography:
///
/// - Thomas H. Cormen et al. *Introduction to Algorithms*, 4th ed. MIT Press.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 4.
public class Queue<T> implements QueueInterface<T> {
    private final LinkedList<T> list;

    public Queue() {
        list = new LinkedList<>();
    }

    /// Removes all elements from this queue, leaving it empty.
    public void clear() {
        list.clear();
    }

    /// Removes and returns the element at the front of the queue.
    ///
    /// @return the element removed from the front of the queue
    /// @throws NoSuchElementException if the queue is empty
    public T dequeue() {
        return list.removeFirst();
    }

    /// Adds an element at the rear of the queue.
    ///
    /// @param element the element to add
    /// @return true if the element was added
    public boolean enqueue(T element) {
        list.addLast(element);
        return true;
    }

    /// Returns the element at the front of the queue without removing it.
    ///
    /// @return the element at the front of the queue
    /// @throws NoSuchElementException if the queue is empty
    public T firstElement() {
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /// Returns the element at the front of the queue without removing it.
    ///
    /// @return the element at the front of the queue
    /// @throws NoSuchElementException if the queue is empty
    public T peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return QueueInterface.boxedQueue("[ EMPTY ]");
        }
        StringBuilder sb = new StringBuilder("FRONT");
        for (T element : list) {
            sb.append(" ➔ [").append(element).append("]");
        }
        sb.append(" ➔ REAR");
        return QueueInterface.boxedQueue(sb.toString());
    }

    @Override
    public int size() {
        return list.size();
    }
}
