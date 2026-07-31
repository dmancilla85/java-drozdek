package org.drozdek.queues;

import org.drozdek.queues.interfaces.QueueInterface;

import java.util.LinkedList;

/// Queue backed by Java's LinkedList, providing a general-purpose FIFO
/// implementation.
///
/// <p><b>Real-world use case:</b> Breadth-first search graph traversal,
/// job scheduling in operating systems.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for enqueue and dequeue
/// Auxiliary Space: O(n) for storing n elements
///
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
public class Queue<T> implements QueueInterface<T> {
    private final LinkedList<T> list;

    public Queue() {
        list = new LinkedList<>();
    }

    public void clear() {
        list.clear();
    }

    public T dequeue() {
        return list.removeFirst();
    }

    public boolean enqueue(T element) {
        list.addLast(element);
        return true;
    }

    public T firstElement() {
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

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
