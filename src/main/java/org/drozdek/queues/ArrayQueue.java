package org.drozdek.queues;

import org.drozdek.commons.LoggerService;

import java.util.Arrays;

/**
 * Implementation with queue array.
 */
public class ArrayQueue {
    private final int size;
    private final Object[] storage;
    private int first;
    private int last;

    /**
     * Constructor
     *
     * @param n Storage size
     */
    public ArrayQueue(int n) {
        size = n;
        storage = new Object[size];
        first = last = -1;
    }

    /**
     * Dequeue the last element.
     *
     * @return The dequeued element
     */
    public Object dequeue() {
        Object tmp = storage[first];

        if (first == last)
            last = first = -1;
        else if (first == size - 1)
            first = 0;
        else first++;

        return tmp;
    }

    /**
     * Enqueue a new element
     *
     * @param element Element to add
     */
    public void enqueue(Object element) {
        if (last == size - 1 || last == -1) {
            storage[0] = element;
            last = 0;

            if (first == -1)
                first = 0;
        } else
            storage[++last] = element;
    }

    /**
     * Get the first element.
     *
     * @return The first element in the queue.
     */
    public Object firstElement() {
        return storage[first];
    }

    /**
     * Is queue empty?
     *
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        return first == -1;
    }

    /**
     * Is queue full?
     *
     * @return True if queue is full
     */
    public boolean isFull() {
        return first == 0 && last == size - 1 || first == last + 1;
    }

    /**
     * Print all elements in the queue.
     */
    public void printAll() {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < size; i++) {
            line.append(storage[i]);
            line.append(" ");
        }

        LoggerService.logInfo(line.toString());
    }

    /**
     * Size of the queue
     *
     * @return Number of elements in the queue
     */
    public int size() {
        int i = 0;
        while (i < storage.length && storage[i] != null) {
            i++;
        }

        return i;
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }
}
