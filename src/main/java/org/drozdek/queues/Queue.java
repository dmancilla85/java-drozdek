package org.drozdek.queues;

import java.util.LinkedList;

public class Queue<T> {
    private final LinkedList<T> list;

    /**
     * Default constructor.
     */
    public Queue() {
        list = new LinkedList<>();
    }

    /**
     * Clear queue.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Dequeue the last element.
     *
     * @return The dequeued element
     */
    public T dequeue() {
        return list.removeFirst();
    }

    /**
     * Enqueue a new element
     *
     * @param element Element to add
     */
    public void enqueue(T element) {
        list.addLast(element);
    }

    /**
     * Get the first element.
     *
     * @return The first element in the queue.
     */
    public T firstElement() {
        return list.getFirst();
    }

    /**
     * Is queue empty?
     *
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Size of the queue
     *
     * @return Number of elements in the queue
     */
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
